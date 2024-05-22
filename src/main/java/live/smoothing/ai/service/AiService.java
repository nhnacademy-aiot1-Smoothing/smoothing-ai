package live.smoothing.ai.service;

import live.smoothing.ai.dto.PredictionDataResponse;

import java.util.List;

public interface AiService {

    List<PredictionDataResponse> getPredictionData(String measurement, String field);
}
