package com.example.kafka.controller;

import com.example.kafka.paylaod.User;
import com.example.kafka.producer.KafkaJsonProducer;
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
    private final KafkaJsonProducer kafkaJsonProducer;

    public MessageController(KafkaProducer kafkaProducer, KafkaJsonProducer kafkaJsonProducer) {
        this.kafkaProducer = kafkaProducer;
        this.kafkaJsonProducer = kafkaJsonProducer;
    }

    @PostMapping("/sendmessage")
    public ResponseEntity<Object> sendMessage(@RequestBody Map<String, String> body) {
        kafkaProducer.sendMessage(body.get("message"));
        return ResponseEntity.ok(Map.of("message", "Message sent successfully..!"));
    }

    @PostMapping("/sendjsonmessage")
    public ResponseEntity<Object> sendJsonMessage(@RequestBody User user) {
        kafkaJsonProducer.sendMessage(user);
        return ResponseEntity.ok(Map.of("message", "Message sent successfully..!"));
    }
}
