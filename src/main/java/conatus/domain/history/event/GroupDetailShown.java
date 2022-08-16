package conatus.domain.history.event;

import conatus.domain.info.Info;
import conatus.infra.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
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
