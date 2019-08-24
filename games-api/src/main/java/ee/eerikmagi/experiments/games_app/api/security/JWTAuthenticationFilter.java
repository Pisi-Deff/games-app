package ee.eerikmagi.experiments.games_app.api.security;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ee.eerikmagi.experiments.games_app.api.dto.DudeDTO;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;
	private final ModelMapper modelMapper;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ModelMapper modelMapper) {
		this.authenticationManager = authenticationManager;
		this.modelMapper = modelMapper;

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

		DudeDTO dude = modelMapper.map(user.getDude(), DudeDTO.class);
		try {
			// TODO: figure out what class Spring is using to do content type negotiation & support writing output as whatever client requests, e.g. as xml
			response.getWriter().write(new ObjectMapper().writeValueAsString(dude));
		} catch (IOException e) {
			// TODO: log error & just don't send dude data to client
		}
	}
}
