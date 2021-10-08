package com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1143;

import java.util.Optional;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
/* 
 * @author Manvendra Singh manvendra.singh@publicissapient.com

 */

@Service("pjmb1143_GetVehicleBYIdServiceImpl")
public class GetVehicleByIdServiceImpl implements GetVehicleByIdService {
    @Autowired
    VehicleRepository dao;

    @Cacheable(value = "Vehicle",key  = "#id")
    @Override
    public Vehicle getVehicleById(String id) throws ServiceException {

        if (id == null || id.trim().equals("")) {
            throw new ServiceException("id cannot be null or empty string");
        }
        Optional<Vehicle> vehicle=dao.findById(id);
        if (vehicle.isPresent()) {
            return vehicle.get();
        } else {
            throw new ServiceException("No matching vehicle found ",HttpStatus.NOT_FOUND);
        }
    }
}

