package com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1303;

import java.util.Optional;
import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@CacheConfig(cacheNames = "Vehicles")
@Slf4j
@Service
public class DeleteVehicleServiceImpl implements DeleteVehicleService{
    @Autowired
    VehicleRepository vehicleRepo;


    @Caching(evict = {
    @CacheEvict(value = "Vehicle",key="#vehicleId"),
    @CacheEvict(value = "VehicleSearch",allEntries = true),
    @CacheEvict(value = "VehicleList",allEntries = true)}
    )
    @Override
    public String deleteVehicleById(String vehicleId,String userId) throws ServiceException {
    
        if (vehicleId == null || vehicleId.trim().isEmpty()) {
            log.debug("deleteVehicleById - Vehicle id not supplied.");
            throw new ServiceException("Vehicle id cannot be empty.", HttpStatus.BAD_REQUEST);
          }
          if (userId == null || userId.trim().isEmpty()) {
            log.debug("deleteVehicleById dealerId not supplied.");
            throw new ServiceException("Dealer id cannot be empty.", HttpStatus.BAD_REQUEST);
          }
      
          try {
            Optional<Vehicle> optionalVehicle = vehicleRepo.findById(vehicleId);
      
            if (!optionalVehicle.isPresent()) {
              log.debug("deleteVehicleById - Vehicle id not found.");
              throw new ServiceException("Vehicle not found.", HttpStatus.NOT_FOUND);
            }
      
            Vehicle vehicle = optionalVehicle.get();
      
            if (!vehicle.getDealerId().equals(userId)) {
              log.debug("deleteVehicleById - user {} Unauthorized to delete Vehicle {}.", userId,
                  vehicleId);
              throw new ServiceException("Unauthorized Access.", HttpStatus.UNAUTHORIZED);
            }
      
            vehicleRepo.deleteById(vehicleId);
            log.info("deleteVehicleById - Delete Vehicle {} for user {}.", vehicleId, userId);
            return vehicleId;
      
          } catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getStatus());
      
          }
    }
    
}
