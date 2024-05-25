package live.smoothing.ai.service.impl;

import live.smoothing.ai.dto.InfluxDataResponse;
import live.smoothing.ai.entity.PredictionData;
import live.smoothing.ai.repository.PredictionDataRepository;
import live.smoothing.ai.service.PredictionDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PredictionDataImpl implements PredictionDataService {

    private final PredictionDataRepository repository;

    @Override
    public List<InfluxDataResponse> getPredictionData(String measurement, String field) {

        List<PredictionData> predictionDataList = repository.getTodayPredictionData(measurement, field);

        return predictionDataList.stream()
                .map(data -> new InfluxDataResponse(data.getTime(), data.getValue()))
                .collect(Collectors.toList());
    }
}
