package ee.eerikmagi.experiments.games_app.api.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameReviewDTO {
	private long id;
	private DudeDTO dude;
	private int score;
	private String review;
	private LocalDateTime reviewDate;
}
