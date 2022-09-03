package conatus.domain;

import conatus.domain.member.MemberService;
import conatus.domain.recommend.Recommend;
import conatus.domain.recommend.RecommendService;
import conatus.domain.recommend.event.GroupRecommended;
import conatus.domain.user.UserService;
import conatus.domain.user.event.SignedUp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/group/connect_middle")
public class GetMiddleController {
    public final UserService userService;
    public final MemberService memberService;
    public final RecommendService recommendService;


    // 구독 : (Auth)회원가입
    @PostMapping("/SignedUp")
    public void postUser(@RequestBody SignedUp signedUp) {
        if (!signedUp.validate()) return;
        userService.postUser(signedUp);

    }

    // 구독 : (Recommend)추천 그룹
    //TODO : 테스트하기
    @PostMapping("/GroupRecommended")
    public void wheneverGroupRecommended_UpdateRecommendedGroup(
            @RequestBody GroupRecommended groupRecommended
    ) {
        if (!groupRecommended.validate()) return;

        Recommend newRecommendGroup = recommendService.postRecommend(
                groupRecommended.getGroupId(),
                groupRecommended.getUserId()
        );

        System.out.println("추천 그룹 저장 성공!");
        System.out.println(newRecommendGroup);

    }

}
