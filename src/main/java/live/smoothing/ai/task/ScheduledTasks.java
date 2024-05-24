package live.smoothing.ai.task;

import com.google.common.util.concurrent.AtomicDouble;
import live.smoothing.ai.dto.PredictionDataResponse;
import live.smoothing.ai.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private static final int THREAD_POOL_SIZE = 3;
    private static final int THREAD_SLEEP_MINUTE = 5;

    private final AiService aiService;
    private ExecutorService executorService;
    private AtomicBoolean reachedTarget;

    @Scheduled(cron = "0 0 1 * * ?")
    public void startPowerGeneration() {

        String measurement = "power_usage";
        String field = "socket_power";

        List<PredictionDataResponse> predictionData = aiService.getPredictionData(measurement, field);
        double sum = predictionData.stream()
                .filter(prediction -> prediction.getValue() != null)
                .mapToDouble(PredictionDataResponse::getValue)
                .sum();

        double target = sum * 1.1;

        AtomicDouble currentSum = new AtomicDouble(0.0);
        executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        reachedTarget = new AtomicBoolean(false);

        for (int i = 0; i < THREAD_POOL_SIZE; i++) {
            int generatorNum = i;
            executorService.submit(() -> generatePowerUntil(generatorNum, currentSum, target));
        }
    }

    public void stopPowerGeneration() {
        reachedTarget.set(true);
        executorService.shutdown();
    }

    private void generatePowerUntil(int generatorNum, AtomicDouble currentSum, double target) {
        long millis = THREAD_SLEEP_MINUTE * 60L * 1000L;

        while (!reachedTarget.get()) {
            double generatedPower = 135;
            currentSum.addAndGet(generatedPower);

            aiService.saveGeneratorData("generator_" + generatorNum, generatedPower);

            if (currentSum.get() >= target) {
                reachedTarget.set(true);
            }

            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @PreDestroy
    public void cleanUp() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, java.util.concurrent.TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}