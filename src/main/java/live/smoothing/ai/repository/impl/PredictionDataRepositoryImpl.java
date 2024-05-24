package live.smoothing.ai.repository.impl;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.write.Point;
import com.influxdb.query.dsl.Flux;
import live.smoothing.ai.entity.PredictionData;
import live.smoothing.ai.repository.PredictionDataRepository;
import live.smoothing.ai.util.TimeProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static live.smoothing.ai.util.FluxUtil.getSearchData;
import static live.smoothing.ai.util.FluxUtil.writeData;

@Repository
@RequiredArgsConstructor
public class PredictionDataRepositoryImpl implements PredictionDataRepository {

    private final InfluxDBClient influxDBClient;
    private final TimeProvider timeProvider;
    private static final String BUCKET = "ai_service_data";
    private static final String ORG = "smoothing";

    @Override
    public List<PredictionData> getTodayPredictionData(String measurement, String field) {
        Flux query = getSearchData(BUCKET, measurement, field, timeProvider.startOfToday(), timeProvider.endOfToday());
        return influxDBClient.getQueryApi().query(query.toString(), PredictionData.class);
    }

    @Override
    public void saveData(String measurement, String tag, String tagValue, String field, double powerData) {
        Point point = writeData(
                measurement,
                tag, tagValue,
                field, powerData,
                timeProvider.now().toInstant());
        try {
            influxDBClient.getWriteApiBlocking().writePoint(BUCKET, ORG, point);
        } catch (Exception e) {
            System.err.println("InfluxDB에 데이터 저장 실패 : " + e.getMessage());
        }
    }
}
