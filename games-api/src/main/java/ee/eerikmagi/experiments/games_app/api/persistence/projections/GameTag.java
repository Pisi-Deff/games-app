package ee.eerikmagi.experiments.games_app.api.persistence.projections;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameTag {
	private long gameid;

	private long tagid;
	private String tagName;

	private long counter;
}
