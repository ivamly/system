package ru.ivamly.system.postmanpechkin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    public ResponseEntity<String> getMessage() {
        log.trace("initial counter is {}", counter.get());
        counter.incrementAndGet();
        log.debug("received request № {}", counter.get());
        if (counter.get() % 7 == 0) {
            log.error("failed to generate message for request № {}", counter.get());
            return ResponseEntity.internalServerError().build();
        } else if (counter.get() % 9 == 0) {
            log.warn("message is null for request № {}", counter.get());
            return ResponseEntity.ok(null);
        }
        String message = String.valueOf(ThreadLocalRandom.current().nextInt());
        log.info("generate message {} for request № {}", message, counter.get());
        return ResponseEntity.ok(message);
    }
}
