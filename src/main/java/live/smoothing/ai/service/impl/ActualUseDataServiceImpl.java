package live.smoothing.ai.service.impl;

import live.smoothing.ai.dto.InfluxDataResponse;
import live.smoothing.ai.entity.ActualUseData;
import live.smoothing.ai.repository.ActualUseDataRepository;
import live.smoothing.ai.service.ActualUseDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActualUseDataServiceImpl implements ActualUseDataService {

    private final ActualUseDataRepository repository;

    @Override
    public List<InfluxDataResponse> getWeekActualUseData(String location, String description) {

        List<ActualUseData> weekActualUseData = repository.getWeekActualUseData(location, description);

        return weekActualUseData.stream()
                .map(data -> {
                    double roundedValue = Math.round(data.getValue() / 3600 * 1000) / 1000.0;
                    return new InfluxDataResponse(data.getTime(), roundedValue);
                }).collect(Collectors.toList());
    }
}
