package live.smoothing.ai.controller;

import live.smoothing.ai.common.dto.InfluxDataResponse;
import live.smoothing.ai.actualuse.service.ActualUseDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai/actual-use-data")
public class ActualUseDataController {

    private final ActualUseDataService actualUseDataService;

    @GetMapping
    public ResponseEntity<List<InfluxDataResponse>> getActualUseData(@RequestParam String location,
                                                                     @RequestParam String description) {

        List<InfluxDataResponse> weekActualUseData = actualUseDataService.getTodayActualUseData(location, description);

        return ResponseEntity.ok(weekActualUseData);
    }
}
