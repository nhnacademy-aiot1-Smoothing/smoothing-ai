package live.smoothing.ai.controller;

import live.smoothing.ai.common.dto.InfluxDataResponse;
import live.smoothing.ai.prediction.service.PredictionDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class PredictionDataController {

    private final PredictionDataService predictionDataService;

    @GetMapping
    public ResponseEntity<List<InfluxDataResponse>> getDailyPredictedPowerUsage(@RequestParam String measurement,
                                                                                @RequestParam String field) {

        List<InfluxDataResponse> predictionData = predictionDataService.getPredictionData(measurement, field);

        return ResponseEntity.ok(predictionData);
    }
}
