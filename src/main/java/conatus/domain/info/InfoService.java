package conatus.domain.info;

import conatus.domain.history.History;
import conatus.domain.history.HistoryRepository;
import conatus.domain.info.dto.InfoDto;
import conatus.domain.like.Like;
import conatus.domain.like.LikeRepository;
import conatus.domain.like.dto.LikeDto;
import conatus.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.stream.Collectors;

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
    public InfoDto getById(Long userId, Long groupId)
    {
        Info info = infoRepository.findById(groupId).get();
        InfoDto infoDto = new InfoDto(info);

        // 유저의 조회 기록 남기기
        Optional<History> foundHistory = historyRepository.findByUserIdAndGroupId(userId, groupId);
        if (foundHistory.isPresent()) {
            foundHistory.get().updateCount();
            historyRepository.save(foundHistory.get());
        }else {
            History history = new History(userId, groupId, info.getCategory());
            historyRepository.save(history);
        }

        return infoDto;
    }
    
    // 검색 결과
    @Transactional
    public List<InfoDto> search(Long userId, String keyword)
    {
        List<Info> info = infoRepository.findByNameContainingOrCategoryContaining(keyword, keyword);
        // 찾은 것이 없을 경우
        List<InfoDto> result = new Vector<InfoDto>();
        // 찾은 것이 있을 경우
        if(!info.isEmpty()) {
        	result =  info.stream().map(x->new InfoDto(x)).collect(Collectors.toList());

        }
        
        // 유저의 조회 기록 남기기
        Optional<History> foundHistory = historyRepository.findByUserIdAndKeyword(userId, keyword);
        if (foundHistory.isPresent()) {
        	foundHistory.get().updateCount();
        	historyRepository.save(foundHistory.get());
        }else {
        	History history = new History(userId, keyword);
        	historyRepository.save(history);
        }
        
        return result;
    }
}
