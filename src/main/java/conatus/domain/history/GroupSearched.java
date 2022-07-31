package conatus.domain.history;

import conatus.domain.info.Info;
import conatus.infra.AbstractEvent;
import lombok.Data;

@Data
public class GroupSearched extends AbstractEvent {

    private Long id;
    private Long userId;
    private String keyword;

    public GroupSearched(Info aggregate) {
        super(aggregate);
    }

    public GroupSearched() {
        super();
    }
    // keep

}
