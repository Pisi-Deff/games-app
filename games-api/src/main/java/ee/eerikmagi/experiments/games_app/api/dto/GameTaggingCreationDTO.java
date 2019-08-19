package ee.eerikmagi.experiments.games_app.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameTaggingCreationDTO {
	private String tagName;
	private long gameId;
}
