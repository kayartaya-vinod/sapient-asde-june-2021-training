package com.sapient.asde.batch5.vehicleservice.repository;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleAccessory;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRepository extends MongoRepository<VehicleAccessory, String>,CustomVehicleRepository {

    public Page<VehicleAccessory> findByDealerId(String dealerId, Pageable page) throws ServiceException;
}
