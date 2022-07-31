package conatus.domain.member;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "history", path = "history")
public interface MemberRepository
        extends PagingAndSortingRepository<Member, Long> {}
