package ee.eerikmagi.experiments.games_app.api.controllers;

import java.util.List;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import ee.eerikmagi.experiments.games_app.api.dto.GameTaggingDTO;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.GameTagging;
import ee.eerikmagi.experiments.games_app.api.services.IGameTaggingService;

@RestController
@RequestMapping("currentDude/taggings")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CurrentDudeTaggingsController {
	private ModelMapper modelMapper;
	private IGameTaggingService gameTaggingSvc;

	// TODO:
	// 1) convert dudemail to ID (hibernate + redis)
	// 2) replace Dude objects with a hibernate proxy using ID

	@GetMapping
	@ResponseBody
	public List<GameTaggingDTO> listTaggings(@RequestParam(required = false) Long gameId, Authentication authentication) {
		List<GameTagging> gameTaggings;
		String dudemail = (String) authentication.getPrincipal();

		if (gameId != null) {
			gameTaggings = gameTaggingSvc.list(dudemail, gameId);
		} else {
			gameTaggings = gameTaggingSvc.list(dudemail);
		}

		return modelMapper.map(gameTaggings, new TypeToken<List<GameTaggingDTO>>() {}.getType());
	}

	@DeleteMapping("/{taggingId}")
	public void delete(@PathVariable long taggingId, Authentication authentication) {
		String dudemail = (String) authentication.getPrincipal();
		gameTaggingSvc.delete(dudemail, taggingId);
	}
}
