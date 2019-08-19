package ee.eerikmagi.experiments.games_app.api.services;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import ee.eerikmagi.experiments.games_app.api.persistence.projections.GameTag;

import java.util.List;

public interface IGameTagService {
	Slice<GameTag> getByGameId(long gameId, Pageable pageable);
	List<GameTag> getTop3ByGameId(long gameId);
}
