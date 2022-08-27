package conatus.domain.posts;

import conatus.domain.posts.dto.PostsResponseDto;
import conatus.domain.posts.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/group/post/create")
    public Long save(@RequestHeader(value="Authorization") Long userId, @RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(userId, requestDto);
    }

    @PutMapping("/group/post/{id}")
    public Long update(@RequestHeader(value="Authorization") Long userId, @PathVariable Long id, @RequestBody PostsSaveRequestDto requestDto){
        return postsService.update(userId, id, requestDto);
    }
    @GetMapping("/group/post/{id}")
    public PostsResponseDto findById(@RequestHeader(value="Authorization") Long userId, @PathVariable Long id){
//        Long userId = (long) 1;
        return postsService.findById(userId, id);
    }

    // 해당 그룹의 모든 게시물 보기
    @GetMapping("/group/{groupId}/post")
    public List<PostsResponseDto> getAllPosts(@RequestHeader(value="Authorization") Long userId, @PathVariable Long groupId){
        //TODO: 그룹에 가입한 유저만 조회 가능

        List<PostsResponseDto> results = postsService.findAllPosts(groupId);
        return results;

    }
}
