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

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne
    private User user;

    private String author = user.getNickname();

    private Integer Like = 0;

    @Builder
    public Posts(String title, String content, User user, String author){
        this.title = title;
        this.content = content;
        this.user = user;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
