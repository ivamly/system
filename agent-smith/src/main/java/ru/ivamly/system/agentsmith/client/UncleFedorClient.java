package ru.ivamly.system.agentsmith.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class UncleFedorClient {

    private static final String uncleFedorBaseUrl = "http://localhost:8082/social-credit";

    private final RestClient restClient;

    public UncleFedorClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public void incrementSocialCredit() {
        restClient.patch()
                .uri(uncleFedorBaseUrl + "/inc")
                .retrieve()
                .toBodilessEntity();
    }

    public void decrementSocialCredit() {
        restClient.patch()
                .uri(uncleFedorBaseUrl + "/dec")
                .retrieve()
                .toBodilessEntity();
    }
}
