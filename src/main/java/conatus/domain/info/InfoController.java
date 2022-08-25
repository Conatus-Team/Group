package conatus.domain.info;

import java.util.List;

import javax.transaction.Transactional;

import conatus.domain.info.dto.CreateGroupDto;
import conatus.domain.info.dto.InfoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/group")
@Transactional
public class InfoController {

    private final InfoRepository groupRepository;
    private final InfoService infoService;

    @ApiOperation(value = "그룹 디테일")
    @GetMapping("/{id}")
    public InfoDto getById(@PathVariable Long id) {
        return infoService.getById(id);
    }

    @ApiOperation(value = "그룹 검색")
    @PostMapping("/search")
    public List<InfoDto> searchInfo(String keyword) {
        return infoService.search(keyword);
    }

    @ApiOperation(value = "그룹 생성")
    @PostMapping("/create")
    public InfoDto create(@RequestHeader(value="userId") Long userId,
                                 @RequestBody CreateGroupDto createGroupDto) {

            return infoService.create(userId, createGroupDto);

    }
}
