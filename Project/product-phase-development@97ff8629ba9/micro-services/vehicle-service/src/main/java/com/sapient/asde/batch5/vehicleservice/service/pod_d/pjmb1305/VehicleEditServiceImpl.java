package com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1305;
import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Suraj kumar suraj.kumar3@publicissapient.com
 */
@Slf4j
@Service("pjmb1305_VehicleEditServiceImpl")
public class VehicleEditServiceImpl implements VehicleEditService {

    @Autowired
    VehicleRepository vehicleRepo;


   
    @CachePut(value = "Vehicle",key="#vehicle.getId()")
    @Caching(evict = {
        @CacheEvict(value = "VehicleSearch",allEntries = true),
        @CacheEvict(value = "VehicleList",allEntries = true)})
    @Override
    public Vehicle saveVehicle(Vehicle vehicle, String userId)
            throws ServiceException {
        if (vehicle == null) {
            throw new ServiceException("Vehicle data not provided", HttpStatus.BAD_REQUEST);
        }
        String  vehicleId=vehicle.getId();
        
        if (vehicleId == null || vehicleId.trim().isEmpty()) {
            log.debug("save vehicle  - Vehicle id not supplied");
            throw new ServiceException("Vehicle id cannot be empty.", HttpStatus.BAD_REQUEST);
        }
        if (userId == null || userId.trim().isEmpty()) {
            log.debug("getVehicleById dealerId not supplied. ");
            throw new ServiceException("Dealer id cannot be empty.", HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<Vehicle> optionalVehicle = vehicleRepo.findById(vehicleId);

            if (!optionalVehicle.isPresent()) {
                log.debug("getVehicleById - Vehicle id not found.");
                throw new ServiceException("Vehicle not found.", HttpStatus.NOT_FOUND);
            }
            Vehicle v = optionalVehicle.get();
            if (!v.getDealerId().equals(userId)) {
                log.debug(" Unauthorized to edit Vehicle {}.", userId, vehicleId);
                throw new ServiceException("Unauthorized Access.", HttpStatus.UNAUTHORIZED);
            }
            vehicle.setId(v.getId());
            return vehicleRepo.save(vehicle);

        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getStatus());

        }

    }


    @Override
    public Vehicle getVehicle(String vehicleId, String userId) throws ServiceException {

        if (vehicleId == null || vehicleId.trim().isEmpty()) {
            log.debug("getVehicleById  - Vehicle id not supplied");
            throw new ServiceException("Vehicle id cannot be empty.", HttpStatus.BAD_REQUEST);
        }
        if (userId == null || userId.trim().isEmpty()) {
            log.debug("getVehicleById dealerId not supplied. ");
            throw new ServiceException("Dealer id cannot be empty.", HttpStatus.BAD_REQUEST);
        }
        log.info("vehicle id = {}",vehicleId);
        try {
            Optional<Vehicle> optionalVehicle = vehicleRepo.findById(vehicleId);
            if (!optionalVehicle.isPresent()) {
                log.debug("getVehicleById - Vehicle id not found.");
                throw new ServiceException("Vehicle not found.", HttpStatus.NOT_FOUND);
            }
            Vehicle vehicle = optionalVehicle.get();
            if (!vehicle.getDealerId().equals(userId)) {
                log.debug(" Unauthorized to view Vehicle {}.", userId, vehicleId);
                throw new ServiceException("Unauthorized Access.", HttpStatus.UNAUTHORIZED);
            }
            log.info(" vehicle = {}", vehicle);
            return vehicle;

        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getStatus());
        }

    }

}
