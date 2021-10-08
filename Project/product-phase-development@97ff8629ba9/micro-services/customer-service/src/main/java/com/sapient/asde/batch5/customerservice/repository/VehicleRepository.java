package com.sapient.asde.batch5.customerservice.repository;

import com.sapient.asde.batch5.customerservice.entity.Vehicle;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleRepository extends MongoRepository<Vehicle, String>{


    
}
