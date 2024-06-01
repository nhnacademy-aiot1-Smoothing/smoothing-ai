package live.smoothing.ai.controller;

import live.smoothing.ai.generator.dto.PowerGeneratorIdResponse;
import live.smoothing.ai.generator.service.PowerGeneratorService;
import live.smoothing.ai.generatorlog.dto.PowerGeneratorLogResponse;
import live.smoothing.ai.generatorlog.service.PowerGeneratorLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai/power-generator")
public class PowerGeneratorController {

    private final PowerGeneratorService powerGeneratorService;
    private final PowerGeneratorLogService powerGeneratorLogService;

    @GetMapping("/list")
    public ResponseEntity<List<PowerGeneratorIdResponse>> getPowerGenerators() {

        List<PowerGeneratorIdResponse> powerGenerators = powerGeneratorService.getPowerGenerators();

        return ResponseEntity.ok(powerGenerators);
    }

    @GetMapping("/log")
    public ResponseEntity<List<PowerGeneratorLogResponse>> getPowerGeneratorLogs(@RequestParam String generatorId) {

        List<PowerGeneratorLogResponse> powerGeneratorLogs = powerGeneratorLogService.getPowerGeneratorLogs(generatorId);

        return ResponseEntity.ok(powerGeneratorLogs);
    }
}
