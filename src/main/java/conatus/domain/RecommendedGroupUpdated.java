package conatus.domain;

import conatus.domain.*;
import conatus.infra.AbstractEvent;
import java.util.Date;
import lombok.Data;

@Data
public class RecommendedGroupUpdated extends AbstractEvent {

    private Long id;

    public RecommendedGroupUpdated(Group aggregate) {
        super(aggregate);
    }

    public RecommendedGroupUpdated() {
        super();
    }
    // keep

}
