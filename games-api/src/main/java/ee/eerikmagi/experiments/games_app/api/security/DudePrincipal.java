package ee.eerikmagi.experiments.games_app.api.security;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@RequiredArgsConstructor
@Getter
public class DudePrincipal implements UserDetails {
	private final Dude dude;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_DUDE")); // TODO
	}

	@Override
	public String getPassword() {
		return this.dude.getPasswordHash();
	}

	@Override
	public String getUsername() {
		return this.dude.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
