package ee.eerikmagi.experiments.games_app.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class GameListItemDTO {
	private long id;
	private String name;
	private LocalDate releaseDate;
	private List<GameTagDTO> topTags;
}
