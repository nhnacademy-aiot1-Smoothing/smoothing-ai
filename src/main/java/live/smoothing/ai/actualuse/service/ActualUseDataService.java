package live.smoothing.ai.actualuse.service;

import live.smoothing.ai.common.dto.InfluxDataResponse;

import java.util.List;

public interface ActualUseDataService {

    List<InfluxDataResponse> getTodayActualUseData(String location, String description);
}
