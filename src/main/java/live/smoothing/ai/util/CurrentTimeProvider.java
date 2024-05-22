package live.smoothing.ai.util;

import org.springframework.stereotype.Component;

import java.time.*;

@Component
public class CurrentTimeProvider implements TimeProvider {

    private static final ZoneId SEOUL_ZONE_ID = ZoneId.of("Asia/Seoul");

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }

    @Override
    public Instant startOfToday() {
        LocalDateTime startOfDay = LocalDateTime.of(now().toLocalDate(), LocalTime.MIN);
        ZonedDateTime zonedStartOfDay = startOfDay.atZone(SEOUL_ZONE_ID);
        return zonedStartOfDay.toInstant();
    }

    @Override
    public Instant endOfToday() {
        LocalDateTime endOfDay = LocalDateTime.of(now().toLocalDate().plusDays(1), LocalTime.of(1, 0));
        ZonedDateTime zonedEndOfDay = endOfDay.atZone(SEOUL_ZONE_ID);
        return zonedEndOfDay.toInstant();
    }
}
