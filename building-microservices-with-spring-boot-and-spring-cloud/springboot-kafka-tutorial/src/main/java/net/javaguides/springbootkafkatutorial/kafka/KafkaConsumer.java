package net.javaguides.springbootkafkatutorial.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author vsushko
 */
@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(topics = "javaguides")
    public void consume(String message) {
        log.info("Message received: {}", message);
    }
}
