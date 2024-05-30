package live.smoothing.ai.prediction.service;

import live.smoothing.ai.common.dto.InfluxDataResponse;
import java.util.List;

public interface PredictionDataService {

    List<InfluxDataResponse> getPredictionData(String measurement, String field);
}
