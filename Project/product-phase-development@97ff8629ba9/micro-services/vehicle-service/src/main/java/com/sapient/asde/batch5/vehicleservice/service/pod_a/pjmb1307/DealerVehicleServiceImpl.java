



/**
 * @author Pritam Patel pritam.patel@publicissapient.com
 */


package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1307;




import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class DealerVehicleServiceImpl implements DealerVehicleService {

    @Autowired
    VehicleRepository repo;

    @Caching(evict = {
        @CacheEvict(value = "VehicleSearch",allEntries = true),
        @CacheEvict(value = "VehicleList",allEntries = true)})
    @Override
    public Vehicle addNewVehicle(Vehicle vehicle, String userId) throws ServiceException {
        
        if(userId == null || userId.trim().isEmpty()){
            throw new ServiceException("Vehicle  id cannot be empty.", HttpStatus.BAD_REQUEST);  
        }
        
        vehicle.setDealerId(userId);
        return repo.save(vehicle);
    }

}
