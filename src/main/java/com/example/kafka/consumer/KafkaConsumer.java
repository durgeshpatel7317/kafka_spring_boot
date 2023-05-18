package com.example.kafka.consumer;

import com.example.kafka.paylaod.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {
    private final Gson gson;

    @Autowired
    public KafkaConsumer(Gson gson) {
        this.gson = gson;
    }

    @KafkaListener(topics = "message", groupId = "my-group")
    public void consumeMessage(String message) {
        log.info(String.format("Message received %s", message));
    }

    @KafkaListener(topics = "json-message", groupId = "my-group")
    public void consumeJsonMessage(String data) {
        User user = gson.fromJson(data, new TypeToken<>() {
        });

        log.info("Received user details {}", user);
    }
}
