package com.example.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(topics = "message", groupId = "my-group")
    public void consumeMessage(String message) {
        log.info(String.format("Message received %s", message));
    }
}
