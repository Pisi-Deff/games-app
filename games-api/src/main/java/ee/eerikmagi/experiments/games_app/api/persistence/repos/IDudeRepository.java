package ee.eerikmagi.experiments.games_app.api.persistence.repos;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDudeRepository extends CrudRepository<Dude, Long> {
	Dude findByUuid(String uuid);
	Dude findByEmailIgnoreCase(String email);
}
