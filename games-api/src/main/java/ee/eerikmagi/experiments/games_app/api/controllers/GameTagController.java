package ee.eerikmagi.experiments.games_app.api.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import ee.eerikmagi.experiments.games_app.api.dto.GameTagDTO;
import ee.eerikmagi.experiments.games_app.api.persistence.projections.GameTag;
import ee.eerikmagi.experiments.games_app.api.services.IGameTagService;

@RestController
@RequestMapping("games/{gameId}/tags")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class GameTagController {
	private ModelMapper modelMapper;
	private IGameTagService gameTagSvc;

	@GetMapping
	@ResponseBody
	public Slice<GameTagDTO> getTags(
		@PathVariable long gameId,

		@PageableDefault(size = 8)
		@SortDefault.SortDefaults({
			@SortDefault(sort = "counter", direction = Sort.Direction.DESC),
			@SortDefault(sort = "name", direction = Sort.Direction.ASC)
		})
		Pageable pageable
	) {
		Slice<GameTag> gameTags = gameTagSvc.getByGameId(gameId, pageable);
		return modelMapper.map(gameTags, new TypeToken<Slice<GameTagDTO>>() {}.getType());
	}
}
