package ee.eerikmagi.experiments.games_app.api.controllers;

import java.util.List;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import ee.eerikmagi.experiments.games_app.api.dto.GameTaggingCreationDTO;
import ee.eerikmagi.experiments.games_app.api.dto.GameTaggingDTO;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.GameTagging;
import ee.eerikmagi.experiments.games_app.api.services.IGameTaggingService;

@RestController
@RequestMapping("games/{gameId}/taggings")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class GameTaggingsController {
	private ModelMapper modelMapper;
	private IGameTaggingService gameTaggingSvc;

	// TODO:
	// 1) convert dudemail to ID (hibernate + redis)
	// 2) replace Dude objects with a hibernate proxy using ID

	@GetMapping
	@ResponseBody
	public List<GameTaggingDTO> list(@PathVariable long gameId, Authentication authentication) {
		String dudemail = (String) authentication.getPrincipal();
		List<GameTagging> gameTaggings = gameTaggingSvc.list(dudemail, gameId);
		return modelMapper.map(gameTaggings, new TypeToken<List<GameTaggingDTO>>() {}.getType());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void add(@PathVariable long gameId, @RequestBody GameTaggingCreationDTO tagging, Authentication authentication) {
		String dudemail = (String) authentication.getPrincipal();
		gameTaggingSvc.add(dudemail, gameId, tagging.getTagName());
	}

	@DeleteMapping("/{taggingId}")
	public void delete(@PathVariable long taggingId, Authentication authentication) {
		String dudemail = (String) authentication.getPrincipal();
		gameTaggingSvc.delete(dudemail, taggingId);
	}
}
