package ee.eerikmagi.experiments.games_app.api.services;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.GameTagging;

import java.util.List;

public interface IGameTaggingService {
	GameTagging get(long id);
	GameTagging add(String dudemail, long gameid, String tagName);
	void delete(String dudemail, long id);

	List<GameTagging> list(String dudemail);
	List<GameTagging> list(String dudemail, Long gameid);
}
