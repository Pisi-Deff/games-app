package ee.eerikmagi.experiments.games_app.api.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ee.eerikmagi.experiments.games_app.api.dto.DudeDTO;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;
import ee.eerikmagi.experiments.games_app.api.annotations.CurrentDude;

@RestController
@RequestMapping("currentDude")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CurrentDudeController {
	private ModelMapper modelMapper;

	@GetMapping
	@ResponseBody
	public DudeDTO get(@CurrentDude Dude currentDude) {
		return modelMapper.map(currentDude, DudeDTO.class);
	}
}
