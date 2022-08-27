package conatus.domain.member;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "member", path = "member")
public interface MemberRepository
        extends PagingAndSortingRepository<Member, Long> {

    Optional<Member> findByGroupIdAndUserId(Long groupId, Long userId);
    List<Member> findByUserId(Long userId);
}
