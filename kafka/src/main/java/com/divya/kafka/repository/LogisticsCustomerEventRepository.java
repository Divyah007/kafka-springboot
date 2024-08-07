package com.divya.kafka.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.divya.kafka.document.LogisticsCustomerDetails;
import com.divya.kafka.document.LogisticsCustomerDetailsEvent;

@RepositoryRestResource(collectionResourceRel = "logistics_customer_details_event", path = "logistics-customer-details-event")
public interface LogisticsCustomerEventRepository extends MongoRepository<LogisticsCustomerDetailsEvent, String> {

}
