package ee.eerikmagi.experiments.games_app.api.services;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;
import ee.eerikmagi.experiments.games_app.api.util.DudeReference;

public interface IDudeService {
	Long getIdByEmail(String email);
	Long getIdByUUID(String uuid);
	Long getIdByReference(DudeReference dudeRef);
	Dude get(long id);
	Dude getByEmail(String email);
	Dude getByUUID(String uuid);
	Dude getByReference(DudeReference dudeRef);
}
