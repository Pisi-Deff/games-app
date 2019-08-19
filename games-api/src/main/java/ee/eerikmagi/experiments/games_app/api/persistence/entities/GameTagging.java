package ee.eerikmagi.experiments.games_app.api.persistence.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class GameTagging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    @NotNull
	@ManyToOne
	@JoinColumn(name = "dude_id", nullable = false)
	private Dude dude;
}
