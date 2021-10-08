package com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1681;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

public interface DeleteVehicleAccessoryService {
    
    public String deleteVehicleAccessoryById(String vehicleId, String userId) throws ServiceException;

}
