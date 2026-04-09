package ru.ivamly.system.agentsmith.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.OffsetDateTime;
import java.time.ZoneId;

@Slf4j
@Component
public class UncleFedorClient {

    private static final String uncleFedorBaseUrl = "http://localhost:8082/social-credit";

    private final RestClient restClient;

    public UncleFedorClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public void incrementSocialCredit() {
        log.trace("increment social credit at {}", OffsetDateTime.now(ZoneId.of("UTC")));
        restClient.patch()
                .uri(uncleFedorBaseUrl + "/inc")
                .retrieve()
                .toBodilessEntity();
    }

    public void decrementSocialCredit() {
        log.trace("decrement social credit at {}", OffsetDateTime.now(ZoneId.of("UTC")));
        restClient.patch()
                .uri(uncleFedorBaseUrl + "/dec")
                .retrieve()
                .toBodilessEntity();
    }
}
