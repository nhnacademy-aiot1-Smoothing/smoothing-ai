package live.smoothing.ai.actualuse.service;

import live.smoothing.ai.common.dto.InfluxDataResponse;
import live.smoothing.ai.actualuse.entity.ActualUseData;
import live.smoothing.ai.actualuse.repository.ActualUseDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActualUseDataServiceImpl implements ActualUseDataService {

    private final ActualUseDataRepository repository;

    @Override
    public List<InfluxDataResponse> getTodayActualUseData(String location, String description) {

        List<ActualUseData> todayActualUseData = repository.getTodayActualUseData(location, description);

        return todayActualUseData.stream()
                .map(data -> {
                    Double calculatedValue = (data.getValue() != null) ? Math.round(data.getValue() / 360 * 1000) / 1000.0 : null;
                    return new InfluxDataResponse(data.getTime(), calculatedValue);
                })
                .collect(Collectors.toList());
    }
}
