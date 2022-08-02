package conatus.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JoinDto {
    private Long groupId;
    private Long userId;
}
