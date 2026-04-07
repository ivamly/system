package ru.ivamly.system.agentsmith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatusCode;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@EnableScheduling
@SpringBootApplication
public class AgentSmithApplication {

    private final RestClient restClient = RestClient.create();

    static void main(String[] args) {
        SpringApplication.run(AgentSmithApplication.class, args);
    }

    @Scheduled(fixedRate = 500, timeUnit = TimeUnit.MILLISECONDS)
    void processMessages() {
        String message = restClient.get()
                .uri(URI.create("http://localhost:8081/messages"))
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        (_, _) -> System.out.println("error")
                ).body(String.class);
        if (message == null) {
            System.out.println("null");
            return;
        }
        System.out.println("message " + message);
        int value = Integer.parseInt(message);
        RestClient.RequestHeadersSpec.ExchangeFunction<Void> exchangeFunction = ((_, response) -> {
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("success");
            } else {
                System.out.println("error");
            }
            return null;
        });
        if (value % 3 == 0) {
            restClient.patch()
                    .uri(URI.create("http://localhost:8082/social-credit/inc"))
                    .exchange(exchangeFunction);
        } else {
            restClient.patch()
                    .uri(URI.create("http://localhost:8082/social-credit/dec"))
                    .exchange(exchangeFunction);
        }
    }
}
