package conatus.domain.history;

import conatus.domain.middle.PostMiddleService;
import conatus.domain.history.event.GroupDetailShown;
import conatus.domain.history.event.GroupSearched;
import conatus.domain.history.event.PostAccessCounted;
import conatus.domain.middle.Url;
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

    private final PostMiddleService postMiddleService;


    // kafka 이벤트 발송
    // 기록 발송
    public void publishEvent(){

//        SentHistory sentHistory = sentHistoryRepository.findAll().get(0);
        // 모든 기록
//        List<History> historyList = historyRepository.findByIdGreaterThan(sentHistory.getSent());
        List<History> historyList = (List<History>)historyRepository.findAll();


        // 기록 카프카로 publish
        for (History history: historyList){

            if (history.getGroupId() != 0 && history.getPostId() == 0){
                System.out.println("====================================================");
                System.out.println("====================================================");
                System.out.println("GroupDetailShown");
                System.out.println("====================================================");
                System.out.println("====================================================");
                GroupDetailShown groupDetailShown = new GroupDetailShown(history.getId(), history.getUserId(), history.getGroupId(), history.getCategory());
//                groupDetailShown.publishAfterCommit();
                groupDetailShown.publish();
                postMiddleService.sendTo(Url.MIDDLE.getUrl() + "/GroupDetailShown", groupDetailShown);
            }
            else if (history.getGroupId() != 0 && history.getPostId() != 0){
                System.out.println("====================================================");
                System.out.println("====================================================");
                System.out.println("PostAccessCounted");
                System.out.println("====================================================");
                System.out.println("====================================================");
                PostAccessCounted postAccessCounted = new PostAccessCounted(history.getId(), history.getGroupId(), history.getUserId(),history.getCount());
//                postAccessCounted.publishAfterCommit();
                postAccessCounted.publish();
                postMiddleService.sendTo(Url.MIDDLE.getUrl() + "/PostAccessCounted", postAccessCounted);
            }
            else if (history.getKeyword() != ""){
                System.out.println("====================================================");
                System.out.println("====================================================");
                System.out.println("GroupSearched");
                System.out.println("====================================================");
                System.out.println("====================================================");
                GroupSearched groupSearched = new GroupSearched(history.getId(), history.getUserId(),history.getKeyword());
//                groupSearched.publishAfterCommit();
                groupSearched.publish();
                postMiddleService.sendTo(Url.MIDDLE.getUrl() + "/GroupSearched", groupSearched);

            }

            // 어디까지 보냈는지 기록
//            sentHistory.setSent(historyList.get(historyList.size()-1).getId());
//            sentHistoryRepository.save(sentHistory);
            // TODO : history 테이블 초기화

        }
    }




    // 전체 history 데이터
    public void publishAll(){
        List<History> historyList = (List<History>) historyRepository.findAll();
        for (History history: historyList) {
            System.out.println("===============================");
            System.out.println("===============================");
            System.out.println("history.getId() = " + history.getId());
            System.out.println("history.getUserId() = " + history.getUserId());
            System.out.println("history.getKeyword() = " + history.getKeyword());
            System.out.println("history.getCategory() = " + history.getCategory());
            System.out.println("history.getGroupId() = " + history.getGroupId());
            System.out.println("history.getPostId() = " + history.getPostId());
            System.out.println("history.getCount() = " + history.getCount());
            System.out.println("history.getIsLiked() = " + history.getIsLiked());
            System.out.println("history.getIsClicked() = " + history.getIsClicked());
            System.out.println("===============================");
            System.out.println("===============================");
        }
    }

    // 어떤 유저가 어떤 post를 클릭했는지
    public Optional<History> findAccessPostId(Long userId, Long postId) {
        return historyRepository.findByUserIdAndPostId(userId, postId);
    }


    // history에 저장하기
    public void saveHistory(History history){
        historyRepository.save(history);
    }


}
