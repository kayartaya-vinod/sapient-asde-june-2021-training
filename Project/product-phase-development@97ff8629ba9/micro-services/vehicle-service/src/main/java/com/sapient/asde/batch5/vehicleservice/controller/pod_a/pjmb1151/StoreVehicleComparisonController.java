package com.sapient.asde.batch5.vehicleservice.controller.pod_a.pjmb1151;

import java.util.HashMap;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleComparison;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1151.StoreVehicleComparisonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Neha Neha1@publicissapient.com
 */
@Slf4j
@RestController
@RequestMapping("/api/vehicles/comparison")
public class StoreVehicleComparisonController {

  @Autowired
  private StoreVehicleComparisonService service;

  private static final String SUCCESS = "success";
  private static final String DATA = "data";
  private static final String USER_ID= "userId";

  @PostMapping
  public ResponseEntity<Object> store(@RequestBody VehicleComparison vehicle,
      @RequestAttribute(required = false, name = "claims") Map<String, Object> claims) throws ServiceException {

    if (claims == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization header may be missing or invalid.");
    }
    log.info("POST /api/vehicles/comparison");

    Map<String, Object> resp = new HashMap<>();
    VehicleComparison saveVehicle = service.storeVehicle(vehicle, claims.get(USER_ID).toString());
    resp.put(SUCCESS, true);
    resp.put(DATA, saveVehicle);
    return ResponseEntity.ok(resp);

  }

}
