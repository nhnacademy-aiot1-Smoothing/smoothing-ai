package live.smoothing.ai.generation.repository;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.write.Point;
import com.influxdb.exceptions.InfluxException;
import com.influxdb.query.dsl.Flux;
import live.smoothing.ai.generation.entity.PowerGenerationData;
import live.smoothing.ai.util.TimeProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static live.smoothing.ai.util.InfluxUtil.*;

/**
 * InfluxDB에서 발전기 데이터를 조회하는 로직을 포함합니다.
 */
@Repository
@RequiredArgsConstructor
public class PowerGenerationDataRepositoryImpl implements PowerGenerationDataRepository {

    private final InfluxDBClient influxDBClient;
    private final TimeProvider timeProvider;
    private static final String BUCKET = "ai_service_data";
    private static final String ORG = "smoothing";

    @Override
    public List<PowerGenerationData> getWeekPowerGenerationData(String measurement, String field) {

        Flux query = getPowerGenerationData(
                BUCKET,
                measurement,
                field,
                timeProvider.startOfWeek(), timeProvider.endOfToday(),
                24L
        );
        return influxDBClient.getQueryApi().query(query.toString(), PowerGenerationData.class);
    }

    @Override
    public void savePowerGenerationData(String measurement, String tag, String tagValue, String field, double powerData) {

        Point point = writeData(
                measurement,
                tag, tagValue,
                field, powerData,
                timeProvider.now().toInstant()
        );

        try {
            influxDBClient.getWriteApiBlocking().writePoint(BUCKET, ORG, point);
        } catch (InfluxException e) {
            throw new InfluxException(e);
        }
    }
}