package conatus.domain.recommend;

import conatus.domain.recommend.event.GroupRecommended;
import conatus.domain.recommend.event.GroupRecommendedList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecommendService {
    private final RecommendRepository recommendRepository;

    // kafka
    // 추천받은 그룹(단일) 저장
    public Recommend updateRecommendedGroup(GroupRecommended groupRecommended){
        return recommendRepository.save(groupRecommended.toEntity());
    }

    // kafka
    // 추천받은 그룹 리스트 저장
    public void updateRecommendedGroupList(GroupRecommendedList groupRecommendedList){
        groupRecommendedList.getGroupRecommendedList().stream().map(x-> recommendRepository.save(x.toEntity()));

    }
}
