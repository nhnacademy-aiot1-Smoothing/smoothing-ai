package live.smoothing.ai.generator.repository;

import live.smoothing.ai.generator.dto.PowerGeneratorIdResponse;
import live.smoothing.ai.generator.entity.PowerGenerator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PowerGeneratorRepository extends JpaRepository<PowerGenerator, String> {

    List<PowerGeneratorIdResponse> findAllBy();
}
