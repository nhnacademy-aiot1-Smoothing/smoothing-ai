package live.smoothing.ai.util;

import org.springframework.stereotype.Component;

import java.time.*;

@Component
public class CurrentTimeProvider implements TimeProvider {

    private static final ZoneId SEOUL_ZONE_ID = ZoneId.of("Asia/Seoul");

    @Override
    public ZonedDateTime now() {
        return ZonedDateTime.now(SEOUL_ZONE_ID);
    }

    @Override
    public Instant startOfToday() {
        ZonedDateTime startOfDay = now().toLocalDate().atStartOfDay(SEOUL_ZONE_ID);
        return startOfDay.toInstant();
    }

    @Override
    public Instant endOfToday() {
        ZonedDateTime endOfDay = now().toLocalDate().plusDays(1).atTime(0, 0, 0).atZone(SEOUL_ZONE_ID);
        return endOfDay.toInstant();
    }

    @Override
    public Instant startOfWeek() {
        ZonedDateTime startOfWeek = now().toLocalDate().minusDays(6).atStartOfDay(SEOUL_ZONE_ID);
        return startOfWeek.toInstant();
    }
}
