package com.divya.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class KafkaTopicConfig {
	 @Value("${spring.kafka.topic.name}")
	  private String topicName;

	 //used to return topic object
	  @Bean
	  public NewTopic createNewTopic() {
	    return TopicBuilder.name(topicName).build();
	  }
	  
	  @Bean
	  public  ObjectMapper  getObjectMapper() {
	    return new ObjectMapper();
	  }
}
