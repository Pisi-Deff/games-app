package ee.eerikmagi.experiments.games_app.api.services.impl;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ee.eerikmagi.experiments.games_app.api.persistence.projections.GameTag;
import ee.eerikmagi.experiments.games_app.api.persistence.repos.IGameTaggingRepository;
import ee.eerikmagi.experiments.games_app.api.services.IGameTagService;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class GameTagService implements IGameTagService {
	private IGameTaggingRepository gameTaggingRep;

	@Override
	public Slice<GameTag> getByGameId(long gameId, Pageable pageable) {
		return gameTaggingRep.getGameTagsByGameId(gameId, pageable);
	}

	@Override
	public List<GameTag> getTop3ByGameId(long gameId) {
		Slice<GameTag> slice = gameTaggingRep.getGameTagsByGameId(gameId,
			PageRequest.of(0, 3, Sort.by(Sort.Order.desc("counter"), Sort.Order.asc("name"))));
		return slice.getContent();
	}

	@Override
	public GameTag getByGameIdAndTagName(long gameId, String name) {
		return gameTaggingRep.getGameTagByGameIdAndTag(gameId, name);
	}
}
