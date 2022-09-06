package conatus.domain.info.dto;

import conatus.domain.info.Info;
import lombok.Data;

import javax.persistence.Column;

@Data
public class InfoDto {
    private Long groupId;
    // groupId와 같음. 삭제하면 안됨(프론트 오류생김)
    private Long id;
    private String name;
    private Long leaderId;
    private String explanation;
    private String category;
    private Integer memberCount;
    private Integer likeCount;
    private String thumbnail;

    public InfoDto(Info info){
        this.groupId = info.getId();
        this.id = info.getId();
        this.name = info.getName();
        this.leaderId = info.getLeaderId();
        this.explanation = info.getExplanation();
        this.category = info.getCategory();
        this.memberCount = info.getMemberCount();
        this.likeCount = info.getLikeCount();
        this.thumbnail = info.getThumbnail();

    }
}
