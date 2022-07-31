package conatus.domain.like;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "like", path = "like")
public interface LikeRepository
        extends PagingAndSortingRepository<Like, Long> {}
