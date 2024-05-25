package live.smoothing.ai.repository;

import live.smoothing.ai.entity.PowerGenerationData;

import java.util.List;

/**
 * 특정 측정값과 필드 기주능로 발전기 데이터를 조회하는 메서드 입니다.
 *
 *
 */

public interface PowerGenerationDataRepository {
    /**
     *
     * @param measurement 측정값 이름 :
     * @param field 필드 이름  :
     * @return 조회된 발전기 데이터의 리스트
     */
    List<PowerGenerationData> getPowerGenerationData(String measurement, String field);
}
