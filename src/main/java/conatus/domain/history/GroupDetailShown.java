package conatus.domain.history;

import conatus.domain.info.Info;
import conatus.infra.AbstractEvent;
import lombok.Data;

@Data
public class GroupDetailShown extends AbstractEvent {

    private Long id;
    private Long userId;
    private Long groupId;
    private String category;

    public GroupDetailShown(Info aggregate) {
        super(aggregate);
    }

    public GroupDetailShown() {
        super();
    }
    // keep

}
