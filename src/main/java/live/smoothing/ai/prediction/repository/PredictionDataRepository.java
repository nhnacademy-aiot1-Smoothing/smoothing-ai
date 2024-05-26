package live.smoothing.ai.prediction.repository;

import live.smoothing.ai.prediction.entity.PredictionData;

import java.util.List;

public interface PredictionDataRepository {

    List<PredictionData> getTodayPredictionData(String measurement, String field);
}
