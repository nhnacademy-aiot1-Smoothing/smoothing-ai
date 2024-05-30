package live.smoothing.ai.generatorlog.service;

import live.smoothing.ai.generatorlog.dto.PowerGeneratorLogResponse;

import java.util.List;

public interface PowerGeneratorLogService {

    List<PowerGeneratorLogResponse> getPowerGeneratorLogs(String generatorId);

    void savePowerGeneratorLog(String generatorId, String message);
}
