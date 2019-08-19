package ee.eerikmagi.experiments.games_app.api.services;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;

public interface IDudeService {
	long getidByEmail(String email);
	long getidByUUID(String uuid);
	Dude findByEmail(String email);
	Dude findByUUID(String uuid);
}
