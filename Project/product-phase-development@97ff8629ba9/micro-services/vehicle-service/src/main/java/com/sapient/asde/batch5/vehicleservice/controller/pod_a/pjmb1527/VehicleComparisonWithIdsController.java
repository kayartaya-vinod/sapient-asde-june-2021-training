package com.sapient.asde.batch5.vehicleservice.controller.pod_a.pjmb1527;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1527.VehicleComparisonWithIdsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
@Slf4j
@RestController
@RequestMapping("/api/vehicles/compare")
public class VehicleComparisonWithIdsController {
  
  @Autowired
  private VehicleComparisonWithIdsService service;

  private static final String SUCCESS = "success";
  private static final String DATA = "data";

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Map<String, Object> compareWithIds(@RequestParam List<String> ids) throws ServiceException, JsonMappingException, JsonProcessingException {
    log.trace("GET /api/vehicles/compare/?ids=" + ids);

    if(ids.size() > 4) {
      log.trace("Error will be thrown: Ids should be less than 4");
      throw new ServiceException("Ids can not be more than 4", HttpStatus.BAD_REQUEST);
    }

    Map<String, Object> vehicles = service.getVehiclesById(ids);

    Map<String, Object> map = new HashMap<>();

    map.put(SUCCESS, true);
    map.put(DATA, vehicles);
    return map;
  }
}
