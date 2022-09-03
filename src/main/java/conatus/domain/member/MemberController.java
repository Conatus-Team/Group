package conatus.domain.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import conatus.domain.PostMiddleService;
import conatus.domain.member.dto.JoinDto;
import conatus.domain.member.event.GroupJoined;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Collections;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/group")
@Transactional
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final PostMiddleService postMiddleService;
    @PostMapping("/join/{groupId}")
    public boolean save(@RequestHeader(value="Authorization") Long userId,
                       @PathVariable Long groupId) throws JsonProcessingException {

        Member member = memberService.save(userId, groupId);
        if(member == null) {
            // 이미 존재하는 유저
            return true;
        }

        // *** 카프카 이벤트 발행 ***
        // Chatting, RecommendSystem 서버로 어떤 유저가 어느 그룹에 가입했는지 발행
        GroupJoined groupJoined = new GroupJoined(member);
        groupJoined.publish();

        // Middle 서버로 http request
        postMiddleService.sendTo("http://localhost:8082/middle/GroupJoined", groupJoined);


        // 존재하지 않는 유저. 그룹 가입.
        return false;

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
