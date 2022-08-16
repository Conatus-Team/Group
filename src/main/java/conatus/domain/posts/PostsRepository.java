package conatus.domain.posts;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "post", path = "post")
public interface PostsRepository extends PagingAndSortingRepository<Posts, Long> {
}
