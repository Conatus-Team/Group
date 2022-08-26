package conatus.domain.posts;

import conatus.domain.posts.dto.PostsResponseDto;
import conatus.domain.posts.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/group/post/create")
    public Long save(@RequestHeader Long userId, @RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(userId, requestDto);
    }

    @PutMapping("/group/post/{id}")
    public Long update(@RequestHeader Long userId, @PathVariable Long id, @RequestBody PostsSaveRequestDto requestDto){
        return postsService.update(userId, id, requestDto);
    }
    @GetMapping("/group/post/{id}")
    public PostsResponseDto findById(@RequestHeader Long userId, @PathVariable Long id){
//        Long userId = (long) 1;
        return postsService.findById(userId, id);
    }
}
