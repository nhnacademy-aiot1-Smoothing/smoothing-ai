package live.smoothing.ai.util;

import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.dsl.Flux;
import com.influxdb.query.dsl.functions.restriction.Restrictions;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class InfluxUtil {

    private InfluxUtil() {
    }

    /**
     * 특정 측정값과 필드를 기준으로 데이터를 조회하는 InfluxDB Flux 쿼리를 생성한다.
     *
     * @param bucketName      버킷 이름
     * @param measurementName 측정값 이름
     * @param fieldName       필드 이름
     * @param start           시작 시간
     * @param stop            종료 시간
     * @return Flux 쿼리
     */
    public static Flux getPredictionData(String bucketName,
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

    public static Flux getPowerGenerationData(String bucketName,
                                              String measurementName,
                                              String fieldName,
                                              Instant start,
                                              Instant stop,
                                              long every
    ) {
        return Flux.from(bucketName)
                .range(start, stop)
                .filter(Restrictions.measurement().equal(measurementName))
                .filter(Restrictions.field().equal(fieldName))
                .groupBy("charge_power")
                .timeShift(-15L, ChronoUnit.HOURS)
                .aggregateWindow(every, ChronoUnit.HOURS, "sum");
    }

    public static Flux getActualUseData(String bucketName,
                                        String phase,
                                        String location,
                                        String description,
                                        Instant start,
                                        Instant stop,
                                        long every
    ) {
        return Flux.from(bucketName)
                .range(start, stop)
                .filter(Restrictions.tag("phase").equal(phase))
                .filter(Restrictions.or(
                    Restrictions.tag("location").equal(location + "_1"),
                    Restrictions.tag("location").equal(location + "_2")))
                .filter(Restrictions.tag("description").equal(description))
                .groupBy(description)
                .timeShift(8L, ChronoUnit.HOURS)
                .aggregateWindow(every, ChronoUnit.HOURS, "sum");
    }

    /**
     * InfluxDB에 데이터를 저장히기 위한 Point 객체를 생성한다.
     *
     * @param measurementName 측정값 이름
     * @param tagName         태그 이름
     * @param tagValue        태그 값
     * @param fieldName       필드 이름
     * @param fieldValue      필드 값
     * @param time            시간
     * @return Point 객체
     */
    public static Point writeData(String measurementName,
                                  String tagName,
                                  String tagValue,
                                  String fieldName,
                                  double fieldValue,
                                  Instant time
    ) {
        return Point.measurement(measurementName)
                .addTag(tagName, tagValue)
                .addField(fieldName, fieldValue)
                .time(time, WritePrecision.NS);
    }
}
