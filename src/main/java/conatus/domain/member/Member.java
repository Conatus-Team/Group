package conatus.domain.member;


import conatus.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
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


    @Builder
    public Member(Long groupId, Long userId,  String nickname, Boolean isLeader){
        this.userId = userId;
        this.groupId = groupId;
        this.nickname = nickname;
        this.isLeader = isLeader;
    }

}
