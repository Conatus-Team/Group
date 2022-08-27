package conatus.domain.member.event;

import conatus.domain.info.Info;
import conatus.infra.AbstractEvent;
import lombok.Data;

@Data
public class GroupQuitted extends AbstractEvent {

    private Long id;
    private Long groupId;
    private Long userId;

    public GroupQuitted(Info aggregate) {
        super(aggregate);
    }

    public GroupQuitted() {
        super();
    }
    // keep

}
