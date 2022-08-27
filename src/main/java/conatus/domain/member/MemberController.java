package conatus.domain.member;

import conatus.domain.member.dto.JoinDto;
import conatus.domain.member.event.GroupJoined;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/group")
@Transactional
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/group/join")
    public Member save(@RequestHeader(value="Authorization") Long userId,
                       @RequestBody JoinDto joinDto){
        
        joinDto.setUserId(userId);
        Member member = memberService.save(joinDto);
        // *** 카프카 이벤트 발행 ***
        // Chatting, RecommendSystem 서버로 어떤 유저가 어느 그룹에 가입했는지 발행
        GroupJoined groupJoined = new GroupJoined(member);
        groupJoined.publish();

        return memberService.save(joinDto);
        
    }


    // TODO : 그룹 탈퇴



//    private final PostsService postsService;
//
//    @PostMapping("/api/v1/posts")
//    public Long save(@RequestBody PostsSaveRequestDto requestDto){
//        return postsService.save(requestDto);
//    }
//
//    @PutMapping("/api/v1/posts/{id}")
//    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
//        return postsService.update(id, requestDto);
//    }
//    @GetMapping("/api/v1/posts/{id}")
//    public PostsResponseDto findById(@PathVariable Long id){
//        return postsService.findById(id);
//    }
}
