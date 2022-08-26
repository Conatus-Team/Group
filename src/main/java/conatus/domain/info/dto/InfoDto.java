package conatus.domain.info.dto;

import conatus.domain.info.Info;
import lombok.Data;

@Data
public class InfoDto {
    private Long groupId;
    private String name;
    private Long leaderId;
    private String explanation;
    private String category;
    private Integer memberCount;
    private Integer likeCount;
    private String thumbnail;

    public InfoDto(Info info){
        this.groupId = info.getId();
        this.name = info.getName();
        this.leaderId = info.getLeaderId();
        this.explanation = info.getExplanation();
        this.category = info.getCategory();
        this.memberCount = info.getMemberCount();
        this.likeCount = info.getLikeCount();
        this.thumbnail = info.getThumbnail();

    }
}
