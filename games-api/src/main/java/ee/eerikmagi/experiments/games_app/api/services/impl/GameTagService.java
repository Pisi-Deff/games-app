package ee.eerikmagi.experiments.games_app.api.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ee.eerikmagi.experiments.games_app.api.persistence.projections.GameTag;
import ee.eerikmagi.experiments.games_app.api.persistence.repos.IGameTaggingRepository;
import ee.eerikmagi.experiments.games_app.api.services.IGameTagService;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class GameTagService implements IGameTagService {
	private IGameTaggingRepository gameTaggingRep;

	@Override
	public Page<GameTag> getByGameId(long gameId, Pageable pageable) {
		return gameTaggingRep.getGameTagsByGameid(gameId, pageable);
	}

	@Override
	public List<GameTag> getTop3ByGameId(long gameId) {
		Page<GameTag> page = gameTaggingRep.getGameTagsByGameid(gameId,
			PageRequest.of(0, 3, Sort.by(Sort.Order.desc("counter"), Sort.Order.asc("name"))));
		return page.getContent();
	}
}
