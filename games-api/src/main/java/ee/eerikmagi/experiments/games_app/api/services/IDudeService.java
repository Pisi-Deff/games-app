package ee.eerikmagi.experiments.games_app.api.services;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;
import ee.eerikmagi.experiments.games_app.api.util.DudeReference;

public interface IDudeService {
	long getIdByEmail(String email);
	long getIdByUUID(String uuid);
	Dude get(long id);
	Dude getByEmail(String email);
	Dude getByUUID(String uuid);
	Dude getByReference(DudeReference dudeRef);
}
