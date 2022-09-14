package conatus.domain.recommend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RecommendedItemListDto {
    List<RecommendedItemDto> data;
}
