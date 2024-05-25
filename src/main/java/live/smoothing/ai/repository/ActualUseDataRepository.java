package live.smoothing.ai.repository;

import live.smoothing.ai.entity.ActualUseData;

import java.util.List;

public interface ActualUseDataRepository {

    List<ActualUseData> getWeekActualUseData(String location, String description);
}
