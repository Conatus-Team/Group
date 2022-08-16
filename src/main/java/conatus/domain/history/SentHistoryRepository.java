package conatus.domain.history;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SentHistoryRepository extends JpaRepository<SentHistory, Long> {
}
