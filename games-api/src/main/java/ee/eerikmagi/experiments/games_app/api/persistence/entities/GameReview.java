package ee.eerikmagi.experiments.games_app.api.persistence.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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

    @Column(insertable = false, updatable = false)
    private LocalDateTime reviewDate;
}
