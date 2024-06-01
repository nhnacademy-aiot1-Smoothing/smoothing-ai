package live.smoothing.ai.task;

import live.smoothing.ai.common.dto.InfluxDataResponse;
import live.smoothing.ai.generation.service.PowerGenerationService;
import live.smoothing.ai.generator.util.GeneratorControl;
import live.smoothing.ai.generator.util.VirtualGenerator;
import live.smoothing.ai.generatorlog.service.PowerGeneratorLogService;
import live.smoothing.ai.prediction.service.PredictionDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final PredictionDataService predictionDataService;
    private final PowerGenerationService generationService;
    private final PowerGeneratorLogService generatorLogService;
    private final GeneratorControl generatorControl;

    private VirtualGenerator generator1;
    private VirtualGenerator generator2;
    private VirtualGenerator generator3;

    @Scheduled(cron = "0 0 1 * * ?")
    public void schedulePowerGenerationStart() {

        double target = calculateTarget();
        initializeGenerators(target);
        startGenerators();
    }

    private double calculateTarget() {

        String measurement = "power_usage";
        String field = "socket_power";
        List<InfluxDataResponse> predictionData = predictionDataService.getPredictionData(measurement, field);
        double sum = predictionData.stream()
                .filter(prediction -> prediction.getValue() != null)
                .mapToDouble(InfluxDataResponse::getValue)
                .sum();
        return sum * 1.1;
    }

    private void initializeGenerators(double target) {

        generatorControl.reset();
        generator1 = new VirtualGenerator(1, target, generatorControl, generationService, generatorLogService);
        generator2 = new VirtualGenerator(2, target, generatorControl, generationService, generatorLogService);
        generator3 = new VirtualGenerator(3, target, generatorControl, generationService, generatorLogService);
    }

    private void startGenerators() {

        generator1.start();
        generator2.start();
        generator3.start();
    }

    public void stopGenerators() {

        generator1.stop();
        generator2.stop();
        generator3.stop();
    }
}