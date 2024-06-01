package live.smoothing.ai.config;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import live.smoothing.ai.prop.AiInfluxDBProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InfluxDBConfig {

    private final AiInfluxDBProperties aiInfluxDBProperties;

    @Bean
    public InfluxDBClient influxDBClient() {
        return InfluxDBClientFactory
                .create(
                        aiInfluxDBProperties.getUrl(),
                        aiInfluxDBProperties.getToken().toCharArray(),
                        aiInfluxDBProperties.getOrg()
                );
    }
}
