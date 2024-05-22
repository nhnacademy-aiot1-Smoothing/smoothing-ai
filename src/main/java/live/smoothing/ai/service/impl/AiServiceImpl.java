package live.smoothing.ai.service.impl;

import live.smoothing.ai.dto.PredictionDataResponse;
import live.smoothing.ai.entity.PredictionData;
import live.smoothing.ai.repository.PredictionDataRepository;
import live.smoothing.ai.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AiServiceImpl implements AiService {

    private final PredictionDataRepository repository;

    @Override
    public List<PredictionDataResponse> getPredictionData(String measurement, String field) {

        List<PredictionData> predictionDataList = repository.get24HourPredictionData(measurement, field);

        return predictionDataList.stream()
                .map(data -> new PredictionDataResponse(data.getTime(), data.getValue()))
                .collect(Collectors.toList());
    }
}
