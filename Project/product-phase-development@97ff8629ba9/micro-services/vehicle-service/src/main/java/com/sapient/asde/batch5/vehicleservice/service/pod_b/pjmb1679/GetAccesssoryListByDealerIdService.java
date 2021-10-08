package com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1679;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleAccessory;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.data.domain.Page;
/**
 * @author Manvendra Singh
 */
public interface GetAccesssoryListByDealerIdService {
    public Page<VehicleAccessory> getAccessoryListByDealerId(String dealerId, Integer page) throws ServiceException;
}
