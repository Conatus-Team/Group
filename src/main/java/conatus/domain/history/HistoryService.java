package conatus.domain.history;

import conatus.domain.history.event.GroupDetailShown;
import conatus.domain.history.event.GroupSearched;
import conatus.domain.history.event.PostAccessCounted;
import conatus.domain.user.User;
import conatus.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final SentHistoryRepository sentHistoryRepository;

    // kafka 이벤트 발송
    // 기록 발송
    public void publishGroupDetailShownEvent(){

        SentHistory sentHistory = sentHistoryRepository.findAll().get(0);
        // 모든 기록
        List<History> historyList = historyRepository.findByIdGreaterThan(sentHistory.getSent());

        // 기록 카프카로 publish
        for (History history: historyList){

            if (history.getGroupId() != 0 && history.getPostId() == 0){
                GroupDetailShown groupDetailShown = new GroupDetailShown(history.getId(), history.getUserId(), history.getGroupId(), history.getCategory());
                groupDetailShown.publishAfterCommit();
            }
            else if (history.getGroupId() != 0 && history.getPostId() != 0){
                PostAccessCounted postAccessCounted = new PostAccessCounted(history.getId(), history.getGroupId(), history.getUserId(),history.getCount());
                postAccessCounted.publishAfterCommit();
            }
            else if (history.getKeyword() != ""){
                GroupSearched groupSearched = new GroupSearched(history.getId(), history.getUserId(),history.getKeyword());
                groupSearched.publishAfterCommit();
            }

            // 어디까지 보냈는지 기록
            sentHistory.setSent(historyList.get(historyList.size()-1).getId());
            sentHistoryRepository.save(sentHistory);


        }
    }
}
