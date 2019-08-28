package ee.eerikmagi.experiments.games_app.api.util;

import lombok.Getter;

@Getter
public class DudeReference {
	private Long id;
	private String email;
	private String uuid;

	public boolean hasId() {
		return this.id != null;
	}

	public boolean hasEmail() {
		return this.email != null;
	}

	public boolean hasUUID() {
		return this.uuid != null;
	}

	public static DudeReference fromID(long id) {
		DudeReference dr = new DudeReference();
		dr.id = id;
		return dr;
	}

	public static DudeReference fromEmail(String email) {
		DudeReference dr = new DudeReference();
		dr.email = email;
		return dr;
	}

	public static DudeReference fromUUID(String uuid) {
		DudeReference dr = new DudeReference();
		dr.uuid = uuid;
		return dr;
	}
}
