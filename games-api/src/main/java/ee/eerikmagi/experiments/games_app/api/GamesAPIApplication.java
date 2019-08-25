package ee.eerikmagi.experiments.games_app.api;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import ee.eerikmagi.experiments.games_app.api.security.JWTAuthenticationFilter;
import ee.eerikmagi.experiments.games_app.api.security.JWTAuthorizationFilter;

@SpringBootApplication
public class GamesAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamesAPIApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());

		return source;
	}

	@Configuration
	@AllArgsConstructor(onConstructor_ = @Autowired)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		private ModelMapper modelMapper;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http
				.addFilter(new JWTAuthenticationFilter(authenticationManager(), modelMapper))
				.addFilter(new JWTAuthorizationFilter(authenticationManager()))

				.cors()

				.and()
					.csrf()
					.disable()

				.authorizeRequests()
					.anyRequest().authenticated()

				.and()
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			// @formatter:on
		}
	}

	@Bean
	public LettuceConnectionFactory lettuceConnectionFactory() {
		return new LettuceConnectionFactory(new RedisStandaloneConfiguration());
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(lettuceConnectionFactory());
		return template;
	}
}
