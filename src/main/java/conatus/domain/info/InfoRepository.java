package conatus.domain.info;

import conatus.domain.info.Info;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "info", path = "info")
public interface InfoRepository
    extends PagingAndSortingRepository<Info, Long> {
	
//	https://stackoverflow.com/questions/47785284/how-to-find-multiple-like-in-jparepository-spring
	List<Info> findByNameContainingOrCategoryContaining(String keyword, String keyword2);



	
}
