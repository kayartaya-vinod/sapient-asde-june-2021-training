package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1529;

import java.util.List;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.bson.json.JsonObject;

public interface AdvancedSearchVehicleAttributeSerice {
  public List<JsonObject> getAdvancedSearchAttributes() throws ServiceException;
}
