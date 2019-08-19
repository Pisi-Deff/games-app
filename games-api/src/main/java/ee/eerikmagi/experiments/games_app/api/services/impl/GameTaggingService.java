package ee.eerikmagi.experiments.games_app.api.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.Game;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.GameTagging;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.Tag;
import ee.eerikmagi.experiments.games_app.api.persistence.repos.IDudeRepository;
import ee.eerikmagi.experiments.games_app.api.persistence.repos.IGameRepository;
import ee.eerikmagi.experiments.games_app.api.persistence.repos.IGameTaggingRepository;
import ee.eerikmagi.experiments.games_app.api.services.IGameTaggingService;
import ee.eerikmagi.experiments.games_app.api.services.ITagService;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class GameTaggingService implements IGameTaggingService {
	private IGameRepository gameRep;

	private IGameTaggingRepository gameTaggingRep;

	private IDudeRepository dudeRep;

	private ITagService tagSvc;

	@Override
	public GameTagging get(long id) {
		return gameTaggingRep.getOne(id);
	}

	@Override
	public GameTagging add(String dudemail, long gameId, String tagName) {
		Tag t = tagSvc.getOrCreateTag(tagName);
		Game g = gameRep.getOne(gameId);
		Dude d = dudeRep.findByEmailIgnoreCase(dudemail);

		GameTagging gt = new GameTagging();
		gt.setTag(t);
		gt.setGame(g);
		gt.setDude(d);

		return gameTaggingRep.save(gt);
	}

	@Override
	public void delete(String dudemail, long id) {
		GameTagging gt = get(id);
		Dude d = dudeRep.findByEmailIgnoreCase(dudemail);
		if (gt.getDude().equals(d)) {
			gameTaggingRep.delete(gt);
		}
	}

	@Override
	public List<GameTagging> list(String dudemail) {
		return gameTaggingRep.getByDude(dudeRep.findByEmailIgnoreCase(dudemail));
	}

	@Override
	public List<GameTagging> list(String dudemail, Long gameId) {
		return gameTaggingRep.getByDudeAndGameId(dudeRep.findByEmailIgnoreCase(dudemail), gameId);
	}
}
