package conatus.domain.user.event;

import conatus.domain.user.User;
import conatus.infra.AbstractEvent;
import lombok.Data;

import java.util.Date;

@Data
public class SignedUp extends AbstractEvent {

    private Long id;
    private Long userId;
    private Boolean gender;
    private Date birth;
    private String location;
    private String nickname;

    public SignedUp(User aggregate) {
        super(aggregate);
    }

    public SignedUp() {
        super();
    }
    // keep

    // 이벤트를 엔티티로 만들기
    public User toEntity(){
        return User.builder()
                .userId(userId)
                .nickname(nickname)
                .build();
    }

}
