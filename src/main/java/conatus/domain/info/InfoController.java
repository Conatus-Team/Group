package conatus.domain.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import conatus.domain.info.Info;
import conatus.domain.info.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/groups")
@Transactional
public class InfoController {

    @Autowired
    InfoRepository groupRepository;

    @RequestMapping(
        value = "/joingroup",
        method = RequestMethod.GET,
        produces = "application/json;charset=UTF-8"
    )
    public Info joinGroup(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody Info info
    ) throws Exception {
        System.out.println("##### /group/joinGroup  called #####");
        info.joinGroup();
        groupRepository.save(info);
        return info;
    }

    @RequestMapping(
        value = "/quitgroup",
        method = RequestMethod.GET,
        produces = "application/json;charset=UTF-8"
    )
    public Info quitGroup(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody Info info
    ) throws Exception {
        System.out.println("##### /group/quitGroup  called #####");
        info.quitGroup();
        groupRepository.save(info);
        return info;
    }
    // keep
}
