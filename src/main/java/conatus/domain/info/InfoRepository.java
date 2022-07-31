package conatus.domain.info;

import conatus.domain.info.Info;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "groups", path = "groups")
public interface InfoRepository
    extends PagingAndSortingRepository<Info, Long> {}