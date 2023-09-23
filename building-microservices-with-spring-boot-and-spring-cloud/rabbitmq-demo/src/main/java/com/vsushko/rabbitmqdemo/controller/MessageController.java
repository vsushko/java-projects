package com.vsushko.rabbitmqdemo.controller;

import com.vsushko.rabbitmqdemo.publisher.RabbitMQProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vsushko
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private RabbitMQProducer producer;

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        producer.sendMessage(message);
        return ResponseEntity.ok("Message was to RabbitMQ...");
    }
}
