package com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1143;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;


public interface GetVehicleByIdService {
    public Vehicle getVehicleById(String id) throws ServiceException ;
}
