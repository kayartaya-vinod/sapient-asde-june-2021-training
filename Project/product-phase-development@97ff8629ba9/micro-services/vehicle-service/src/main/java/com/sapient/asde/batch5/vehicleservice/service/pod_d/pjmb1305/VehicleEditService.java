package com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1305;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

/**
 * @author Suraj kumar suraj.kumar3@publicissapient.com
 */
public interface VehicleEditService {
    public Vehicle saveVehicle(Vehicle vehicle,String userId) throws ServiceException;
    public Vehicle getVehicle(String vehicleId,String userId) throws ServiceException;
    
}
