package ee.eerikmagi.experiments.games_app.api.persistence.entities;

import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Data
public class Dude {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Generated(GenerationTime.INSERT)
	@Column(insertable = false, updatable = false)
	private UUID uuid;

	@NotNull
	private String email;

	@NotNull
	private String passwordHash;

	@NotNull
	private String displayName;
}
