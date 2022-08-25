package conatus.domain.info;

import conatus.domain.history.History;
import conatus.domain.history.HistoryRepository;
import conatus.domain.info.dto.CreateGroupDto;
import conatus.domain.info.dto.InfoDto;
import conatus.domain.like.Like;
import conatus.domain.like.LikeRepository;
import conatus.domain.like.dto.LikeDto;
import conatus.domain.member.Member;
import conatus.domain.member.MemberRepository;
import conatus.domain.user.User;
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
    private final MemberRepository memberRepository;

    @Transactional
    public InfoDto getById(Long id)
    {
        Info info = infoRepository.findById(id).get();
        InfoDto infoDto = new InfoDto(info);

        // 유저의 조회 기록 남기기 (구현해야함)


        return infoDto;
    }
    
    // 검색 결과
    @Transactional
    public List<InfoDto> search(String keyword)
    {
        List<Info> info = infoRepository.findByNameContainingOrCategoryContaining(keyword, keyword);
        // 찾은 것이 없을 경우
        List<InfoDto> result = new Vector<InfoDto>();
        // 찾은 것이 있을 경우
        if(!info.isEmpty()) {
        	result =  info.stream().map(x->new InfoDto(x)).collect(Collectors.toList());

        }
        
        // 유저의 조회 기록 남기기 (userId 부분 수정 필요)

        Long userId = (long) 1;
        
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

    @Transactional
    public InfoDto create(Long userId, CreateGroupDto createGroupDto){
        User user = userRepository.findByUserId(userId);


        Info info = Info.builder()
                .thumbnail(createGroupDto.getThumbnail())
                .category(createGroupDto.getCategory())
                .leaderId(userId)
                .name(createGroupDto.getName())
                .explanation(createGroupDto.getExplanation())
                .build();

        Info savedInfo = infoRepository.save(info);

        Member member = Member.builder()
                .groupId(savedInfo.getId())
                .isLeader(Boolean.TRUE)
                .userId(userId)
                .nickname(user.getNickname())
                .build();

        memberRepository.save(member);

        InfoDto infoDto = new InfoDto(savedInfo);

        return infoDto;

    }
}
