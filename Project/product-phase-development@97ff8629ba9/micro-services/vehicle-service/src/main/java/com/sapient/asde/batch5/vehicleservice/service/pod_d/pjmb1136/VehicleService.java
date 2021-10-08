package com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1136;


import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

/**
 * @author Aravind M Krishnan aravind.m.krishnan@publicissapient.com
 */

public interface VehicleService {

    public Iterable<Vehicle> getSponsoredVehicleByIdList() throws ServiceException;
}
