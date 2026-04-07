package ru.ivamly.system.agentsmith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AgentSmithApplication {

    static void main(String[] args) {
        SpringApplication.run(AgentSmithApplication.class, args);
    }
}
