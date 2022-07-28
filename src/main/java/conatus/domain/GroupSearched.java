package conatus.domain;

import conatus.domain.*;
import conatus.infra.AbstractEvent;
import java.util.Date;
import lombok.Data;

@Data
public class GroupSearched extends AbstractEvent {

    private Long id;
    private Long userId;
    private String keyword;

    public GroupSearched(Group aggregate) {
        super(aggregate);
    }

    public GroupSearched() {
        super();
    }
    // keep

}
