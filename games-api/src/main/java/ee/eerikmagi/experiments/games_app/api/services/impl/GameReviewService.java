package ee.eerikmagi.experiments.games_app.api.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.GameReview;
import ee.eerikmagi.experiments.games_app.api.persistence.repos.IGameReviewRepository;
import ee.eerikmagi.experiments.games_app.api.services.IGameReviewService;
import ee.eerikmagi.experiments.games_app.api.services.IGameService;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class GameReviewService implements IGameReviewService {
	private IGameReviewRepository gameReviewRep;
	private IGameService gameSvc;

	@Override
	public Page<GameReview> list(long gameId, Pageable pageable) {
		return gameReviewRep.getByGameId(gameId, pageable);
	}

	@Override
	public GameReview add(Dude dude, long gameId, GameReview gr) {
		gr.setDude(dude);
		gr.setGame(gameSvc.get(gameId));
		return gameReviewRep.saveAndFlush(gr);
	}

	@Override
	public void delete(Dude dude, long id) {
		GameReview gr = gameReviewRep.getOne(id);
		if (gr.getDude().equals(dude)) {
			gameReviewRep.delete(gr);
		}
	}

	@Override
	public GameReview getByGameIdAndDudeId(long gameId, long dudeId) {
		return gameReviewRep.getByGameIdAndDudeId(gameId, dudeId);
	}
}
