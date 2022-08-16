package conatus.domain.recommend;

import conatus.domain.info.dto.InfoDto;
import conatus.domain.recommend.event.GroupRecommended;
import conatus.domain.recommend.event.GroupRecommendedList;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecommendService {
	private final RecommendRepository recommendRepository;

	// kafka
	// 추천받은 그룹(단일) 저장
	public Recommend updateRecommendedGroup(GroupRecommended groupRecommended) {
		return recommendRepository.save(groupRecommended.toEntity());
	}

	// kafka
	// 추천받은 그룹 리스트 저장
	public void updateRecommendedGroupList(GroupRecommendedList groupRecommendedList) {
		groupRecommendedList.getGroupRecommendedList().stream().map(x -> recommendRepository.save(x.toEntity()));

	}

	// 추천받은 그룹 리스트 보여주기
	public List<InfoDto> getInfoByUserId(Long userId) {
		List<Recommend> recommend = recommendRepository.findByUserId(userId);
		List<InfoDto> info = new Vector<InfoDto>();
		if (!recommend.isEmpty()) {
			info = recommend.stream().map(x -> new InfoDto(x.getInfo())).collect(Collectors.toList());
		}
		return info;

	}
}
