package live.smoothing.ai.prediction.service;

import live.smoothing.ai.common.dto.InfluxDataResponse;
import live.smoothing.ai.prediction.entity.PredictionData;
import live.smoothing.ai.prediction.repository.PredictionDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PredictionDataServiceImpl implements PredictionDataService {

    private final PredictionDataRepository repository;

    @Override
    public List<InfluxDataResponse> getPredictionData(String measurement, String field) {

        List<PredictionData> predictionDataList = repository.getTodayPredictionData(measurement, field);

        return predictionDataList.stream()
                .map(data -> new InfluxDataResponse(data.getTime(), data.getValue()))
                .collect(Collectors.toList());
    }
}
