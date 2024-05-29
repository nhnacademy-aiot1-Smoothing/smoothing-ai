package live.smoothing.ai.generator.util;

import live.smoothing.ai.generation.service.PowerGenerationService;
import live.smoothing.ai.generatorlog.service.PowerGeneratorLogService;

public class VirtualGenerator implements Runnable {

    private static final long DEFAULT_INTERVAL = 5000;
    private static final double GENERATED_POWER = 135;

    private final PowerGenerationService generationService;
    private final PowerGeneratorLogService generatorLogService;
    private final GeneratorControl control;
    private final String generatorId;
    private final double target;
    private final Thread thread;

    public VirtualGenerator(int generatorNum, double target, GeneratorControl control ,PowerGenerationService generationService, PowerGeneratorLogService generatorLogService) {
        this.generatorId = "generator_" + generatorNum;
        this.target = target;
        this.control = control;
        this.generationService = generationService;
        this.generatorLogService = generatorLogService;
        this.thread = new Thread(this, generatorId);
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        thread.interrupt();
    }

    public boolean isRunning() {
        return thread.isAlive();
    }

    private void preprocess() {
        generatorLogService.savePowerGeneratorLog(generatorId, "발전기 동작");
    }

    private void process() {
        control.addCurrentSum(GENERATED_POWER);
        generationService.savePowerGenerationData(generatorId, GENERATED_POWER);
        if (control.getCurrentSum() >= target) {
            control.updateTargetReached(true);
        }
    }

    private void postprocess() {
        generatorLogService.savePowerGeneratorLog(generatorId, "발전기 정지");
    }

    @Override
    public void run() {
        preprocess();

        try {
            while (!control.getReachedTarget() && !Thread.currentThread().isInterrupted()) {
                process();
                Thread.sleep(DEFAULT_INTERVAL);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            postprocess();
        }
    }
}
