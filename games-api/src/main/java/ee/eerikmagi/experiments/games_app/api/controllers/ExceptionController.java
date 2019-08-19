package ee.eerikmagi.experiments.games_app.api.controllers;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void defaultExHandler(Exception e) throws Exception {
		if (AnnotationUtils.findAnnotation
			(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		}
	}
}
