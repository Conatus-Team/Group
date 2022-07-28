package conatus.domain;

import conatus.domain.*;
import conatus.infra.AbstractEvent;
import java.util.Date;
import lombok.Data;

@Data
public class GroupJoined extends AbstractEvent {

    private Long id;
    private Long groupId;
    private Long userId;
    private String category;

    public GroupJoined(Group aggregate) {
        super(aggregate);
    }

    public GroupJoined() {
        super();
    }
    // keep

}
