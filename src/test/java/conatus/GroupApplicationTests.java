package conatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import conatus.domain.history.History;
import conatus.domain.info.InfoController;
import conatus.domain.info.dto.CreateGroupDto;
import conatus.domain.info.event.GroupCreated;
import conatus.domain.middle.PostMiddleService;
import conatus.domain.middle.Url;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


//@AutoConfigureMockMvc
//@Autowired 있는 애들만 주입 (기본 값)
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ANNOTATED)
@SpringBootTest
@Slf4j
//@WebMvcTest(InfoController.class)
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

	@Autowired
	private PostMiddleService postMiddleService;
//	@Autowired
//	private MockMvc mockMvc;
//
//	ObjectMapper mapper = new ObjectMapper();
	@Test
	public void infoCreateTest() throws Exception{

//		CreateGroupDto dto = new CreateGroupDto();
//		dto.setCategory("독서");
//		dto.setName("독서의 계절");
//		dto.setThumbnail("");
//		dto.setExplanation("다같이 독서해요");
//
//		Long userId = 62L; //vovo
//
//		InfoDto newInfo = infoService.create(userId, dto);
//
//		// 채팅방 생성하기
//		// Middle 서버로 http request
//		GroupCreated groupCreated = new GroupCreated(
//				newInfo.getGroupId(),
//				newInfo.getLeaderId(),
//				newInfo.getCategory(),
//				newInfo.getName()
//		);
//		postMiddleService.sendTo(Url.MIDDLE.getUrl() + "/GroupCreated", groupCreated);


		String[] data = {"드라마시청","뜨개질","궁술","천문학","캠핑","야구","농구","양봉","운전","정원가꾸기",
				"핸드볼","하이킹","등산","사격","골동품","동전","우표","수석","당구","체스","컬링","다트","토론",
				"바둑","체조","마작","포커","골프","수영","외국어","사진","철도","여행","춤","DIY","프로그래밍",
				"요리","십자수","십자말풀이","연기","보드게임","캘리그래피","악기연주","베이킹","재테크","회화","영화",
				"애니메이션","달리기","독서","마술","커피"};
		ArrayList<String> hobbyList = new ArrayList<>(Arrays.asList(data));

		for (String hobby:hobbyList
			 ) {
			for(int i = 0; i < 2; i++){
				CreateGroupDto dto = new CreateGroupDto();
				if (i == 0){
					dto.setCategory(hobby);
					dto.setName("다 같이 "+hobby);
					dto.setThumbnail("");
					dto.setExplanation(hobby + " 같이 해요");
				} else{
					dto.setCategory(hobby);
					dto.setName("즐거운 "+hobby);
					dto.setThumbnail("");
					dto.setExplanation("즐겁게 "+hobby+"해요");
				}


				Long userId = 62L; //vovo

				InfoDto newInfo = infoService.create(userId, dto);

				// 채팅방 생성하기
				// Middle 서버로 http request
				GroupCreated groupCreated = new GroupCreated(
						newInfo.getGroupId(),
						newInfo.getLeaderId(),
						newInfo.getCategory(),
						newInfo.getName()
				);
				postMiddleService.sendTo(Url.MIDDLE.getUrl() + "/GroupCreated", groupCreated);


			}
		}

	}



//	@Test
	public void searchHistoryTest() {
		User user = new User((long)1, "테스트 유저 닉네임", "네임");
		this.userRepository.save(user);
		
		List<InfoDto> noFoundInfo = infoService.search(user.getUserId(),"키워드");
		assertEquals(0, noFoundInfo.size());
		
		Info info = new Info("그룹명_키워드", (long)1, "그룹설명", "그룹카테고리", "썸네일");
		Info info2 = new Info("그룹명_키워드2", (long)1, "그룹설명", "그룹카테고리", "썸네일");
		Info info3 = new Info("그룹명_키3워드", (long)1, "그룹설명", "그룹카테고리", "썸네일");
		Info info4 = new Info("그룹명4", (long)1, "그룹설명", "그룹카테고리_키워드", "썸네일");
		infoRepository.save(info);
		infoRepository.save(info2);
		infoRepository.save(info3);
		infoRepository.save(info4);
		
		
		List<InfoDto> foundInfo = infoService.search(user.getUserId(), "키워드");
		assertEquals(3, foundInfo.size());
	}



//	@Test
	public void history(){
		historyService.publishAll();
	}


//	@Test
	public void logTest() {
		log.info("hello");
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
