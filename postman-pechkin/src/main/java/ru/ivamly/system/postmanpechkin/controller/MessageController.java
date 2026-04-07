package ru.ivamly.system.postmanpechkin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    public ResponseEntity<String> getMessage() {
        counter.incrementAndGet();
        if (counter.get() % 10 == 0) {
            return ResponseEntity.internalServerError().build();
        } else if (counter.get() % 20 == 0) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(String.valueOf(ThreadLocalRandom.current().nextInt()));
    }
}
