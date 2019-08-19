package ee.eerikmagi.experiments.games_app.api.persistence.repos;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGameRepository extends JpaRepository<Game, Long> {
	// TODO: figure out way to use postgresql similarity operator instead of "contains"
	// possibly something similar to: http://java-talks.blogspot.com/2014/04/use-postgresql-full-text-search-with-hql.html
	List<Game> findFirst20ByNameContainingIgnoreCase(String name);
}
