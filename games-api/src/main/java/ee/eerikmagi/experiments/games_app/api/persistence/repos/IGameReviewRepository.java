package ee.eerikmagi.experiments.games_app.api.persistence.repos;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.GameReview;

public interface IGameReviewRepository extends JpaRepository<GameReview, Long> {
	Page<GameReview> getByGameId(long gameId, Pageable pageable);
	Page<GameReview> getByDudeId(long dudeId, Pageable pageable);
	GameReview getByGameIdAndDudeId(long gameId, long dudeId);

	@Query("SELECT AVG(gt.score) FROM GameReview gt WHERE gt.game.id = :gameId")
	BigDecimal getAverageScoreByGameId(@Param("gameId") long gameId);
}
