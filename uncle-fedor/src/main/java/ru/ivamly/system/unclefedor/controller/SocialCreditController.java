package ru.ivamly.system.unclefedor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

@RestController
@RequestMapping("/social-credit")
public class SocialCreditController {

    private static final Logger log = LoggerFactory.getLogger(SocialCreditController.class);

    private final AtomicLong counter = new AtomicLong();
    private final AtomicLong socialCredit = new AtomicLong(0L);

    @PatchMapping("/inc")
    public ResponseEntity<Void> increment() {
        return updateSocialRating(AtomicLong::incrementAndGet);
    }

    @PatchMapping("/dec")
    public ResponseEntity<Void> decrement() {
        return updateSocialRating(AtomicLong::decrementAndGet);
    }

    private ResponseEntity<Void> updateSocialRating(Consumer<AtomicLong> updater) {
        log.trace("initial counter is {}", counter.get());
        counter.incrementAndGet();
        log.debug("received request № {}", counter.get());
        if (counter.get() % 6 == 0) {
            log.error("failed to update social credit for request № {}", counter.get());
            return ResponseEntity.internalServerError().build();
        }
        updater.accept(socialCredit);
        log.info("social credit is {} after request № {}", socialCredit.get(), counter.get());
        if (socialCredit.get() > 10) {
            log.warn("so bad - it's logical");
        }
        return ResponseEntity.ok().build();
    }
}
