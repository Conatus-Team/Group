package conatus.domain.info;

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

    @GetMapping("/{id}")
    public InfoDto getById(@PathVariable Long id) {
        return infoService.getById(id);
    }


}
