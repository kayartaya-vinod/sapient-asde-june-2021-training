package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1151;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleComparison;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;


/**
 * @author Neha Neha1@publicissapient.com
 */
public interface StoreVehicleComparisonService {
    public VehicleComparison storeVehicle(VehicleComparison vehicleComparison,String userId) throws ServiceException;
}
