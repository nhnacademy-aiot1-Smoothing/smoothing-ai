package live.smoothing.ai.actualuse.repository;

import live.smoothing.ai.actualuse.dto.ActualUseData;

import java.util.List;

public interface ActualUseDataRepository {

    List<ActualUseData> getWeekActualUseData(String location, String description);
}
