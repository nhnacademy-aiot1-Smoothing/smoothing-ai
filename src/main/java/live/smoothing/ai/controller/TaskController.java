package live.smoothing.ai.controller;

import live.smoothing.ai.task.ScheduledTasks;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/power-generation")
public class TaskController {

    private final ScheduledTasks scheduledTasks;

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
