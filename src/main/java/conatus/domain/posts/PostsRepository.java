package conatus.domain.posts;

import conatus.domain.history.History;
import conatus.domain.posts.dto.PostsResponseDto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "post", path = "post")
public interface PostsRepository extends PagingAndSortingRepository<Posts, Long> {
    List<Posts> findByGroupId(Long groupId);
}
