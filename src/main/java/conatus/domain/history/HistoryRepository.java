package conatus.domain.history;

import conatus.domain.history.History;
import conatus.domain.info.Info;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "history", path = "history")
public interface HistoryRepository
        extends PagingAndSortingRepository<History, Long> {}
