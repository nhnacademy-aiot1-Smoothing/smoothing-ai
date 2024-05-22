package live.smoothing.ai.service.impl;

import live.smoothing.ai.dto.PowerGenerationDataResponse;
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

    private final PowerGenerationDataRepository repository;

    @Override
    public List<PowerGenerationDataResponse> getPowerGenerationData(String measurement, String field) {
        List<PowerGenerationData> powerGenerationDataList = repository.getPowerGenerationData(measurement, field);

        return powerGenerationDataList.stream()
                .map(data -> new PowerGenerationDataResponse(data.getTime(), data.getValue()))
                .collect(Collectors.toList());
    }
}