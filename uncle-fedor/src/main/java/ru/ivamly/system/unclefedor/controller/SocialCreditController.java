package ru.ivamly.system.unclefedor.controller;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/social-credit")
public class SocialCreditController {

    private final AtomicLong socialCredit = new AtomicLong(0L);

    @PatchMapping("/inc")
    public void increment() {
        System.out.println(socialCredit.incrementAndGet());
    }

    @PatchMapping("/dec")
    public void decrement() {
        System.out.println(socialCredit.decrementAndGet());
    }
}
