package ee.eerikmagi.experiments.games_app.api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;

		setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
		setUsernameParameter(SecurityConstants.PARAM_USERNAME);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

		return authenticationManager.authenticate(authToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
		DudePrincipal user = (DudePrincipal) authResult.getPrincipal();

		List<String> roles = user.getAuthorities()
			.stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.toList());

		byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();

		String token = Jwts.builder()
			.signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
			.setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
			.setIssuer(SecurityConstants.TOKEN_ISSUER)
			.setAudience(SecurityConstants.TOKEN_AUDIENCE)
			.setSubject(user.getUsername())
			.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_LIFETIME))
			.claim("rol", roles)
			.compact();

		response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token);
	}
}
