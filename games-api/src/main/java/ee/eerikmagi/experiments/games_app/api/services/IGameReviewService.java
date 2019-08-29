package ee.eerikmagi.experiments.games_app.api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.GameReview;

public interface IGameReviewService {
	Page<GameReview> getByGameId(long gameId, Pageable pageable);
	Page<GameReview> getByDudeId(long dudeId, Pageable pageable);
	GameReview getByGameIdAndDudeId(long gameId, long dudeId);

	GameReview add(Dude currentDude, long gameId, GameReview gr);
	void delete(Dude currentDude, long reviewId);
}
