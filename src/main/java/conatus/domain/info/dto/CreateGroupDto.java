package conatus.domain.info.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateGroupDto {
    String thumbnail;
    String explanation;
    String name;
    String category;
}
