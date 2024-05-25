package live.smoothing.ai.service.impl;

import live.smoothing.ai.dto.InfluxDataResponse;
import live.smoothing.ai.entity.PowerGenerationData;
import live.smoothing.ai.repository.PowerGenerationDataRepository;
import live.smoothing.ai.service.PowerGenerationService;
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

    private static final String MEASUREMENT_GENERATION = "generation";
    private static final String TAG_GENERATOR = "generator";
    private static final String FIELD_CHARGE_POWER = "charge_power";

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
                MEASUREMENT_GENERATION,
                TAG_GENERATOR,
                tagValue,
                FIELD_CHARGE_POWER,
                powerData
        );
    }
}