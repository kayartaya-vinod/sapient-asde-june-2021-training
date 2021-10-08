package com.sapient.asde.batch5.vehicledataservice.repository;


import com.sapient.asde.batch5.vehicledataservice.entity.Vehicle;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {
    public long countByDealerId(String dealerId);
}
