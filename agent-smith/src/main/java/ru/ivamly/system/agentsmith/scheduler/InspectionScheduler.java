package ru.ivamly.system.agentsmith.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.ivamly.system.agentsmith.client.PostmanPechkinClient;
import ru.ivamly.system.agentsmith.client.UncleFedorClient;

@Slf4j
@Component
public class InspectionScheduler {

    private final PostmanPechkinClient postmanPechkinClient;
    private final UncleFedorClient uncleFedorClient;

    public InspectionScheduler(PostmanPechkinClient postmanPechkinClient, UncleFedorClient uncleFedorClient) {
        this.postmanPechkinClient = postmanPechkinClient;
        this.uncleFedorClient = uncleFedorClient;
    }

    @Scheduled(fixedRate = 500L)
    public void observe() {
        String message = postmanPechkinClient.retrieveMessage();
        if (message == null) {
            log.warn("retrieved message is null");
            return;
        }
        log.info("retrieved message is {}", message);
        int value = Integer.parseInt(message);
        if (value % 2 == 0) {
            uncleFedorClient.incrementSocialCredit();
        } else {
            uncleFedorClient.decrementSocialCredit();
        }
    }
}
