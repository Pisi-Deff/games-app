package ee.eerikmagi.experiments.games_app.api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.GameReview;

public interface IGameReviewService {
	Page<GameReview> list(long gameId, Pageable pageable);
	GameReview add(Dude currentDude, long gameId, GameReview gr);
	void delete(Dude currentDude, long reviewId);
	GameReview getByGameIdAndDudeId(long gameId, long dudeId);
}
