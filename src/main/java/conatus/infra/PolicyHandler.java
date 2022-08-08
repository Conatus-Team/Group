package conatus.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import conatus.config.kafka.KafkaProcessor;
import conatus.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;

import conatus.domain.member.Member;
import conatus.domain.member.MemberRepository;
import conatus.domain.member.MemberService;
import conatus.domain.member.dto.JoinDto;
import conatus.domain.member.event.GroupJoined;
import conatus.domain.recommend.event.GroupRecommended;
import conatus.domain.user.User;
import conatus.domain.user.UserService;
import conatus.domain.user.event.SignedUp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class PolicyHandler {

    public final UserService userService;
    public final MemberService memberService;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    // 유저 회원가입 이벤트
    // 유저 등록
    @StreamListener(KafkaProcessor.INPUT)
//    @SendTo(KafkaProcessor.OUTPUT)
    public void postUser(@Payload SignedUp signedUp) {
        if (!signedUp.validate()) throw new RuntimeException();
        userService.postUser(signedUp);

    }

    // 유저 그룹 가입 이벤트
    // 유저 그룹 채팅방 가입
    @StreamListener(KafkaProcessor.INPUT)
    public void postUserChattingRoom(@Payload GroupJoined groupJoined) {
        if (!groupJoined.validate()) throw new RuntimeException();
        JoinDto joinDto = new JoinDto(groupJoined.getGroupId(), groupJoined.getUserId());
        memberService.save(joinDto);

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverGroupRecommended_UpdateRecommendedGroup(
            @Payload GroupRecommended groupRecommended
    ) {
        if (!groupRecommended.validate()) return;
        GroupRecommended event = groupRecommended;
        System.out.println(
                "\n\n##### listener UpdateRecommendedGroup : " +
                        groupRecommended.toJson() +
                        "\n\n"
        );

//        // Sample Logic //
//        Group.updateRecommendedGroup(event);
    }
    // keep

}
