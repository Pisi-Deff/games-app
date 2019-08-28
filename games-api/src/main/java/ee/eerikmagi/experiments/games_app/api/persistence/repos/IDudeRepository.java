package ee.eerikmagi.experiments.games_app.api.persistence.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;

@Repository
public interface IDudeRepository extends CrudRepository<Dude, Long> {
	Dude getById(long id);
	Dude getByUuid(String uuid);
	Dude getByEmailIgnoreCase(String email);

	long getIdByUuid(String uuid);
	long getIdByEmailIgnoreCase(String email);
}
