package ee.eerikmagi.experiments.games_app.api.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

@Getter
@Setter
public class GameDTO {
	private long id;
	private String name;
	private String description;
	private LocalDate releaseDate;
	private Slice<GameTagDTO> tags;
	private List<GameTaggingBasicDTO> dudeTaggings;
	private Page<GameReviewDTO> gameReviews;
}
