package conatus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import conatus.domain.history.HistoryRepository;
import conatus.domain.history.HistoryService;
import conatus.domain.info.Info;
import conatus.domain.info.InfoRepository;
import conatus.domain.info.InfoService;
import conatus.domain.info.dto.InfoDto;
import conatus.domain.like.LikeRepository;
import conatus.domain.recommend.RecommendService;
import conatus.domain.recommend.event.GroupRecommended;
import conatus.domain.user.User;
import conatus.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;



//@AutoConfigureMockMvc
//@Autowired 있는 애들만 주입 (기본 값)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ANNOTATED)
@SpringBootTest
//@RequiredArgsConstructor
class GroupApplicationTests {

	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LikeRepository likeRepository;
	@Autowired
	private HistoryRepository historyRepository;
	@Autowired
	private InfoRepository infoRepository;
	
	@Autowired
	private HistoryService historyService;
	@Autowired
	private InfoService infoService;
	@Autowired
	private RecommendService recommendService;
	
//	@Test
	public void searchHistoryTest() {
		User user = new User((long)1, "테스트 유저 닉네임");
		this.userRepository.save(user);
		
		List<InfoDto> noFoundInfo = infoService.search("키워드");
		assertEquals(0, noFoundInfo.size());
		
		Info info = new Info("그룹명_키워드", (long)1, "그룹설명", "그룹카테고리", "썸네일");
		Info info2 = new Info("그룹명_키워드2", (long)1, "그룹설명", "그룹카테고리", "썸네일");
		Info info3 = new Info("그룹명_키3워드", (long)1, "그룹설명", "그룹카테고리", "썸네일");
		Info info4 = new Info("그룹명4", (long)1, "그룹설명", "그룹카테고리_키워드", "썸네일");
		infoRepository.save(info);
		infoRepository.save(info2);
		infoRepository.save(info3);
		infoRepository.save(info4);
		
		
		List<InfoDto> foundInfo = infoService.search("키워드");
		assertEquals(3, foundInfo.size());
	}
	
//	@Test
//	public void recommendTest() {
//		User user = new User((long)2, "테스트 유저2");
//
//		// 이벤트 만들기
//		GroupRecommended re1 = new GroupRecommended((long)1, (long)2, (long) 11, infoRepository);
//		GroupRecommended re2 = new GroupRecommended((long)2, (long)2, (long) 12, infoRepository);
//
//		// 이벤트 엔티티로 저장
//		recommendService.updateRecommendedGroup(re1);
//		recommendService.updateRecommendedGroup(re2);
//
//		// 추천 받기
//		List<InfoDto> recommend = recommendService.getInfoByUserId((long)2);
//		assertEquals(2, recommend.size());
//		System.out.println(recommend);
//	}

}
