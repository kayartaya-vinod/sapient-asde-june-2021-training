package com.sapient.asde.batch5.vehicleservice.service.pod_c.pjmb1138;


import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.data.domain.Page;

/**
 * @author Mutharsan E mutharasan.e@publicissapient.com
 */
public interface VehicleListService {
    public Page<Vehicle> getVehiclewithPagination(Integer page) throws ServiceException;
}
