package conatus.domain.info;

import conatus.domain.history.History;
import conatus.domain.history.HistoryRepository;
import conatus.domain.info.dto.InfoDto;
import conatus.domain.like.Like;
import conatus.domain.like.LikeRepository;
import conatus.domain.like.dto.LikeDto;
import conatus.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class InfoService {
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final HistoryRepository historyRepository;
    private final InfoRepository infoRepository;

    @Transactional
    public InfoDto getById(Long id)
    {
        Info info = infoRepository.findById(id).get();
        InfoDto infoDto = new InfoDto(info);

        // 유저의 조회 기록 남기기 (구현해야함)


        return infoDto;
    }
}
