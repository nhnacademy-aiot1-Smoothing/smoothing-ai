package live.smoothing.ai.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * 발전 조회 API에 사용 될 Entity 클래스 입니다.
 * 발전 데이터를 저장하는데 사용하고, InfluxDB에서 데이터를 조회할 때 사용합니다.
 */
@Getter
@NoArgsConstructor
public class PowerGenerationData {

    private Instant time;
    private Double value;
}