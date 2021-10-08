package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1527;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
public interface VehicleComparisonWithIdsService {
  public Map<String, Object> getVehiclesById(List<String> ids) throws ServiceException, JsonMappingException, JsonProcessingException  ;
}
