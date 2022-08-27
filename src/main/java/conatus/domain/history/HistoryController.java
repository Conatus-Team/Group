package conatus.domain.history;

import conatus.domain.info.InfoRepository;
import conatus.domain.info.InfoService;
import conatus.domain.info.dto.InfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/group")
@Transactional
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("/send")
    public void sendToRecommendSystem() {
        historyService.publishEvent();
    }


}
