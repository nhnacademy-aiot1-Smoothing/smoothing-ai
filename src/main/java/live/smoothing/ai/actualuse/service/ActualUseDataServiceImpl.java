package live.smoothing.ai.actualuse.service;

import live.smoothing.ai.common.dto.InfluxDataResponse;
import live.smoothing.ai.actualuse.entity.ActualUseData;
import live.smoothing.ai.actualuse.repository.ActualUseDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActualUseDataServiceImpl implements ActualUseDataService {

    private final ActualUseDataRepository repository;

    @Override
    public List<InfluxDataResponse> getTodayActualUseData(String location, String description) {

        List<ActualUseData> todayActualUseData = repository.getTodayActualUseData(location, description);

        Map<Instant, Double> hourlySum = todayActualUseData.stream()
                .collect(Collectors.groupingBy(
                        data -> data.getTime().truncatedTo(ChronoUnit.HOURS),
                        Collectors.summingDouble(data -> data.getValue() != null ? data.getValue() : 0.0)
                ));

        return hourlySum.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> new InfluxDataResponse(entry.getKey(), (double) Math.round(entry.getValue() / 60)))
                .collect(Collectors.toList());
    }
}
