package com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1349;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.data.domain.Page;

public interface GetVehicleListByDealerIdService {
    public Page<Vehicle> getVehicleListByDealerId(String dealerId, Integer page) throws ServiceException;
}
