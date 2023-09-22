package com.vsushko.emailservice.consumer;

import com.vsushko.edmcore.dto.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author vsushko
 */
@Slf4j
@Service
public class OrderConsumer {

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(OrderEvent event) {
        log.info(String.format("Order event received in email service => %s", event.toString()));
    }
}
