package live.smoothing.ai.service;

import live.smoothing.ai.dto.InfluxDataResponse;
import java.util.List;

public interface PredictionDataService {

    List<InfluxDataResponse> getPredictionData(String measurement, String field);
}
