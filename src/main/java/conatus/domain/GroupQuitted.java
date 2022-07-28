package conatus.domain;

import conatus.domain.*;
import conatus.infra.AbstractEvent;
import java.util.Date;
import lombok.Data;

@Data
public class GroupQuitted extends AbstractEvent {

    private Long id;
    private Long groupId;
    private Long userId;

    public GroupQuitted(Group aggregate) {
        super(aggregate);
    }

    public GroupQuitted() {
        super();
    }
    // keep

}
