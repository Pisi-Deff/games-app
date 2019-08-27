package ee.eerikmagi.experiments.games_app.api.controllers;

import java.util.List;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ee.eerikmagi.experiments.games_app.api.dto.GameCreateUpdateDTO;
import ee.eerikmagi.experiments.games_app.api.dto.GameDTO;
import ee.eerikmagi.experiments.games_app.api.dto.GameListItemDTO;
import ee.eerikmagi.experiments.games_app.api.dto.GameTagDTO;
import ee.eerikmagi.experiments.games_app.api.persistence.entities.Game;
import ee.eerikmagi.experiments.games_app.api.persistence.projections.GameTag;
import ee.eerikmagi.experiments.games_app.api.services.IGameService;
import ee.eerikmagi.experiments.games_app.api.services.IGameTagService;

@RestController
@RequestMapping("games")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class GameController {
	private ModelMapper modelMapper;
	private IGameService gameSvc;
	private IGameTagService gameTagSvc;

	@GetMapping
	@ResponseBody
	public Page<GameListItemDTO> list(Pageable pageable) {
		Page<Game> page = gameSvc.getPage(pageable);
		return page.map((game) -> {
			GameListItemDTO item = modelMapper.map(game, GameListItemDTO.class);

			List<GameTag> gameTags = gameTagSvc.getTop3ByGameId(game.getId());
			item.setTopTags(modelMapper.map(gameTags, new TypeToken<List<GameTagDTO>>() {}.getType()));

			return item;
		});
	}

	@GetMapping("/{id}")
	@ResponseBody
	public GameDTO get(@PathVariable long id) {
		Game game = gameSvc.get(id);
		GameDTO gameDTO = modelMapper.map(game, GameDTO.class);

		Slice<GameTag> gameTags = gameTagSvc.getByGameId(game.getId(),
			PageRequest.of(0, 10, Sort.by(Sort.Order.desc("counter"), Sort.Order.asc("name"))));
		gameDTO.setTags(modelMapper.map(gameTags, new TypeToken<Slice<GameTagDTO>>() {}.getType()));

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
