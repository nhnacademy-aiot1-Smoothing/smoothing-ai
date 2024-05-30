package live.smoothing.ai.generator.service;

import live.smoothing.ai.generator.dto.PowerGeneratorIdResponse;

import java.util.List;

public interface PowerGeneratorService {

    List<PowerGeneratorIdResponse> getPowerGenerators();
}
