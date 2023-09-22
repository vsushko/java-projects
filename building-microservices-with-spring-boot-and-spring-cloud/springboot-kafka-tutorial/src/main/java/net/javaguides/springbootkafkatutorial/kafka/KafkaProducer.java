package net.javaguides.springbootkafkatutorial.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author vsushko
 */
@Slf4j
@AllArgsConstructor
@Service
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        log.info("Message was sent: {}", message);
        kafkaTemplate.send("javaguides", message);
    }
}
