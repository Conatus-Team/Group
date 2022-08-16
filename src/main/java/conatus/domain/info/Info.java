package conatus.domain.info;

import javax.persistence.*;

import conatus.domain.BaseTimeEntity;
import conatus.domain.recommend.event.GroupRecommended;
import conatus.domain.user.User;
import lombok.*;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Setter
@Entity
@Table(name = "info")
public class Info extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Long leaderId;
    @Column(columnDefinition = "TEXT")
    private String explanation;
    private String category;
    private Integer memberCount = 1;
    private Integer likeCount = 0;

    private String thumbnail;

    
    @Builder
    public Info(String name, Long leaderId, String explanation, String category, String thumbnail){
        this.name = name;
        this.leaderId = leaderId;
        this.explanation = explanation;
        this.category = category;
        this.thumbnail = thumbnail;
    }
    
    @PostPersist
    public void onPostPersist() {
//        GroupJoined groupJoined = new GroupJoined(this);
//        groupJoined.publishAfterCommit();
//
//        GroupQuitted groupQuitted = new GroupQuitted(this);
//        groupQuitted.publishAfterCommit();
//
//        PostAccessCounted postAccessCounted = new PostAccessCounted(this);
//        postAccessCounted.publishAfterCommit();
//
//        GroupSearched groupSearched = new GroupSearched(this);
//        groupSearched.publishAfterCommit();
//
//        GroupDetailShown groupDetailShown = new GroupDetailShown(this);
//        groupDetailShown.publishAfterCommit();
//
//        RecommendedGroupUpdated recommendedGroupUpdated = new RecommendedGroupUpdated(
//            this
//        );
//        recommendedGroupUpdated.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    @PreRemove
    public void onPreRemove() {}

//    public static InfoRepository repository() {
//        InfoRepository groupRepository = GroupApplication.applicationContext.getBean(
//            InfoRepository.class
//        );
//        return groupRepository;
//    }

    public void joinGroup() {}

    public void quitGroup() {}

    public static void updateRecommendedGroup(
        GroupRecommended groupRecommended
    ) {
        /** Example 1:  new item 
        Group group = new Group();
        repository().save(group);

        */

        /** Example 2:  finding and process
        
        repository().findById(groupRecommended.get???()).ifPresent(group->{
            
            group // do something
            repository().save(group);


         });
        */

    }
}
