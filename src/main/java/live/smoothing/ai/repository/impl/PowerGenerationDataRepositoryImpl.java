package live.smoothing.ai.repository.impl;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.query.dsl.Flux;
import live.smoothing.ai.entity.PowerGenerationData;
import live.smoothing.ai.repository.PowerGenerationDataRepository;
import live.smoothing.ai.util.TimeProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static live.smoothing.ai.util.FluxUtil.getSearchData;

/**
 * InfluxDB에서 발전기 데이터를 조회하는 로직을 포함합니다.
 */
@Repository
@RequiredArgsConstructor
public class PowerGenerationDataRepositoryImpl implements PowerGenerationDataRepository {

    private final InfluxDBClient aiInfluxDBClient;
    private final TimeProvider timeProvider;
    private final String BUCKET = "ai_servce_data";

    @Override
    public List<PowerGenerationData> getPowerGenerationData(String measurement, String field) {
        Flux query = getSearchData(BUCKET, measurement, field, timeProvider.startOfToday(), timeProvider.endOfToday());
        return aiInfluxDBClient.getQueryApi().query(query.toString(), PowerGenerationData.class);
    }
}
