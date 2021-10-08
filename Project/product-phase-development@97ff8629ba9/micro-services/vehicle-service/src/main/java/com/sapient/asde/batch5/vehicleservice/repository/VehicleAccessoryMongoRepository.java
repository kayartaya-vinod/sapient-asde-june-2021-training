package com.sapient.asde.batch5.vehicleservice.repository;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleAccessory;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleAccessoryMongoRepository extends MongoRepository<VehicleAccessory, String> {

}
