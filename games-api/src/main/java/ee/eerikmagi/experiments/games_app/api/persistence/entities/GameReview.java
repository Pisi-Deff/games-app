package ee.eerikmagi.experiments.games_app.api.persistence.entities;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Data
public class GameReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "dude_id", nullable = false)
    private Dude dude;

    @NotNull
    private int score;

    private String review;

	@Generated(GenerationTime.INSERT)
    @Column(insertable = false, updatable = false)
    private LocalDateTime reviewDate;
}
