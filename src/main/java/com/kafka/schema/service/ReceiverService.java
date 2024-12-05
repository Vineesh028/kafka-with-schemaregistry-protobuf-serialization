package com.kafka.schema.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.schema.model.UserProto.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReceiverService {


    @KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void read(ConsumerRecord<String, User> consumerRecord) {
        String key = consumerRecord.key();
        User user = consumerRecord.value();
        log.info("Message received with key : " + key + " value : " + user.toString());

    }
}