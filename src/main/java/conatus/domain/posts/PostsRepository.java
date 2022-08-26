package conatus.domain.posts;

import conatus.domain.history.History;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "post", path = "post")
public interface PostsRepository extends PagingAndSortingRepository<Posts, Long> {

}
