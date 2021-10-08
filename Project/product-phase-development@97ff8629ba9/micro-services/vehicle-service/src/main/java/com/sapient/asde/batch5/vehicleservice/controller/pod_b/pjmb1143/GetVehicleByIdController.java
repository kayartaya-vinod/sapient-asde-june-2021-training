package com.sapient.asde.batch5.vehicleservice.controller.pod_b.pjmb1143;

import java.util.HashMap;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1143.GetVehicleByIdService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/vehicles")
public class GetVehicleByIdController {
  private static final String SUCCESS = "success";
  private static final String DATA = "data";
  @Autowired
  GetVehicleByIdService service;

  @GetMapping("/{id}")
  public ResponseEntity<Object> getVehicleById(@PathVariable String id) throws ServiceException {
    log.info("GET /api/vehicles/{}", id);
    Map<String, Object> map = new HashMap<>();
    Vehicle vehicle = service.getVehicleById(id);
    map.put(DATA, vehicle);
    map.put(SUCCESS, true);
    return ResponseEntity.ok(map);
  }
}
