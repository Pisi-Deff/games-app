package ee.eerikmagi.experiments.games_app.api.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
									FilterChain filterChain) throws IOException, ServletException {
		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		if (authentication == null) {
			filterChain.doFilter(request, response);
			return;
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstants.TOKEN_HEADER);
		if (StringUtils.isNotEmpty(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			try {
				byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();

				Jws<Claims> parsedToken = Jwts.parser()
					.setSigningKey(signingKey)
					.parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, StringUtils.EMPTY));

				String username = parsedToken
					.getBody()
					.getSubject();

				List<SimpleGrantedAuthority> authorities = ((List<?>) parsedToken.getBody()
					.get("rol")).stream()
					.map(authority -> new SimpleGrantedAuthority((String) authority))
					.collect(Collectors.toList());

				if (StringUtils.isNotBlank(username)) {
					return new UsernamePasswordAuthenticationToken(username, null, authorities);
				}
			} catch (ExpiredJwtException exception) {
//				log.warn("Request to parse expired JWT : {} failed : {}", token, exception.getMessage());
			} catch (UnsupportedJwtException exception) {
//				log.warn("Request to parse unsupported JWT : {} failed : {}", token, exception.getMessage());
			} catch (MalformedJwtException exception) {
//				log.warn("Request to parse invalid JWT : {} failed : {}", token, exception.getMessage());
			} catch (SignatureException exception) {
//				log.warn("Request to parse JWT with invalid signature : {} failed : {}", token, exception.getMessage());
			} catch (IllegalArgumentException exception) {
//				log.warn("Request to parse empty or null JWT : {} failed : {}", token, exception.getMessage());
			}
		}

		return null;
	}
}
