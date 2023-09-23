package com.vsushko.rabbitmqdemo.consumer;

import com.vsushko.rabbitmqdemo.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author vsushko
 */
@Slf4j
@Service
public class RabbitMQJsonConsumer {

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJsonMessage(User user) {
        log.info(String.format("Received JSON message -> %s", user.toString()));
    }
}
