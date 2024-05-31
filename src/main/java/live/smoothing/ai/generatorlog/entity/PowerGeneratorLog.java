package live.smoothing.ai.generatorlog.entity;

import live.smoothing.ai.generator.entity.PowerGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Entity(name = "generator_logs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PowerGeneratorLog {

    @Id
    @Column(name = "log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @ManyToOne
    @JoinColumn(name = "generator_id")
    private PowerGenerator powerGenerator;

    @NotNull
    private LocalDateTime time;

    @NotNull
    @Size(max = 100)
    private String message;

    public PowerGeneratorLog(PowerGenerator powerGenerator, LocalDateTime time, String message) {
        this.powerGenerator = powerGenerator;
        this.time = time;
        this.message = message;
    }
}
