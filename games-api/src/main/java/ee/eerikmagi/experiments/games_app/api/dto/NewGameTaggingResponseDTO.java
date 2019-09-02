package ee.eerikmagi.experiments.games_app.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewGameTaggingResponseDTO {
	private GameTaggingBasicDTO tagging;
	private GameTagDTO tag;
}
