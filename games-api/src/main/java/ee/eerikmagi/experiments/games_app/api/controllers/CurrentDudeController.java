package ee.eerikmagi.experiments.games_app.api.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import ee.eerikmagi.experiments.games_app.api.dto.DudeDTO;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;
import ee.eerikmagi.experiments.games_app.api.services.IDudeService;

@RestController
@RequestMapping("currentDude")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CurrentDudeController {
	private ModelMapper modelMapper;
	private IDudeService dudeSvc;

	@GetMapping
	@ResponseBody
	public DudeDTO get(Authentication authentication) {
		String dudemail = (String) authentication.getPrincipal();

		Dude dude = dudeSvc.findByEmail(dudemail);
		return modelMapper.map(dude, DudeDTO.class);
	}
}
