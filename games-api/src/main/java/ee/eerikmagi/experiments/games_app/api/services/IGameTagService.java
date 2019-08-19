package ee.eerikmagi.experiments.games_app.api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ee.eerikmagi.experiments.games_app.api.persistence.projections.GameTag;

import java.util.List;

public interface IGameTagService {
	Page<GameTag> getByGameId(long gameId, Pageable pageable);
	List<GameTag> getTop3ByGameId(long gameId);
}
