package ee.eerikmagi.experiments.games_app.api.persistence.projections;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameTag {
	private long gameId;

	private long tagId;
	private String tagName;

	private long counter;
}
