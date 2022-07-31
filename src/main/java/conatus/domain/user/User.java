package conatus.domain.user;


import conatus.domain.BaseTimeEntity;
import conatus.domain.gallery.Gallery;
import conatus.domain.like.Like;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isDeleted = Boolean.FALSE;
    private Long userId;
    private String nickname;

    @OneToMany(mappedBy = "user")
    private List<Like> likeList;

//    @OneToMany(mappedBy = "user")
//    private List<Gallery> galleryList;

    @Builder
    public User(Long userId, String nickname){
        this.userId = userId;
        this.nickname = nickname;
    }

}
