package ru.ivamly.system.agentsmith.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PostmanPechkinClient {

    private final RestClient restClient;

    public PostmanPechkinClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public String retrieveMessage() {
        return restClient.get()
                .uri("http://localhost:8081/messages")
                .retrieve()
                .body(String.class);
    }
}
