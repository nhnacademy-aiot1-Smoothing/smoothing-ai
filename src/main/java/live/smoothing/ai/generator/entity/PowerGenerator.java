package live.smoothing.ai.generator.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity(name = "power_generator")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PowerGenerator {

    @Id
    @Column(name = "generator_id")
    private String generatorId;

    @Column(name = "generator_state")
    private String generatorState;
}
