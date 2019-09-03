package ee.eerikmagi.experiments.games_app.api.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ee.eerikmagi.experiments.games_app.api.annotations.CurrentDude;
import ee.eerikmagi.experiments.games_app.api.dto.GameReviewEditDTO;
import ee.eerikmagi.experiments.games_app.api.dto.GameReviewDTO;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.GameReview;
import ee.eerikmagi.experiments.games_app.api.services.IGameReviewService;

@RestController
@RequestMapping("games/{gameId}/reviews")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class GameReviewsController {
	private ModelMapper modelMapper;
	private IGameReviewService gameReviewSvc;

	@GetMapping
	@ResponseBody
	public Page<GameReviewDTO> list(
		@PathVariable long gameId,

		@PageableDefault
		@SortDefault(sort = "reviewDate", direction = Sort.Direction.DESC)
		Pageable pageable
	) {
		Page<GameReview> gameReviews = gameReviewSvc.getByGameId(gameId, pageable);
		return gameReviews.map(r -> modelMapper.map(r, GameReviewDTO.class));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public GameReviewDTO add(@PathVariable long gameId,
		@RequestBody GameReviewEditDTO review,
		@CurrentDude Dude currentDude
	) {
		GameReview gr = modelMapper.map(review, GameReview.class);
		return modelMapper.map(gameReviewSvc.add(currentDude, gameId, gr), GameReviewDTO.class);
	}

	@PatchMapping("/{reviewId}")
	public GameReviewDTO update(
		@PathVariable long reviewId,
		@RequestBody GameReviewEditDTO update,
		@CurrentDude Dude currentDude
	) {
		GameReview gr = gameReviewSvc.get(reviewId);
		modelMapper.map(update, gr);
		return modelMapper.map(gameReviewSvc.update(currentDude, gr), GameReviewDTO.class);
	}

	@DeleteMapping("/{reviewId}")
	public void delete(@PathVariable long reviewId, @CurrentDude Dude currentDude) {
		gameReviewSvc.delete(currentDude, reviewId);
	}
}
