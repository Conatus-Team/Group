package conatus.domain.member;

import conatus.domain.history.History;
import conatus.domain.history.HistoryRepository;
import conatus.domain.info.Info;
import conatus.domain.info.InfoRepository;
import conatus.domain.like.Like;
import conatus.domain.like.LikeRepository;
import conatus.domain.like.dto.LikeDto;
import conatus.domain.member.dto.JoinDto;
import conatus.domain.user.User;
import conatus.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final HistoryRepository historyRepository;
    private final InfoRepository infoRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Member save(Long userId, Long groupId)
    {
        // 이미 있는 멤버인지
        Optional<Member> existMember = memberRepository.findByGroupIdAndUserId(groupId, userId);
        if (existMember.isPresent()){
//            return existMember.get();
            return null;
        }
        
        // 유저 찾기
        User user = userRepository.findByUserId(userId);
        // 멤버 오브젝트 만들기
        Member memberObj = new Member(groupId, userId, user.getNickname(), Boolean.FALSE);

        // 그룹 member 개수 올리기
        Info infoObj = infoRepository.findById(groupId).get();
        infoObj.setMemberCount(infoObj.getMemberCount() + 1);
        infoRepository.save(infoObj);

        // 멤버 저장하기
        return memberRepository.save(memberObj);
    }
}
