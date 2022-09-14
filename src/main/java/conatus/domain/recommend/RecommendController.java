package conatus.domain.recommend;

import conatus.domain.recommend.dto.RecommendedItemDto;
import conatus.domain.recommend.dto.RecommendedItemListDto;
import conatus.domain.recommend.event.GroupRecommendedList;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.*;

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
    public List<InfoDto> getByUserId(@RequestHeader(value="Authorization") Long userId){
        return recommendService.getInfoByUserId(userId);
    }

    @PostMapping("/group/recommend")
    public RecommendedItemListDto getByUserId(@RequestBody RecommendedItemListDto dto ){
        System.out.println(dto);
        System.out.println(dto.toString());
        recommendService.saveRecommendedGroupList(dto);
        return dto;
    }
//    @GetMapping("/api/v1/posts/{id}")
//    public PostsResponseDto findById(@PathVariable Long id){
//        return postsService.findById(id);
//    }
}
