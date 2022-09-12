package conatus.domain.info;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import conatus.domain.info.dto.CreateGroupDto;
import conatus.domain.info.dto.InfoDto;
import conatus.domain.info.event.GroupCreated;
import conatus.domain.member.event.GroupJoined;
import conatus.domain.middle.PostMiddleService;
import conatus.domain.middle.Url;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/group")
@Transactional
public class InfoController {

    private final InfoRepository groupRepository;
    private final InfoService infoService;
    private final PostMiddleService postMiddleService;

    @ApiOperation(value = "그룹 디테일")
    @GetMapping("/{groupId}")
    public InfoDto getById(@RequestHeader(value="Authorization") Long userId, @PathVariable Long groupId) {
        return infoService.getById(userId, groupId);
    }

    @ApiOperation(value = "그룹 검색")
    @PostMapping("/search")
    public List<InfoDto> searchInfo(@RequestHeader(value="Authorization") Long userId, @RequestParam("keyword") String keyword) {
        List<InfoDto> results = infoService.search(userId, keyword);
        return results;
    }

    @ApiOperation(value = "그룹 생성")
    @PostMapping("/create")
    public InfoDto create(@RequestHeader(value="Authorization") Long userId,
                                 @RequestBody CreateGroupDto createGroupDto) {
            InfoDto newInfo = infoService.create(userId, createGroupDto);

            // 채팅방 생성하기
            // Middle 서버로 http request
            GroupCreated groupCreated = new GroupCreated(
                    newInfo.getGroupId(),
                    newInfo.getLeaderId(),
                    newInfo.getCategory(),
                    newInfo.getName()
            );
            postMiddleService.sendTo(Url.MIDDLE.getUrl() + "/GroupCreated", groupCreated);


            return newInfo;



    }

    @ApiOperation(value = "내가 속한 그룹 보기")
    @GetMapping("/my")
    public List<Info> getGroupByUserId(@RequestHeader(value="Authorization") Long userId) {

        return infoService.getGroupByUserId(userId);

    }


}
