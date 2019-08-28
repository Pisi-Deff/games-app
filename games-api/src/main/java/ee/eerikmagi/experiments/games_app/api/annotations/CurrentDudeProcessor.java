package ee.eerikmagi.experiments.games_app.api.annotations;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;
import ee.eerikmagi.experiments.games_app.api.services.IDudeService;
import ee.eerikmagi.experiments.games_app.api.util.DudeReference;

@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CurrentDudeProcessor implements HandlerMethodArgumentResolver {
	private IDudeService dudeSvc;

	private static final List<Class> SUPPORTED_TYPES = Arrays.asList(
		Dude.class, DudeReference.class
	);

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(CurrentDude.class)
			&& SUPPORTED_TYPES.contains(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory
	) throws Exception {
		Authentication auth = (Authentication) webRequest.getUserPrincipal();
		DudeReference dudeRef = (DudeReference) auth.getPrincipal();

		if (parameter.getParameterType().equals(Dude.class)) {
			return dudeSvc.getByReference(dudeRef);
		} else if (parameter.getParameterType().equals(DudeReference.class)) {
			return dudeRef;
		}

		return null;
	}
}
