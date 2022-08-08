package conatus.domain.history;

import conatus.domain.history.History;
import conatus.domain.info.Info;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "history", path = "history")
public interface HistoryRepository
        extends PagingAndSortingRepository<History, Long> {

    List<History> findByUserId(Long userId);

    List<History> findByUserIdAndUpdatedTimeBetween(Long userId, LocalDateTime start, LocalDateTime end);

    List<History> findByIdGreaterThan(Long sentId);
    
    Optional<History> findByUserIdAndKeyword(Long userId, String keyword);
}
