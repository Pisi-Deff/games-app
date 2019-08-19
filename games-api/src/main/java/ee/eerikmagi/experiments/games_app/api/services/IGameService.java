package ee.eerikmagi.experiments.games_app.api.services;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IGameService {
	Page<Game> getPage(Pageable pageable);
	Game get(long id);
	Game save(Game map);
	void delete(long id);

//	Game find();
}
