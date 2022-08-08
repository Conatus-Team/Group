package conatus.domain.posts;


import conatus.domain.BaseTimeEntity;
import conatus.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private Long userId;

    private Long groupId;

    private String author;

    private Integer likeCount = 0;

    @Builder
    public Posts(String title, String content, Long userId, String author, Long groupId){
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.author = author;
        this.groupId = groupId;

    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
