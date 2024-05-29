package live.smoothing.ai.actualuse.repository;

import live.smoothing.ai.actualuse.entity.ActualUseData;

import java.util.List;

public interface ActualUseDataRepository {

    List<ActualUseData> getTodayActualUseData(String location, String description);
}
