package ee.eerikmagi.experiments.games_app.api.controllers;

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

import java.util.List;

@RestController
@RequestMapping("currentDude/taggings")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CurrentDudeTaggingsController {
	private ModelMapper modelMapper;
	private IGameTaggingService gameTaggingSvc;

	@GetMapping
	@ResponseBody
	public List<GameTaggingDTO> listTaggings(@RequestParam(required = false) Long gameid, Authentication authentication) {
		List<GameTagging> gameTaggings;
		String dudemail = (String) authentication.getPrincipal();

		if (gameid != null) {
			gameTaggings = gameTaggingSvc.list(dudemail, gameid);
		} else {
			gameTaggings = gameTaggingSvc.list(dudemail);
		}

		return modelMapper.map(gameTaggings, new TypeToken<List<GameTaggingDTO>>() {}.getType());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void addTagging(@RequestBody GameTaggingCreationDTO tagging, Authentication authentication) {
		String dudemail = (String) authentication.getPrincipal();
		gameTaggingSvc.add(dudemail, tagging.getGameId(), tagging.getTagName());
	}

	@DeleteMapping("/{taggingId}")
	public void deleteTagging(@PathVariable long taggingId, Authentication authentication) {
		String dudemail = (String) authentication.getPrincipal();
		gameTaggingSvc.delete(dudemail, taggingId);
	}
}
