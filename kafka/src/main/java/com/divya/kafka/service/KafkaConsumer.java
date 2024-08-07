package com.divya.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Service;

import com.divya.kafka.document.LogisticsCustomerDetails;
import com.divya.kafka.document.LogisticsCustomerDetailsEvent;
import com.divya.kafka.repository.LogisticsCustomerEventRepository;
import com.divya.kafka.repository.LogisticsCustomerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaConsumer {
	
	@Autowired
	private LogisticsCustomerEventRepository logisticsCustomerEventRepository;
	
	@Autowired
	private LogisticsCustomerRepository logisticsCustomerRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
//	case 1:
	//without try and catch
//		if any exception occured,it will throw the exception on the sts console. then we cannot proceed further and our input message is lost
	
//	  @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
//	  public void eventConsume(String message) throws Exception {
//	    Integer i=Integer.valueOf(message);
//		log.info("==i==={}",i);
//		log.info("================COMPLETED-===============");
//	  }

//================
//	case 2: 
	//with try and catch
//	if any exception occured,it is handled by catch block and  then we cannot proceed further and our input message is lost, here 
//	we can store the input message in the db inside catch block and Then we can have an endpoint to GET these messages from db for later
//	manual fix.But in realtime it isn't right way.
	
//	  @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
//	  public void eventConsume(String message) throws Exception {
//		  try {
//			  Integer i=Integer.valueOf(message);
//				log.info("==i==={}",i);
//				log.info("================COMPLETED-===============");
//			  
//		  } catch(Exception e) {
//			  log.error("EEEEEERRRRROOORRRRR");
//		  }
//	    
//	  }
	//====================================
	
	//case 3:
	//	without try and catch , if exception occurs, kafka will again retry n-1 times to consume the message using @RetryableTopic
	//annotation with no. of attempts and delay.
	
	//even after retrying for n-1 times , at n-1th attempt if it fails then the message will be sent to dlt
  @RetryableTopic(attempts = "3") // if we give n attempts then only n-1 times it will retry, at n-1th attempt it fails then the input message will be sent to dlt
	  @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	  public void eventConsume(String message) throws Exception {
	  log.info("consumer=====");
			  Integer i=Integer.valueOf(message);
				log.info("==i==={}",i);
				log.info("================COMPLETED-===============");
			  
		 
	    
	  }
 // ============================================================================
  //DLT
//  A Dead Letter Topic (DLT) in Kafka is a special topic where messages that cannot be processed successfully after a 
//  certain number of retries are sent.
//  This allows you to track and handle problematic messages separately from your main processing flow.
  
  
 // @DltHandler
//  each consumer should be in separate file, for each consumer we can have dlthandler. the dlt handler sholuld 
//  be there in the same file where the kafka consumer is present
  
  //inside dlthandler
  // save the records to a database. 
  
 //we can do manual fix for the failed messages
    //1. endpoint to GET these messages.
  //2.another endpoint where you can post/patch/put the fixed record.
  
  @DltHandler // it will listen the failure message
  public void listenDLT(String message) {
	  log.error("DLT========={}",message);
	  
  }
	


}
