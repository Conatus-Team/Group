package conatus.domain.recommend.dto;

import conatus.domain.recommend.Recommend;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecommendedItemDto {
    Long groupId;
    Long userId;


    // 이벤트를 엔티티로 만들기
    public Recommend toEntity(){
        return Recommend.builder()
                .userId(userId)
//                .info(infoRepository.findById(groupId).get())
                .build();
    }
}
