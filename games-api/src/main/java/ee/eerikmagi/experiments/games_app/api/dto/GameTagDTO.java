package ee.eerikmagi.experiments.games_app.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameTagDTO implements Comparable<GameTagDTO> {
	private long tagId;
	private String tagName;
	private long counter;

	@Override
	public int compareTo(GameTagDTO o) {
		int result = Long.compare(o.counter, this.counter);

		if (result == 0) {
			result = this.tagName.compareToIgnoreCase(o.tagName);
		}

		return result;
	}
}
