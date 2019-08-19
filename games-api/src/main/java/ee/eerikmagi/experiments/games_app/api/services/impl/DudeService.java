package ee.eerikmagi.experiments.games_app.api.services.impl;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;
import ee.eerikmagi.experiments.games_app.api.persistence.repos.IDudeRepository;
import ee.eerikmagi.experiments.games_app.api.security.DudePrincipal;
import ee.eerikmagi.experiments.games_app.api.services.IDudeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class DudeService implements IDudeService, UserDetailsService {
	private IDudeRepository dudeRep;

	@Override
	public long getidByEmail(String email) {
		// TODO: redis with fallback to query in repo
		return 0;
	}

	@Override
	public long getidByUUID(String uuid) {
		// TODO: redis with fallback to query in repo
		return 0;
	}

	@Override
	public Dude findByEmail(String email) {
		return dudeRep.findByEmailIgnoreCase(email);
	}

	@Override
	public Dude findByUUID(String uuid) {
		return dudeRep.findByUuid(uuid);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Dude dude = findByEmail(email);
		if (dude == null) {
			throw new UsernameNotFoundException(email);
		}

		return new DudePrincipal(dude);
	}
}
