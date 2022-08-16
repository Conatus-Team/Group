package conatus.domain.recommend;


import conatus.domain.BaseTimeEntity;
import conatus.domain.info.Info;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Recommend extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isDeleted = Boolean.FALSE;
    private Long userId;
    @ManyToOne
    private Info info;

    @Builder
    public Recommend(Long userId, Info info){
        this.userId = userId;
        this.info = info;
    }

}
