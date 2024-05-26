package live.smoothing.ai.generation.service;

import live.smoothing.ai.common.dto.InfluxDataResponse;

import java.util.List;

/**
 *  발전기 조회 데이터를 처리하는 서비스 입니다.
 */

public interface PowerGenerationService {

    List<InfluxDataResponse> getWeekPowerGenerationData(String measurement, String field);

    void savePowerGenerationData(String tagValue, double powerData);
}
