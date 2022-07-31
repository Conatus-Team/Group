package conatus.domain.member;


import conatus.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isDeleted = Boolean.FALSE;
    private Long userId;
    private String nickname;
    private Boolean isLeader = Boolean.FALSE;
    private Long groupId;


//    @Builder
//    public Member(Long userId, Long groupId, Long postId, String keyword, String category){
//        this.userId = userId;
//        this.groupId = groupId;
//        this.postId = postId;
//        this.keyword = keyword;
//        this.category = category;
//    }

}
