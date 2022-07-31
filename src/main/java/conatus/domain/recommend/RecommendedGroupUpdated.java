package conatus.domain.recommend;

import conatus.domain.info.Info;
import conatus.infra.AbstractEvent;
import lombok.Data;

@Data
public class RecommendedGroupUpdated extends AbstractEvent {

    private Long id;

    public RecommendedGroupUpdated(Info aggregate) {
        super(aggregate);
    }

    public RecommendedGroupUpdated() {
        super();
    }
    // keep

}
