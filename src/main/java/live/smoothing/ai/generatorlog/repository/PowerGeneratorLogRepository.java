package live.smoothing.ai.generatorlog.repository;

import live.smoothing.ai.generatorlog.entity.PowerGeneratorLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PowerGeneratorLogRepository extends JpaRepository<PowerGeneratorLog, Long> {

    List<PowerGeneratorLog> findTop8ByPowerGenerator_GeneratorIdOrderByTimeAsc(String generatorId);
}
