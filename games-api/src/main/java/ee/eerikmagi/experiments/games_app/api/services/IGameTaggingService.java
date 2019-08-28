package ee.eerikmagi.experiments.games_app.api.services;

import java.util.List;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.GameTagging;

public interface IGameTaggingService {
	GameTagging get(long id);
	GameTagging add(Dude dude, long gameId, String tagName);
	void delete(Dude dude, long id);

	List<GameTagging> list(Dude dude);
	List<GameTagging> list(Dude dude, Long gameId);
}
