package ee.eerikmagi.experiments.games_app.api.security;

public class SecurityConstants {
	public static final String AUTH_LOGIN_URL = "/authenticate";

	public static final String PARAM_USERNAME = "email";

	// Signing key for HS512 algorithm
	// You can use the page http://www.allkeysgenerator.com/ to generate all kinds of keys
	public static final String JWT_SECRET =
		"ShVmYq3t6w9z$B&E)H@McQfTjWnZr4u7x!A%D*F-JaNdRgUkXp2s5v8y/B?E(H+KbPeShVmYq3t6w9z$C&F)J@NcQfTjWnZr4u7x!A%D*G-KaPdSgUkXp2s5v8y/B?E(H+MbQeThWmYq3t6w9z$C&F)J@NcRfUjXn2r5u7x!A%D*G-KaPdSgVkYp3s6v9y/B?E(H+MbQeThWmZq4t7w!z%C&F)J@NcRfUjXn2r5u8x/A?D(G-KaPdSgVkYp3s6v9";

	// JWT token defaults
	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String TOKEN_TYPE = "JWT";
	public static final String TOKEN_ISSUER = "secure-api";
	public static final String TOKEN_AUDIENCE = "secure-app";

	public static final long TOKEN_LIFETIME = 10 * 24 * 60 * 60 * 1000; // 10 days in ms
}
