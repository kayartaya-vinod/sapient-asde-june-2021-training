package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1612;

import java.util.List;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.bson.json.JsonObject;

/**
 * @author Neha neha1@publicissapient.com
 */
public interface GetVehicleAccessoryService {
    public List<JsonObject> getVehicleAccessory(String id) throws ServiceException;

}
