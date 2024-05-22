package live.smoothing.ai.controller;

import live.smoothing.ai.dto.PredictionDataResponse;
import live.smoothing.ai.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;

    @GetMapping
    public List<PredictionDataResponse> getDailyPredictedPowerUsage(@RequestParam String measurement,
                                                                    @RequestParam String field) {

        return aiService.getPredictionData(measurement, field);
    }
}
