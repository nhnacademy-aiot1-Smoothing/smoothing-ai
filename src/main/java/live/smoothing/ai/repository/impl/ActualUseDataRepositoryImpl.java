package live.smoothing.ai.repository.impl;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.query.dsl.Flux;
import live.smoothing.ai.entity.ActualUseData;
import live.smoothing.ai.repository.ActualUseDataRepository;
import live.smoothing.ai.util.TimeProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

import static live.smoothing.ai.util.InfluxUtil.getActualUseData;

@Repository
@RequiredArgsConstructor
public class ActualUseDataRepositoryImpl implements ActualUseDataRepository {

    private final InfluxDBClient influxDBClient;
    private final TimeProvider timeProvider;
    private static final String BUCKET = "powermetrics_data";
    private static final String PHASE = "total";

    @Override
    public List<ActualUseData> getWeekActualUseData(String location, String description) {

        Flux query = getActualUseData(
                BUCKET,
                PHASE,
                location,
                description,
                timeProvider.startOfWeek(), timeProvider.endOfToday(),
                24L
        );
        return influxDBClient.getQueryApi().query(query.toString(), ActualUseData.class);
    }
}
