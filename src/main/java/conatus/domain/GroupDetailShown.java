package conatus.domain;

import conatus.domain.*;
import conatus.infra.AbstractEvent;
import java.util.Date;
import lombok.Data;

@Data
public class GroupDetailShown extends AbstractEvent {

    private Long id;
    private Long userId;
    private Long groupId;
    private String category;

    public GroupDetailShown(Group aggregate) {
        super(aggregate);
    }

    public GroupDetailShown() {
        super();
    }
    // keep

}
