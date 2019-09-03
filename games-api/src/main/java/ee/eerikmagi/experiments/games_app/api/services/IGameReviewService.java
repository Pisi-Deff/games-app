package ee.eerikmagi.experiments.games_app.api.services;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.GameReview;

public interface IGameReviewService {
	Page<GameReview> getByGameId(long gameId, Pageable pageable);
	Page<GameReview> getByDudeId(long dudeId, Pageable pageable);
	GameReview getByGameIdAndDudeId(long gameId, long dudeId);

	BigDecimal getAverageReviewScore(long gameId);

	GameReview get(long id);
	GameReview add(Dude currentDude, long gameId, GameReview gr);
	GameReview update(Dude dude, GameReview gr);
	void delete(Dude currentDude, long reviewId);
}
