package conatus.domain.posts.dto;


import conatus.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsUpdateRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    //database와 맞닿은 class 이를 기준으로 테이블이 생성되고, 스키마가 변경됨
    //꼭 Entity class와 Controller의 Dto는 분리해서 쓸 것.
    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
