package conatus.domain.posts.dto;


import conatus.domain.posts.Posts;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    // private String groupName;
    private LocalDateTime date;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
//        this.groupName = entity.getGroupName();
        this.date = entity.getCreatedTime();
    }
}
