package ru.ivamly.system.agentsmith.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.OffsetDateTime;
import java.time.ZoneId;

@Slf4j
@Component
public class PostmanPechkinClient {

    private final RestClient restClient;

    public PostmanPechkinClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public String retrieveMessage() {
        log.trace("retrieving message at {}", OffsetDateTime.now(ZoneId.of("UTC")));
        return restClient.get()
                .uri("http://localhost:8081/messages")
                .retrieve()
                .body(String.class);
    }
}
