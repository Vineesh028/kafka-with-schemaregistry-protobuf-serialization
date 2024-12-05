package com.kafka.schema.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
public class KafkaConfig {

	@Value("${topic.name}")
	private String topicName;

	@Bean
	public NewTopic createTopic() {
		return new NewTopic(topicName, 3, (short) 1);
	}



}
