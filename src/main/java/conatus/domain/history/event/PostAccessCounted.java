package conatus.domain.history.event;

import conatus.domain.info.Info;
import conatus.infra.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostAccessCounted extends AbstractEvent {

    private Long id;
    private Long groupId;
    private Long userId;
    private Integer postAccessCount;

    public PostAccessCounted(Info aggregate) {
        super(aggregate);
    }

    public PostAccessCounted() {
        super();
    }
    // keep

}
