package live.smoothing.ai.repository;

import live.smoothing.ai.entity.PredictionData;
import java.util.List;

public interface PredictionDataRepository {

    List<PredictionData> get24HourPredictionData(String measurement, String field);
}
