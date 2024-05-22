package live.smoothing.ai.util;

import com.influxdb.query.dsl.Flux;
import com.influxdb.query.dsl.functions.restriction.Restrictions;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class FluxUtil {

    private FluxUtil() {}

    /**
     * 특정 측정값과 필드를 기준으로 데이터를 조회하는 InfluxDB Flux 쿼리를 생성한다.
     *
     * @param bucketName 버킷 이름
     * @param measurementName 측정값 이름 (예: "power_usage")
     * @param fieldName 필드 이름 (예: "socket_power")
     * @param start 시작 시간
     * @return Flux 쿼리
     */
    public static Flux getSearchData(String bucketName,
                                         String measurementName,
                                         String fieldName,
                                         Instant start,
                                         Instant stop
    ) {
        return Flux.from(bucketName)
                .range(start, stop)
                .filter(Restrictions.measurement().equal(measurementName))
                .filter(Restrictions.field().equal(fieldName))
                .timeShift(9L, ChronoUnit.HOURS);
    }
}
