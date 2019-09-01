package ee.eerikmagi.experiments.games_app.api.services.impl;

import java.util.UUID;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;
import ee.eerikmagi.experiments.games_app.api.persistence.repos.IDudeRepository;
import ee.eerikmagi.experiments.games_app.api.security.DudePrincipal;
import ee.eerikmagi.experiments.games_app.api.services.IDudeService;
import ee.eerikmagi.experiments.games_app.api.util.DudeReference;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class DudeService implements IDudeService, UserDetailsService {
	private IDudeRepository dudeRep;

	@Override
	public Long getIdByEmail(String email) {
		// TODO: redis
		return dudeRep.getIdByEmailIgnoreCase(email);
	}

	@Override
	public Long getIdByUUID(String uuid) {
		// TODO: redis
		return dudeRep.getIdByUuid(UUID.fromString(uuid));
	}

	@Override
	public Long getIdByReference(DudeReference dudeRef) {
		if (dudeRef.hasId()) {
			return dudeRef.getId();
		} else if (dudeRef.hasEmail()) {
			return getIdByEmail(dudeRef.getEmail());
		} else if (dudeRef.hasUUID()) {
			return getIdByUUID(dudeRef.getUuid());
		}
		return null;
	}

	@Override
	public Dude get(long id) {
		return dudeRep.getOne(id);
	}

	@Override
	public Dude getByEmail(String email) {
		return dudeRep.getByEmailIgnoreCase(email);
	}

	@Override
	public Dude getByUUID(String uuid) {
		return dudeRep.getByUuid(UUID.fromString(uuid));
	}

	@Override
	public Dude getByReference(DudeReference dudeRef) {
		if (dudeRef.hasId()) {
			return get(dudeRef.getId());
		} else if (dudeRef.hasEmail()) {
			return getByEmail(dudeRef.getEmail());
		} else if (dudeRef.hasUUID()) {
			return getByUUID(dudeRef.getUuid());
		}
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Dude dude = getByEmail(email);
		if (dude == null) {
			throw new UsernameNotFoundException(email);
		}

		return new DudePrincipal(dude);
	}
}
