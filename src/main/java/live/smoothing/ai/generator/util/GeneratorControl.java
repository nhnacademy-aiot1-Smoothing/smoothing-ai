package live.smoothing.ai.generator.util;

import com.google.common.util.concurrent.AtomicDouble;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class GeneratorControl {

    private final AtomicBoolean reachedTarget = new AtomicBoolean(false);
    private final AtomicDouble currentSum = new AtomicDouble(0.0);

    public boolean getReachedTarget() {
        return reachedTarget.get();
    }

    public void updateTargetReached(boolean reached) {
        reachedTarget.set(reached);
    }

    public double getCurrentSum() {
        return currentSum.get();
    }

    public void addCurrentSum(double value) {
        currentSum.addAndGet(value);
    }

    public void reset() {
        currentSum.set(0.0);
        reachedTarget.set(false);
    }
}