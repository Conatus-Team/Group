package conatus.domain.recommend;


import conatus.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Recommend extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isDeleted = Boolean.FALSE;
    private Long userId;
    private Long groupId;

    @Builder
    public Recommend(Long userId, Long groupId){
        this.userId = userId;
        this.groupId = groupId;
    }

}
