package conatus.infra;

import conatus.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/groups")
@Transactional
public class GroupController {

    @Autowired
    GroupRepository groupRepository;

    @RequestMapping(
        value = "/joingroup",
        method = RequestMethod.GET,
        produces = "application/json;charset=UTF-8"
    )
    public Group joinGroup(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody Group group
    ) throws Exception {
        System.out.println("##### /group/joinGroup  called #####");
        group.joinGroup();
        groupRepository.save(group);
        return group;
    }

    @RequestMapping(
        value = "/quitgroup",
        method = RequestMethod.GET,
        produces = "application/json;charset=UTF-8"
    )
    public Group quitGroup(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody Group group
    ) throws Exception {
        System.out.println("##### /group/quitGroup  called #####");
        group.quitGroup();
        groupRepository.save(group);
        return group;
    }
    // keep
}
