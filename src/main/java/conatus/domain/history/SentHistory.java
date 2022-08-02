package conatus.domain.history;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class SentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    // 해당 아이디까지 보냈음
    Long sent;
    // 여기부터 새로 보내야 할 아이디
    Long toSend;
}
