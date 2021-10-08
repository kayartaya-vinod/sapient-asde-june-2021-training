package com.sapient.asde.batch5.vehicleservice.controller.pod_a.pjmb1529;

import java.util.HashMap;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1529.AdvancedSearchVehicleAttributeSerice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Abhishek Singh abhishek.singh8@publicissapient.com
 */
@Slf4j
@RestController
@RequestMapping("/api/vehicles/search-attributes")
public class AdvancedSearchVechicleAttributeController {
  @Autowired
  @Qualifier("pjmb1529_advancedsearchvehicleattributeserviceimpl")
  private AdvancedSearchVehicleAttributeSerice service;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Map<String, Object> getList() throws ServiceException {

    log.trace("GET /api/vehicles/search-attributes");

    Map<String, Object> response = new HashMap<>();
    response.put("data", service.getAdvancedSearchAttributes());
    response.put("success", true);

    return response;
  }
}
