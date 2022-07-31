package conatus.domain.recommend.event;

import conatus.domain.*;
import conatus.domain.recommend.Recommend;
import conatus.domain.user.User;
import conatus.infra.AbstractEvent;
import java.util.Date;
import java.util.Map;
import lombok.Data;

@Data
public class GroupRecommended extends AbstractEvent {

    private Long id;
    private Long userId;
    private Long groupId;
    // keep

    // 이벤트를 엔티티로 만들기
    public Recommend toEntity(){
        return Recommend.builder()
                .userId(userId)
                .groupId(groupId)
                .build();
    }
}
