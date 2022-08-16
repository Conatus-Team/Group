package conatus.domain.like;


import conatus.domain.BaseTimeEntity;
import conatus.domain.user.User;
import conatus.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "likes")
public class Like extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long groupId;
//    private Long userId;

    @ManyToOne()
    private User user;



    @Builder
    public Like(Long groupId, User user){

        this.user = user;
        this.groupId = groupId;

    }

}
