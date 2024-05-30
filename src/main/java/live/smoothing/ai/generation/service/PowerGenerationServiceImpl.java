package live.smoothing.ai.generation.service;

import live.smoothing.ai.common.dto.InfluxDataResponse;
import live.smoothing.ai.generation.entity.PowerGenerationData;
import live.smoothing.ai.generation.repository.PowerGenerationDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * PowerGenerationServiceImpl 클래스는 PowerGenerationService 인터페이스이 구현체로, 발전기 데이터 관련 비즈니스 로직을 처리 합니다.
 * PowerGenerationDataRepository를 사용해여 InfluxDB에서 데이터를 조회 합니다.
 */
@Service
@RequiredArgsConstructor
public class PowerGenerationServiceImpl implements PowerGenerationService {

    private static final String MEASUREMENT = "generation";
    private static final String TAG = "generator";
    private static final String FIELD = "charge_power";

    private final PowerGenerationDataRepository repository;

    @Override
    public List<InfluxDataResponse> getWeekPowerGenerationData(String measurement, String field) {

        List<PowerGenerationData> powerGenerationDataList = repository.getWeekPowerGenerationData(measurement, field);

        return powerGenerationDataList.stream()
                .map(data -> new InfluxDataResponse(data.getTime(), data.getValue()))
                .collect(Collectors.toList());

    }

    @Override
    public void savePowerGenerationData(String tagValue, double powerData) {

        repository.savePowerGenerationData(
                MEASUREMENT,
                TAG,
                tagValue,
                FIELD,
                powerData
        );
    }
}