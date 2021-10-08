package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1612;

import java.util.List;

import com.sapient.asde.batch5.vehicleservice.repository.VehicleAccessoryRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Neha neha1@publicissapient.com
 */
@Slf4j
@Service
public class GetVehicleAccessoryServiceImpl implements GetVehicleAccessoryService {

    @Autowired
    VehicleAccessoryRepository repo;

    @Override
    public List<JsonObject> getVehicleAccessory(String id) throws ServiceException {
        log.trace("getVehicleAccessoty()");
        if (id == null || id.trim().equals("")) {
            throw new ServiceException("id cannot be null or empty string", HttpStatus.BAD_REQUEST);
        }

        return repo.vehicleAccessory(id);

    }

}
