package conatus.domain.recommend;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "recommend", path = "recommend")
public interface RecommendRepository
        extends PagingAndSortingRepository<Recommend, Long> {}
