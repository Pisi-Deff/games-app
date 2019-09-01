package ee.eerikmagi.experiments.games_app.api.persistence.repos;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;

@Repository
public interface IDudeRepository extends JpaRepository<Dude, Long> {
	Dude getByUuid(UUID uuid);
	Dude getByEmailIgnoreCase(String email);

	Long getIdByUuid(UUID uuid);
	Long getIdByEmailIgnoreCase(String email);
}
