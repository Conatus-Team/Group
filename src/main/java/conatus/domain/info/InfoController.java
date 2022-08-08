package conatus.domain.info;

import java.util.List;

import javax.transaction.Transactional;

import conatus.domain.info.dto.InfoDto;
import conatus.domain.like.dto.LikeDto;
import conatus.domain.member.MemberService;
import conatus.domain.posts.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/group")
@Transactional
public class InfoController {

    private final InfoRepository groupRepository;
    private final InfoService infoService;

    @GetMapping("/{id}")
    public InfoDto getById(@PathVariable Long id) {
        return infoService.getById(id);
    }
    
    @PostMapping("/search")
    public List<InfoDto> searchInfo(String keyword) {
        return infoService.search(keyword);
    }

//    @RequestMapping(
//        value = "/joingroup",
//        method = RequestMethod.GET,
//        produces = "application/json;charset=UTF-8"
//    )
//    public Info joinGroup(
//        HttpServletRequest request,
//        HttpServletResponse response,
//        @RequestBody Info info
//    ) throws Exception {
//        System.out.println("##### /group/joinGroup  called #####");
//        info.joinGroup();
//        groupRepository.save(info);
//        return info;
//    }
//
//    @RequestMapping(
//        value = "/quitgroup",
//        method = RequestMethod.GET,
//        produces = "application/json;charset=UTF-8"
//    )
//    public Info quitGroup(
//        HttpServletRequest request,
//        HttpServletResponse response,
//        @RequestBody Info info
//    ) throws Exception {
//        System.out.println("##### /group/quitGroup  called #####");
//        info.quitGroup();
//        groupRepository.save(info);
//        return info;
//    }
//    // keep
}
