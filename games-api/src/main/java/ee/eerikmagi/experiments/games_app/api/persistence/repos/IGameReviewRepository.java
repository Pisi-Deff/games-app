package ee.eerikmagi.experiments.games_app.api.persistence.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.GameReview;

public interface IGameReviewRepository extends JpaRepository<GameReview, Long> {
	Page<GameReview> getByGameId(long gameId, Pageable pageable);
}
