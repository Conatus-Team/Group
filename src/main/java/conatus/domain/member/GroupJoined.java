package conatus.domain.member;

import conatus.domain.info.Info;
import conatus.infra.AbstractEvent;
import lombok.Data;

@Data
public class GroupJoined extends AbstractEvent {

    private Long id;
    private Long groupId;
    private Long userId;
    private String category;

    public GroupJoined(Info aggregate) {
        super(aggregate);
    }

    public GroupJoined() {
        super();
    }
    // keep

}
