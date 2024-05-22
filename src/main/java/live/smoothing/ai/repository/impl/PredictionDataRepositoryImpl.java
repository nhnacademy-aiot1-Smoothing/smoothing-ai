package live.smoothing.ai.repository.impl;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.query.dsl.Flux;
import live.smoothing.ai.entity.PredictionData;
import live.smoothing.ai.repository.PredictionDataRepository;
import live.smoothing.ai.util.TimeProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static live.smoothing.ai.util.FluxUtil.getSearchData;


@Repository
@RequiredArgsConstructor
public class PredictionDataRepositoryImpl implements PredictionDataRepository {

    private final InfluxDBClient aiInfluxDBClient;
    private final TimeProvider timeProvider;
    private final String BUCKET = "power_ml_data";

    @Override
    public List<PredictionData> get24HourPredictionData(String measurement, String field) {
        Flux query = getSearchData(BUCKET, measurement, field, timeProvider.startOfToday(), timeProvider.endOfToday());
        return aiInfluxDBClient.getQueryApi().query(query.toString(), PredictionData.class);
    }
}
