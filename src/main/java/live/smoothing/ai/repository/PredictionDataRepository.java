package live.smoothing.ai.repository;

import live.smoothing.ai.entity.PredictionData;

import java.util.List;

public interface PredictionDataRepository {

    List<PredictionData> getTodayPredictionData(String measurement, String field);

    void saveData(String measurement, String tag, String tagValue, String field, double powerData);
}
