package live.smoothing.ai.repository.impl;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.query.dsl.Flux;
import live.smoothing.ai.entity.PredictionData;
import live.smoothing.ai.repository.PredictionDataRepository;
import live.smoothing.ai.util.TimeProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static live.smoothing.ai.util.InfluxUtil.getSearchData;

@Repository
@RequiredArgsConstructor
public class PredictionDataRepositoryImpl implements PredictionDataRepository {

    private final InfluxDBClient influxDBClient;
    private final TimeProvider timeProvider;
    private static final String BUCKET = "ai_service_data";

    @Override
    public List<PredictionData> getTodayPredictionData(String measurement, String field) {

        Flux query = getSearchData(
                BUCKET,
                measurement,
                field,
                timeProvider.startOfToday(), timeProvider.endOfToday()
        );

        return influxDBClient.getQueryApi().query(query.toString(), PredictionData.class);
    }
}
