package conatus.domain.like;

import conatus.domain.history.History;
import conatus.domain.history.HistoryRepository;
import conatus.domain.info.Info;
import conatus.domain.info.InfoRepository;
import conatus.domain.like.dto.LikeDto;
import conatus.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final HistoryRepository historyRepository;
    private final InfoRepository infoRepository;

    @Transactional
    public Long save(LikeDto likeDto)
    {
        Like likeObj = new Like(likeDto.getGroupId(), userRepository.findByUserId(likeDto.getUserId()));

        // 그룹 like 개수 올리기
        Info infoObj = infoRepository.findById(likeDto.getGroupId()).get();
        infoObj.setLikeCount(infoObj.getLikeCount() + 1);
        infoRepository.save(infoObj);

        // 유저의 like 기록 남기기
        History historyObj = new History(likeDto.getUserId(), likeDto.getGroupId(),(long)0, "",infoObj.getCategory(), Boolean.TRUE, Boolean.FALSE);
        historyRepository.save(historyObj);

        // like 저장하기
        return likeRepository.save(likeObj).getId();
    }

}
