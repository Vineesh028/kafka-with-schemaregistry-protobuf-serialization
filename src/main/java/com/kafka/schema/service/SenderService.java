package com.kafka.schema.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.kafka.schema.model.UserProto.User;

@Service
public class SenderService {

	@Value("${topic.name}")
	private String topicName;

	@Autowired
	KafkaTemplate<String, User> KafkaTemplate;

	public String send(User user) throws InterruptedException, ExecutionException {

		CompletableFuture<SendResult<String, User>> future = KafkaTemplate.send(topicName, UUID.randomUUID().toString(),
				user);

		return future.handle((result, ex) -> {
			if (ex == null) {

				return "Sent message=" + user + " to topic= " + result.getRecordMetadata().topic();
			} else {
				return "Failed to send message=" + user + " due to : " + ex.getMessage();
			}
		}).get();

	}

}
