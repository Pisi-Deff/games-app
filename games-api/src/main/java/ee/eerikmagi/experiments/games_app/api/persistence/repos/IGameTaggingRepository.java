package ee.eerikmagi.experiments.games_app.api.persistence.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.GameTagging;
import ee.eerikmagi.experiments.games_app.api.persistence.projections.GameTag;

import java.util.List;

@Repository
public interface IGameTaggingRepository extends JpaRepository<GameTagging, Long> {
	GameTagging getByDudeIdAndGameIdAndTagNameIgnoreCase(long dudeId, long gameId, String tagName);

	List<GameTagging> getByDude(Dude dude);

	List<GameTagging> getByDudeAndGameId(Dude dude, long gameId);

	@SuppressWarnings("SpringDataRepositoryMethodReturnTypeInspection")
	@Query(
		"SELECT NEW ee.eerikmagi.experiments.games_app.api.persistence.projections.GameTag(" +
			"gt.game.id AS gameId, gt.tag.id AS tagId, gt.tag.name AS name, COUNT(gt) AS counter" +
			") FROM GameTagging gt WHERE gt.game.id = :gameId " +
			"GROUP BY gt.game.id, gt.tag.id, gt.tag.name"
	)
	Slice<GameTag> getGameTagsByGameId(@Param("gameId") long gameId, Pageable pageable);
}
