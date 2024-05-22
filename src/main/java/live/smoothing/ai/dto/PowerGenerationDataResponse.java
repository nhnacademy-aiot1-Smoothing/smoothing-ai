package live.smoothing.ai.dto;

import lombok.*;

import java.time.Instant;

/**
 * 발전기 조회 데이터 응답 DTO 클래스 입니다.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PowerGenerationDataResponse {
    private Instant time;

    private Double value;

}
