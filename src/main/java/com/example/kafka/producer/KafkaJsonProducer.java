package com.example.kafka.producer;

import com.example.kafka.paylaod.User;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaJsonProducer {
    private final Gson gson;
    private final KafkaTemplate<String, User> kafkaTemplate;

    @Autowired
    public KafkaJsonProducer(Gson gson, KafkaTemplate<String, User> kafkaTemplate) {
        this.gson = gson;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User data) {
        String string = gson.toJson(data);
        Message<String> message = MessageBuilder
            .withPayload(string)
            .setHeader(KafkaHeaders.TOPIC, "json-message")
            .build();

        log.info("Sent user details {}", string);

        kafkaTemplate.send(message);
    }
}
