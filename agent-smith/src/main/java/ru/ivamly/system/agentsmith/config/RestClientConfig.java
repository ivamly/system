package ru.ivamly.system.agentsmith.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    private static final Logger log = LoggerFactory.getLogger(RestClientConfig.class);

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .defaultStatusHandler(HttpStatusCode::isError,
                        (request, response) ->
                                log.error("Error: {}, URL: {}", response.getStatusCode(), request.getURI()))
                .build();
    }
}
