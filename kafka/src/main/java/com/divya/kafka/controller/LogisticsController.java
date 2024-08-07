package com.divya.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.divya.kafka.document.LogisticsCustomerDetails;
import com.divya.kafka.service.KafkaProducer;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/api")
public class LogisticsController {
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
	@PostMapping("/customer")
	public String insertCustomerDetails(@RequestBody String message) {
		String response="Failure";
		kafkaProducer.sendMessage(message);
		log.info("====controller====");
		response="True";
		return response;
	}

}
