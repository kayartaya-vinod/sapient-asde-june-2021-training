package com.sapient.asde.batch5.vehicleservice.repository;

import com.sapient.asde.batch5.vehicleservice.entity.SponsoredVehicles;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsoredVehiclesRepository extends MongoRepository<SponsoredVehicles, String> {

}
