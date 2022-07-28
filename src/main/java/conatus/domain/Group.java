package conatus.domain;

import conatus.GroupApplication;
import conatus.domain.GroupDetailShown;
import conatus.domain.GroupJoined;
import conatus.domain.GroupQuitted;
import conatus.domain.GroupSearched;
import conatus.domain.PostAccessCounted;
import conatus.domain.RecommendedGroupUpdated;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Group_table")
@Data
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long groupId;

    private Long memberCount;

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

    public static GroupRepository repository() {
        GroupRepository groupRepository = GroupApplication.applicationContext.getBean(
            GroupRepository.class
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
