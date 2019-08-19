package ee.eerikmagi.experiments.games_app.api.persistence.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Dude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(insertable = false, updatable = false)
    private String uuid;

    @NotNull
    private String email;

    @NotNull
    private String passwordHash;

    @NotNull
    private String displayName;
}
