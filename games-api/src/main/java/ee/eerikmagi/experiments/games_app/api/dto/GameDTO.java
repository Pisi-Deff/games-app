package ee.eerikmagi.experiments.games_app.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Slice;

import java.time.LocalDate;

@Getter
@Setter
public class GameDTO {
	private long id;
	private String name;
	private String description;
	private LocalDate releaseDate;
	private Slice<GameTagDTO> tags;
}
