package conatus.domain.history;

import conatus.domain.info.Info;
import conatus.infra.AbstractEvent;
import lombok.Data;

@Data
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
