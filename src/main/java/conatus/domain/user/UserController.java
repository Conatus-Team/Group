package conatus.domain.user;

import conatus.domain.user.event.SignedUp;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

//    private final PostsService postsService;
//    @PostMapping("/api/v1/posts")
//    public Long save(@RequestBody PostsSaveRequestDto requestDto){
//        return postsService.save(requestDto);
//    }
//
//    @PutMapping("/api/v1/posts/{id}")
//    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
//        return postsService.update(id, requestDto);
//    }
//    @GetMapping("/api/v1/posts/{id}")
//    public PostsResponseDto findById(@PathVariable Long id){
//        return postsService.findById(id);
//    }
}
