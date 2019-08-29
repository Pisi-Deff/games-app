package ee.eerikmagi.experiments.games_app.api.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import ee.eerikmagi.experiments.games_app.api.annotations.CurrentDude;
import ee.eerikmagi.experiments.games_app.api.dto.GameReviewDTO;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.Dude;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.GameReview;
import ee.eerikmagi.experiments.games_app.api.services.IDudeService;
import ee.eerikmagi.experiments.games_app.api.services.IGameReviewService;
import ee.eerikmagi.experiments.games_app.api.util.DudeReference;

@RestController
@RequestMapping("currentDude/reviews")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CurrentDudeReviewsController {
	private ModelMapper modelMapper;
	private IGameReviewService gameReviewSvc;
	private IDudeService dudeSvc;

	@GetMapping
	@ResponseBody
	public Page<GameReviewDTO> listReviews(
		@CurrentDude DudeReference currentDude,

		@PageableDefault
		@SortDefault(sort = "reviewDate", direction = Sort.Direction.DESC)
		Pageable pageable
	) {
		Page<GameReview> gameReviews = gameReviewSvc.getByDudeId(
			dudeSvc.getIdByReference(currentDude), pageable);
		return gameReviews.map(gr -> modelMapper.map(gr, GameReviewDTO.class));
	}

	@DeleteMapping("/{reviewId}")
	public void delete(@PathVariable long reviewId, @CurrentDude Dude currentDude) {
		gameReviewSvc.delete(currentDude, reviewId);
	}
}
