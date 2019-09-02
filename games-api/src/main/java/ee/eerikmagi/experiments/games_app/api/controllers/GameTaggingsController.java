package ee.eerikmagi.experiments.games_app.api.controllers;

import java.util.List;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ee.eerikmagi.experiments.games_app.api.annotations.CurrentDude;
import ee.eerikmagi.experiments.games_app.api.dto.GameTagDTO;
import ee.eerikmagi.experiments.games_app.api.dto.GameTaggingBasicDTO;
import ee.eerikmagi.experiments.games_app.api.dto.NewGameTaggingResponseDTO;
import ee.eerikmagi.experiments.games_app.api.dto.TagDTO;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.GameTagging;
import ee.eerikmagi.experiments.games_app.api.persistence.projections.GameTag;
import ee.eerikmagi.experiments.games_app.api.services.IGameTagService;
import ee.eerikmagi.experiments.games_app.api.services.IGameTaggingService;

@RestController
@RequestMapping("games/{gameId}/taggings")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class GameTaggingsController {
	private ModelMapper modelMapper;
	private IGameTaggingService gameTaggingSvc;
	private IGameTagService gameTagSvc;

	@GetMapping
	@ResponseBody
	public List<GameTaggingBasicDTO> list(@PathVariable long gameId, @CurrentDude Dude currentDude) {
		List<GameTagging> gameTaggings = gameTaggingSvc.list(currentDude, gameId);
		return modelMapper.map(gameTaggings, new TypeToken<List<GameTaggingBasicDTO>>() {}.getType());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public NewGameTaggingResponseDTO add(@PathVariable long gameId, @RequestBody TagDTO tag, @CurrentDude Dude currentDude) {
		GameTagging newGT = gameTaggingSvc.add(currentDude, gameId, tag.getName());
		GameTag gameTag = gameTagSvc.getByGameIdAndTagName(gameId, tag.getName());

		NewGameTaggingResponseDTO response = new NewGameTaggingResponseDTO();
		response.setTag(modelMapper.map(gameTag, GameTagDTO.class));
		response.setTagging(modelMapper.map(newGT, GameTaggingBasicDTO.class));
		return response;
	}

	@DeleteMapping("/{taggingId}")
	public void delete(@PathVariable long taggingId, @CurrentDude Dude currentDude) {
		gameTaggingSvc.delete(currentDude, taggingId);
	}
}
