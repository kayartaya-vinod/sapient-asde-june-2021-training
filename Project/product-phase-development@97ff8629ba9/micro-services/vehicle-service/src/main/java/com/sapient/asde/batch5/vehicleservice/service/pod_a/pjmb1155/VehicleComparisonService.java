package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1155;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

public interface VehicleComparisonService {

  public void deleteComparisonMatrixById(String id, String userId) throws ServiceException;
}
