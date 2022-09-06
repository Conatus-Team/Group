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
import conatus.domain.posts.Posts;
import conatus.domain.posts.PostsRepository;
import conatus.domain.user.User;
import conatus.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
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
    private final PostsRepository postsRepository;

    // 그룹 디테일보기
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

    // 그룹 생성
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

        Posts posts = Posts.builder()
                .groupId(savedInfo.getId())
                .author(user.getNickname())
                .title(savedInfo.getName()+"에서 즐거운 시간 보내시길 바랍니다")
                .content(" ")
                .userId(userId)
                .build();
        memberRepository.save(member);
        postsRepository.save(posts);

        InfoDto infoDto = new InfoDto(savedInfo);

        return infoDto;

    }

    // 내가 속한 그룹 보기
    @Transactional
    public List<Info> getGroupByUserId(Long userId){
        List<Member> member = memberRepository.findByUserId(userId);
        List<Long> groupIdList = member.stream().map(x-> x.getGroupId()).collect(Collectors.toList());
//        List<Info> infoList = new ArrayList<Info> ();

        List<Info> infoList = groupIdList.stream().map(x-> infoRepository.findById(x).get()).collect(Collectors.toList());
        return infoList;
    }

    // groupId에 해당하는 그룹 리턴
    public Info getGroup(Long groupId){
        return infoRepository.findById(groupId).get();
    }

}
