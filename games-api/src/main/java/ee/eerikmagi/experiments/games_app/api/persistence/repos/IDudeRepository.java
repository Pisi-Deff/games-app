package ee.eerikmagi.experiments.games_app.api.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;

@Repository
public interface IDudeRepository extends JpaRepository<Dude, Long> {
	Dude getByUuid(String uuid);
	Dude getByEmailIgnoreCase(String email);

	long getIdByUuid(String uuid);
	long getIdByEmailIgnoreCase(String email);
}
