package ee.eerikmagi.experiments.games_app.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GameCreateUpdateDTO {
	private String name;
	private String description;
	private LocalDate releaseDate;
}
