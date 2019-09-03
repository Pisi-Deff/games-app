package ee.eerikmagi.experiments.games_app.api.controllers;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ee.eerikmagi.experiments.games_app.api.annotations.CurrentDude;
import ee.eerikmagi.experiments.games_app.api.dto.*;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.Game;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.GameReview;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.GameTagging;
import ee.eerikmagi.experiments.games_app.api.persistence.projections.GameTag;
import ee.eerikmagi.experiments.games_app.api.services.IGameReviewService;
import ee.eerikmagi.experiments.games_app.api.services.IGameService;
import ee.eerikmagi.experiments.games_app.api.services.IGameTagService;
import ee.eerikmagi.experiments.games_app.api.services.IGameTaggingService;

@RestController
@RequestMapping("games")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class GameController {
	private ModelMapper modelMapper;
	private IGameService gameSvc;
	private IGameTagService gameTagSvc;
	private IGameTaggingService gameTaggingSvc;
	private IGameReviewService gameReviewSvc;

	@GetMapping
	@ResponseBody
	public Page<GameListItemDTO> list(Pageable pageable) {
		Page<Game> page = gameSvc.getPage(pageable);
		return page.map(game -> {
			GameListItemDTO item = modelMapper.map(game, GameListItemDTO.class);

			List<GameTag> gameTags = gameTagSvc.getTop3ByGameId(game.getId());
			item.setTopTags(modelMapper.map(gameTags, new TypeToken<List<GameTagDTO>>() {}.getType()));

			return item;
		});
	}

	@GetMapping("/{id}")
	@ResponseBody
	public GameDTO get(@PathVariable long id, @CurrentDude Dude currentDude) {
		Game game = gameSvc.get(id);
		GameDTO gameDTO = modelMapper.map(game, GameDTO.class);

		Slice<GameTag> gameTags = gameTagSvc.getByGameId(game.getId(),
			PageRequest.of(0, 10, Sort.by(Sort.Order.desc("counter"), Sort.Order.asc("name"))));
		gameDTO.setTags(gameTags.map(gt -> modelMapper.map(gt, GameTagDTO.class)));

		// TODO: rethink whether this is "the" solution...
		//  maybe forcing frontend to make 2 requests is more reasonable to keep this call cleaner?
		List<GameTagging> dudeGameTags = gameTaggingSvc.list(currentDude, game.getId());
		gameDTO.setDudeTaggings(modelMapper.map(dudeGameTags, new TypeToken<List<GameTaggingBasicDTO>>() {}.getType()));

		Page<GameReview> gameReviews = gameReviewSvc.getByGameId(game.getId(),
			PageRequest.of(0, 10, Sort.by(Sort.Order.desc("reviewDate"))));
		gameDTO.setReviews(gameReviews.map(gr -> modelMapper.map(gr, GameReviewDTO.class)));

		GameReview currentDudeReview = gameReviewSvc.getByGameIdAndDudeId(game.getId(), currentDude.getId());
		if (currentDudeReview != null) {
			gameDTO.setDudeReview(modelMapper.map(currentDudeReview, GameReviewDTO.class));
		}

		gameDTO.setAvgReviewScore(BigDecimal.valueOf(3.66d)); // TODO

		return gameDTO;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public GameDTO create(@RequestBody GameCreateUpdateDTO game) {
		Game newGame = gameSvc.save(modelMapper.map(game, Game.class));
		return modelMapper.map(newGame, GameDTO.class);
	}

	@PatchMapping("/{id}")
	@ResponseBody
	public GameDTO update(@PathVariable long id, @RequestBody GameCreateUpdateDTO gameUpdate) {
		Game game = gameSvc.get(id);
		modelMapper.map(gameUpdate, game);
		game = gameSvc.save(game);
		return modelMapper.map(game, GameDTO.class);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		gameSvc.delete(id);
	}
}
