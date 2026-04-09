package ru.ivamly.system.agentsmith.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

@Slf4j
@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .defaultStatusHandler(HttpStatusCode::isError,
                        (request, response) ->
                                log.error("Error: {}, URL: {}", response.getStatusCode(), request.getURI()))
                .build();
    }
}
