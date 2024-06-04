package live.smoothing.ai.generatorlog.service;

import live.smoothing.ai.generator.entity.PowerGenerator;
import live.smoothing.ai.generator.repository.PowerGeneratorRepository;
import live.smoothing.ai.generatorlog.dto.PowerGeneratorLogResponse;
import live.smoothing.ai.generatorlog.entity.PowerGeneratorLog;
import live.smoothing.ai.generatorlog.repository.PowerGeneratorLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PowerGeneratorLogServiceImpl implements PowerGeneratorLogService {

    private final PowerGeneratorRepository powerGeneratorRepository;
    private final PowerGeneratorLogRepository powerGeneratorLogRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PowerGeneratorLogResponse> getPowerGeneratorLogs(String generatorId) {

        List<PowerGeneratorLog> powerGeneratorLogs = powerGeneratorLogRepository.findTop6ByPowerGenerator_GeneratorIdOrderByTimeDesc(generatorId);

        powerGeneratorLogs.sort(Comparator.comparing(PowerGeneratorLog::getTime));

        return powerGeneratorLogs.stream()
                .map(data -> new PowerGeneratorLogResponse(data.getTime(), data.getMessage()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void savePowerGeneratorLog(String generatorId, String message) {

        PowerGenerator powerGenerator = powerGeneratorRepository.getReferenceById(generatorId);

        powerGeneratorLogRepository.save(new PowerGeneratorLog(
                powerGenerator,
                LocalDateTime.now(),
                message
        ));
    }
}
