package org.example.oligarchrating;

import org.example.oligarchrating.infrastructure.rest.ExternalApiService;
import org.example.oligarchrating.infrastructure.rest.ExternalApiServiceInterface;
import org.example.oligarchrating.infrastructure.rest.MockExternalApiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Profile("dev")
    public ExternalApiServiceInterface mockExternalApiService() {
        return new MockExternalApiService();
    }

    @Bean
    @Profile("prod")
    public ExternalApiServiceInterface externalApiService() {
        return new ExternalApiService(restTemplate());
    }
}
