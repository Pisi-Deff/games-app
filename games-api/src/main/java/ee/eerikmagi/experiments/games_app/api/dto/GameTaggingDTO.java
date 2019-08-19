package ee.eerikmagi.experiments.games_app.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameTaggingDTO {
	private long id;
	private String tagName;
	private String gameName;
}
