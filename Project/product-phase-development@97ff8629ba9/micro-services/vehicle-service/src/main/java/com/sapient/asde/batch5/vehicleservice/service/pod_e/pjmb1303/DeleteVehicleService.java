package com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1303;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

public interface DeleteVehicleService {
    public String deleteVehicleById(String vehicleId,String userId) throws ServiceException;
}
