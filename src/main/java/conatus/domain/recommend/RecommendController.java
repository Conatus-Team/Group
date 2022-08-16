package conatus.domain.recommend;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import conatus.domain.info.dto.InfoDto;

@RequiredArgsConstructor
@RestController
public class RecommendController {

    private final RecommendService recommendService;
//
//    @PostMapping("/api/v1/posts")
//    public Long save(@RequestBody PostsSaveRequestDto requestDto){
//        return postsService.save(requestDto);
//    }
//
    @GetMapping("/group/recommend")
    public List<InfoDto> getByUserId(@PathVariable Long userId){
        return recommendService.getInfoByUserId(userId);
    }
//    @GetMapping("/api/v1/posts/{id}")
//    public PostsResponseDto findById(@PathVariable Long id){
//        return postsService.findById(id);
//    }
}
