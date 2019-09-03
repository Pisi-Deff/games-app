package ee.eerikmagi.experiments.games_app.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameReviewEditDTO {
	private int score;
	private String review;
}
