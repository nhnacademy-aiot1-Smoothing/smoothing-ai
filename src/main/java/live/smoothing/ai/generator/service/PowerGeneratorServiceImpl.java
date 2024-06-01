package live.smoothing.ai.generator.service;

import live.smoothing.ai.generator.dto.PowerGeneratorIdResponse;
import live.smoothing.ai.generator.repository.PowerGeneratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PowerGeneratorServiceImpl implements PowerGeneratorService {

    private final PowerGeneratorRepository repository;

    @Override
    public List<PowerGeneratorIdResponse> getPowerGenerators() {

        return repository.findAllBy();
    }
}
