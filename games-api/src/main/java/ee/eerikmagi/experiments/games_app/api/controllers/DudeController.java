package ee.eerikmagi.experiments.games_app.api.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ee.eerikmagi.experiments.games_app.api.dto.DudeDTO;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;
import ee.eerikmagi.experiments.games_app.api.services.IDudeService;

@RestController
@RequestMapping("dudes")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class DudeController {
	private ModelMapper modelMapper;
	private IDudeService dudeSvc;

	@GetMapping("{uuid}")
	@ResponseBody
	public DudeDTO get(@PathVariable String uuid) {
		Dude dude = dudeSvc.getByUUID(uuid);
		return modelMapper.map(dude, DudeDTO.class);
	}
}
