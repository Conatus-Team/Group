package conatus.domain.recommend.event;

import conatus.domain.*;
import conatus.domain.info.Info;
import conatus.domain.info.InfoRepository;
import conatus.domain.recommend.Recommend;
import conatus.domain.user.User;
import conatus.infra.AbstractEvent;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class GroupRecommended extends AbstractEvent {

    private Long id;
    private Long userId;
    private Long groupId;
    
    
//    // DB 테이블에 적용 안 됨
//    @Transient
//    private final InfoRepository infoRepository;
    
//    @Builder
//    public GroupRecommended(Long id, Long userId, Long groupId){
//    	this.id = id;
//    	this.userId = userId;
//    	this.groupId = groupId;
//    }
//    public GroupRecommended(Long id, Long userId, Long groupId) {
//    	this.id = id;
//    	this.userId = userId;
//    	this.groupId = groupId;
//    }
    
    // 이벤트를 엔티티로 만들기
    public Recommend toEntity(){
        return Recommend.builder()
                .userId(userId)
//                .info(infoRepository.findById(groupId).get())
                .build();
    }
}
