package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1151;

import java.util.Date;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleComparison;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleComparisonRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Neha Neha1@publicissapient.com
 */

@Service
public class StoreVehicleComparisonServiceImpl implements StoreVehicleComparisonService {

    @Autowired
    VehicleComparisonRepository repo;

    @Override
    public VehicleComparison storeVehicle(VehicleComparison vehicleComparison,String userId) throws ServiceException {
        if (userId ==null || userId.trim().isEmpty()){
            throw new ServiceException("Vehicle  id cannot be empty.", HttpStatus.BAD_REQUEST);
        }
        
        List<VehicleComparison> ids = repo.getByUserId(userId);
        for (VehicleComparison vc : ids) {
            if (vc.getVehicleIds().equals(vehicleComparison.getVehicleIds())) {
                throw new ServiceException("comparison matrix exists already", HttpStatus.OK);
            }
        }

        Date createdDate = new Date();
        vehicleComparison.setCreatedDate(createdDate);
        vehicleComparison.setUserId(userId);
        return repo.save(vehicleComparison);
    }

}


