package go.utic.cctv.config;

import go.utic.cctv.mapper.CctvApiMapper;
import go.utic.cctv.service.CctvApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DatabaseConfig {

    private final CctvApiMapper cctvApiMapper;

//    @Bean
    public CctvApiService cctvApiService() {
        return new CctvApiService(cctvApiMapper);
    }
}
