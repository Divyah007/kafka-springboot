package com.divya.kafka.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.divya.kafka.document.LogisticsCustomerDetails;

@RepositoryRestResource(collectionResourceRel = "logistics_customer_details", path = "logistics-customer-details")
public interface LogisticsCustomerRepository extends MongoRepository<LogisticsCustomerDetails, String> {

}
