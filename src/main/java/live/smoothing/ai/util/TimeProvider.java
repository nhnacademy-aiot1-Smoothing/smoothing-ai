package live.smoothing.ai.util;

import java.time.Instant;
import java.time.LocalDateTime;

public interface TimeProvider {

    LocalDateTime now();

    Instant startOfToday();

    Instant endOfToday();
}
