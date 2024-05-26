package live.smoothing.ai.controller;

import live.smoothing.ai.dto.InfluxDataResponse;
import live.smoothing.ai.service.PowerGenerationService;
import live.smoothing.ai.task.ScheduledTasks;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 발전기 조회 데이터를 반환하는 RESTAPI Controller 입니다.
 */
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

        scheduledTasks.startPowerGeneration();
        return ResponseEntity.ok("발전기 정상 작동");
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stopPowerGeneration() {

        scheduledTasks.stopPowerGeneration();
        return ResponseEntity.ok("발전기 정상 종료");
    }
}
