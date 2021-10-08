package com.sapient.asde.batch5.vehicleservice.controller.pod_d.pjmb1136;

import java.util.HashMap;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1136.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Aravind M Krishnan aravind.m.krishnan@publicissapient.com
 */
@Slf4j
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

  @Autowired
  private VehicleService service;

  private static final String SUCCESS = "success";
  private static final String DATA = "data";

  @GetMapping("/sponsored")
  public ResponseEntity<Object> getSponsoredVehicle() throws ServiceException {

    log.info("GET /api/vehicles/sponsored");

    Iterable<Vehicle> v1 = service.getSponsoredVehicleByIdList();

    Map<String, Object> map = new HashMap<>();

    map.put(SUCCESS, true);
    map.put(DATA, v1);

    return ResponseEntity.ok(map);
  }

}
