package live.smoothing.ai.service;

import live.smoothing.ai.dto.PowerGenerationDataResponse;

import java.util.List;

/**
 *  발전기 조회 데이터를 처리하는 서비스 입니다.
 */

public interface PowerGenerationService {
    List<PowerGenerationDataResponse> getPowerGenerationData(String measurement, String field);
}
