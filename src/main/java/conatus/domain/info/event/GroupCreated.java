package conatus.domain.info.event;

import conatus.domain.info.Info;
import conatus.infra.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupCreated extends AbstractEvent {
    Long groupId;
    Long userId;
    String Category;
    String name;



}
