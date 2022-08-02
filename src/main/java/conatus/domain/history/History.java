package conatus.domain.history;


import conatus.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class History extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isDeleted = Boolean.FALSE;
    private Long userId;
    private Long groupId = (long) 0;
    private Integer count = 1;
    private Long postId = (long) 0;
    private String keyword = "";
    private String category;
    private Boolean isLiked = Boolean.FALSE;
    private Boolean isClicked = Boolean.FALSE;

    @Builder
    public History(Long userId, Long groupId, Long postId, String keyword, String category, Boolean isLiked, Boolean isClicked){
        this.userId = userId;
        this.groupId = groupId;
        this.postId = postId;
        this.keyword = keyword;
        this.category = category;
        this.isLiked = isLiked;
        this.isClicked = isClicked;
    }

    public void updateCount(){
        this.count += 1;
    }
}
