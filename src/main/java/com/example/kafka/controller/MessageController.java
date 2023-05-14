package com.example.kafka.controller;

import com.example.kafka.producer.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/api/v1/kafka")
@RestController
public class MessageController {
    private final KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/sendmessage")
    public ResponseEntity<Object> sendMessage(@RequestBody Map<String, String> body) {
        kafkaProducer.sendMessage(body.get("message"));
        return ResponseEntity.ok(Map.of("message", "Message sent successfully..!"));
    }
}
