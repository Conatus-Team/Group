package conatus.domain.info;

import conatus.GroupApplication;

import javax.persistence.*;

import conatus.domain.history.event.GroupDetailShown;
import conatus.domain.history.event.GroupSearched;
import conatus.domain.history.event.PostAccessCounted;
import conatus.domain.member.event.GroupJoined;
import conatus.domain.member.event.GroupQuitted;
import conatus.domain.recommend.event.GroupRecommended;
import conatus.domain.recommend.event.RecommendedGroupUpdated;
import lombok.Data;

@Entity
@Table(name = "info")
@Data
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long groupId;

    private Integer memberCount;

    private String category;

    @PostPersist
    public void onPostPersist() {
        GroupJoined groupJoined = new GroupJoined(this);
        groupJoined.publishAfterCommit();

        GroupQuitted groupQuitted = new GroupQuitted(this);
        groupQuitted.publishAfterCommit();

        PostAccessCounted postAccessCounted = new PostAccessCounted(this);
        postAccessCounted.publishAfterCommit();

        GroupSearched groupSearched = new GroupSearched(this);
        groupSearched.publishAfterCommit();

        GroupDetailShown groupDetailShown = new GroupDetailShown(this);
        groupDetailShown.publishAfterCommit();

        RecommendedGroupUpdated recommendedGroupUpdated = new RecommendedGroupUpdated(
            this
        );
        recommendedGroupUpdated.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    @PreRemove
    public void onPreRemove() {}

    public static InfoRepository repository() {
        InfoRepository groupRepository = GroupApplication.applicationContext.getBean(
            InfoRepository.class
        );
        return groupRepository;
    }

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
