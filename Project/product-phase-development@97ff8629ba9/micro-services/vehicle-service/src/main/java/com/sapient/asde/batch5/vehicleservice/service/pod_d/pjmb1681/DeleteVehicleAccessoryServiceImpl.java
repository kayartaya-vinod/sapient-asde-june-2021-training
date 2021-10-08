package com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1681;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleAccessory;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleAccessoryMongoRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Aravind M Krishnan aravind.m.krishnan@publicissapeint.com
 */

@Slf4j
@Service
public class DeleteVehicleAccessoryServiceImpl implements DeleteVehicleAccessoryService {

    @Autowired
    VehicleAccessoryMongoRepository vehicleAccRepo;

    @CacheEvict(value = "AccessorySearch")
    @Override
    public String deleteVehicleAccessoryById(String vehicleAccId, String userId) throws ServiceException {

        if (vehicleAccId == null || vehicleAccId.trim().isEmpty()) {
            log.debug("deleteVehicleAccById - Vehicle Accessory id not supplied.");
            throw new ServiceException("Vehicle id cannot be empty.", HttpStatus.BAD_REQUEST);
        }
        if (userId == null || userId.trim().isEmpty()) {
            log.debug("deleteVehicleAccById dealerId not supplied.");
            throw new ServiceException("Dealer id cannot be empty.", HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<VehicleAccessory> optionalVehicleAcc = vehicleAccRepo.findById(vehicleAccId);
            if (!optionalVehicleAcc.isPresent()) {
                log.debug("deleteVehicleAccById - Vehicle Accessory id not found.");
                throw new ServiceException("Vehicle Accessory not found.", HttpStatus.NOT_FOUND);
            }

            VehicleAccessory vehicleAcc = optionalVehicleAcc.get();
            if (!vehicleAcc.getDealerId().equals(userId)) {
                log.debug("deleteVehicleAccById - Dealer {} Unauthorized to delete Vehicle Acc {}.", userId,
                        vehicleAccId);
                throw new ServiceException("Unauthorized Access.", HttpStatus.UNAUTHORIZED);
            }

            vehicleAccRepo.deleteById(vehicleAccId);
            log.info("deleteVehicleAccById - Delete Vehicle {} for user {}.", vehicleAccId, userId);
            return vehicleAccId;

        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getStatus());

        }

    }

}
