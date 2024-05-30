package live.smoothing.ai.generator.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Getter
@Entity(name = "power_generator")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PowerGenerator {

    @Id
    @Column(name = "generator_id")
    private String generatorId;

    @Size(max = 50)
    @Column(name = "generator_state")
    private String generatorState;
}
