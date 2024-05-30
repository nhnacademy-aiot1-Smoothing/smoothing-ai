package live.smoothing.ai.actualuse.repository;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.query.dsl.Flux;
import live.smoothing.ai.actualuse.entity.ActualUseData;
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
    public List<ActualUseData> getTodayActualUseData(String location, String description) {

        Flux query = getActualUseData(
                BUCKET,
                PHASE,
                location,
                description,
                timeProvider.startOfToday(), timeProvider.endOfToday(),
                1L
        );
        return influxDBClient.getQueryApi().query(query.toString(), ActualUseData.class);
    }
}
