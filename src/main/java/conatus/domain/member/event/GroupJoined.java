package conatus.domain.member.event;

import conatus.domain.info.Info;
import conatus.domain.member.Member;
import conatus.domain.member.dto.JoinDto;
import conatus.infra.AbstractEvent;
import lombok.Data;

@Data
public class GroupJoined extends AbstractEvent {

//    private Long id;
//    private Long groupId;
//    private Long userId;
//    private String category;
    private Member member;

    public GroupJoined(Member aggregate) {
        super(aggregate);
        member = aggregate;
    }

    public GroupJoined() {
        super();
    }
    // keep

}
