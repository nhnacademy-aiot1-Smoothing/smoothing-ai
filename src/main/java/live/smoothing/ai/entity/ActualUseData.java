package live.smoothing.ai.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
public class ActualUseData {

    private Instant time;
    private Double value;
    private String field;
}
