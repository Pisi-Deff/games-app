package ee.eerikmagi.experiments.games_app.api.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameReviewDTO {
	private long id;
	private String dudeUuid;
	private String dudeDisplayName;
	private int score;
	private String review;
	private LocalDateTime reviewDate;
}
