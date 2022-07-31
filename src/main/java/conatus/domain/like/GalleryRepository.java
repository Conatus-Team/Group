package conatus.domain.like;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "gallery", path = "gallery")
public interface GalleryRepository
        extends PagingAndSortingRepository<Like, Long> {}
