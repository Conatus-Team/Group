package conatus.domain.gallery;


import conatus.domain.BaseTimeEntity;
import conatus.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Gallery extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long groupId;
    private String ImgUrl;

    @ManyToOne()
    private User user;
    private String author;
    private String title;


//    @Builder
//    public Member(Long userId, Long groupId, Long postId, String keyword, String category){
//        this.userId = userId;
//        this.groupId = groupId;
//        this.postId = postId;
//        this.keyword = keyword;
//        this.category = category;
//    }

}
