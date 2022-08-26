package conatus.domain.history;


import conatus.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class History extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isDeleted = Boolean.FALSE;
    private Long userId = (long) 0;
    private Long groupId = (long) 0;
    private Integer count = 1;
    private Long postId = (long) 0;
    private String keyword = "";
    private String category = "";
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



    // 그룹 자세히 보기 클릭
    @Builder
    public History(Long userId, Long groupId, String category){
        this.userId = userId;
        this.groupId = groupId;
        this.postId = (long) 0;
        this.keyword = "";
        this.category = category;
        this.isLiked = false;
        this.isClicked = false;
    }


    // 그룹 게시물 클릭
    @Builder
    public History(Long userId, Long groupId, Long postId){
        this.userId = userId;
        this.groupId = groupId;
        this.postId = postId;
        this.keyword = "";
        this.category = "";
        this.isLiked = false;
        this.isClicked = false;
    }



    // 그룹 검색
    @Builder
    public History(Long userId, String keyword){
        this.userId = userId;
        this.groupId = (long) 0;
        this.postId = (long) 0;
        this.keyword = keyword;
        this.category = "";
        this.isLiked = false;
        this.isClicked = false;
    }

    public void updateCount(){
        this.count += 1;
    }
}
