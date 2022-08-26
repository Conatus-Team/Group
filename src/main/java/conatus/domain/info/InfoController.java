package conatus.domain.info;

import java.util.List;

import javax.transaction.Transactional;

import conatus.domain.info.dto.InfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/group")
@Transactional
public class InfoController {

    private final InfoRepository groupRepository;
    private final InfoService infoService;


    @GetMapping("/{groupId}")
    public InfoDto getById(@RequestHeader Long userId, @PathVariable Long groupId) {
        return infoService.getById(userId, groupId);



    }
    
    @PostMapping("/search")
    public List<InfoDto> searchInfo(@RequestHeader Long userId, String keyword) {
        return infoService.search(userId, keyword);
    }


}
