package live.smoothing.ai.controller;

import live.smoothing.ai.common.dto.InfluxDataResponse;
import live.smoothing.ai.generation.service.PowerGenerationService;
import live.smoothing.ai.task.ScheduledTasks;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/power-generation")
public class PowerGenerationController {

    private final PowerGenerationService powerGenerationService;
    private final ScheduledTasks scheduledTasks;

    @GetMapping
    public ResponseEntity<List<InfluxDataResponse>> getPowerGenerationData(@RequestParam String measurement,
                                                                           @RequestParam String field) {

        List<InfluxDataResponse> weekPowerGenerationData = powerGenerationService.getWeekPowerGenerationData(measurement, field);

        return ResponseEntity.ok(weekPowerGenerationData);
    }

    @PostMapping("/start")
    public ResponseEntity<String> startPowerGeneration() {

        scheduledTasks.schedulePowerGenerationStart();
        return ResponseEntity.ok("발전기 정상 작동");
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stopPowerGeneration() {

        scheduledTasks.stopGenerators();
        return ResponseEntity.ok("발전기 정상 종료");
    }
}
