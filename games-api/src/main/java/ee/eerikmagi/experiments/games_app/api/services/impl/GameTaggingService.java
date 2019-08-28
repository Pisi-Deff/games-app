package ee.eerikmagi.experiments.games_app.api.services.impl;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.Game;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.GameTagging;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.Tag;
import ee.eerikmagi.experiments.games_app.api.persistence.repos.IGameRepository;
import ee.eerikmagi.experiments.games_app.api.persistence.repos.IGameTaggingRepository;
import ee.eerikmagi.experiments.games_app.api.services.IGameTaggingService;
import ee.eerikmagi.experiments.games_app.api.services.ITagService;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class GameTaggingService implements IGameTaggingService {
	private IGameRepository gameRep;
	private IGameTaggingRepository gameTaggingRep;
	private ITagService tagSvc;

	@Override
	public GameTagging get(long id) {
		return gameTaggingRep.getOne(id);
	}

	@Override
	public GameTagging add(Dude dude, long gameId, String tagName) {
		Tag t = tagSvc.getOrCreateTag(tagName);
		Game g = gameRep.getOne(gameId);

		GameTagging gt = new GameTagging();
		gt.setTag(t);
		gt.setGame(g);
		gt.setDude(dude);

		return gameTaggingRep.save(gt);
	}

	@Override
	public void delete(Dude dude, long id) {
		GameTagging gt = get(id);
		if (gt.getDude().equals(dude)) {
			gameTaggingRep.delete(gt);
		}
	}

	@Override
	public List<GameTagging> list(Dude dude) {
		return gameTaggingRep.getByDude(dude);
	}

	@Override
	public List<GameTagging> list(Dude dude, Long gameId) {
		return gameTaggingRep.getByDudeAndGameId(dude, gameId);
	}
}
