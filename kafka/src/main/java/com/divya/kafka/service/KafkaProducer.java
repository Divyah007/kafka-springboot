 package com.divya.kafka.service;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.kafka.core.KafkaTemplate;
 import org.springframework.stereotype.Component;

import com.divya.kafka.document.LogisticsCustomerDetails;

import lombok.extern.slf4j.Slf4j;

 @Slf4j
 @Component
 public class KafkaProducer {
 @Value("${spring.kafka.topic.name}")
 private String topicName;
 
 @Value("${spring.kafka.topic.event.name}")
 private String eventTopic;
 
 @Value("${spring.kafka.topic.invalid.request.object.name}")
 private String invalidReqObjTopic;

 @Autowired
 private KafkaTemplate<String, String> kafkaTemplate;

 public void sendMessage(String message) {
 kafkaTemplate.send(topicName, message);
 }
 
 }
