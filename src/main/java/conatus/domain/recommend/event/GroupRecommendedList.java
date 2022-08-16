package conatus.domain.recommend.event;

import conatus.domain.recommend.Recommend;
import conatus.infra.AbstractEvent;
import lombok.Data;

import java.util.List;
@Data
public class GroupRecommendedList extends AbstractEvent {
    private List<GroupRecommended> groupRecommendedList;

}
