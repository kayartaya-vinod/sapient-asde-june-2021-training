package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1154;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleComparison;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
public interface VehicleComparisonService {
  public VehicleComparison getComparisonMatrix(String id, String userId) throws ServiceException;
}
