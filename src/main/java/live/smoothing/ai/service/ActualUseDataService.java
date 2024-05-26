package live.smoothing.ai.service;

import live.smoothing.ai.dto.InfluxDataResponse;

import java.util.List;

public interface ActualUseDataService {

    List<InfluxDataResponse> getWeekActualUseData(String location, String description);
}
