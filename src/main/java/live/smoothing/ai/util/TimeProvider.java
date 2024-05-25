package live.smoothing.ai.util;

import java.time.Instant;
import java.time.ZonedDateTime;

public interface TimeProvider {

    ZonedDateTime now();

    Instant startOfToday();

    Instant endOfToday();

    Instant startOfWeek();
}
