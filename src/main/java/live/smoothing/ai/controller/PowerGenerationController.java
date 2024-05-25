package live.smoothing.ai.controller;

import live.smoothing.ai.dto.PowerGenerationDataResponse;
import live.smoothing.ai.service.PowerGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 발전기 조회 데이터를 반환하는 RESTAPI Controller 입니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/power-generation")
public class PowerGenerationController {

    private final PowerGenerationService powerGenerationService;

    @GetMapping
    public List<PowerGenerationDataResponse> getPowerGenerationData(@RequestParam String measurement,
                                                                    @RequestParam String field) {
        return powerGenerationService.getPowerGenerationData(measurement, field);
    }
}
