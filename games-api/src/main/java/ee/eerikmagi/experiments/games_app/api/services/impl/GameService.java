package ee.eerikmagi.experiments.games_app.api.services.impl;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Game;
import ee.eerikmagi.experiments.games_app.api.persistence.repos.IGameRepository;
import ee.eerikmagi.experiments.games_app.api.services.IGameService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class GameService implements IGameService {
	private IGameRepository gameRep;

	@Override
	public Page<Game> getPage(Pageable pageable) {
		return gameRep.findAll(pageable);
	}

	@Override
	public Game get(long id) {
		return gameRep.getOne(id);
	}

	@Override
	public Game save(Game game) {
		return gameRep.save(game);
	}

	@Override
	public void delete(long id) {
		gameRep.deleteById(id);
	}
}
