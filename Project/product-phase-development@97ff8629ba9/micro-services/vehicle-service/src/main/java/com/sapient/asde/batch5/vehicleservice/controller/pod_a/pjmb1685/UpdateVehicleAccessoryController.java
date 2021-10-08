/**
 * @author Pritam  Patel  pritam.patel@publicissapient.com
 * 
 */

package com.sapient.asde.batch5.vehicleservice.controller.pod_a.pjmb1685;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1685.UpdateVehicleAccessoryService;
import com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1612.GetVehicleAccessoryService;
import org.bson.Document;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/vehicles")
public class UpdateVehicleAccessoryController {

  private static final String SUCCESS = "success";
  private static final String DATA = "data";

  @Autowired
  UpdateVehicleAccessoryService service;
  @Autowired
  GetVehicleAccessoryService service2;

  @PutMapping("/accessory")
  public ResponseEntity<Object> updateAccessoryById(@RequestBody Document payload,
      @RequestAttribute(required = false, name = "claims") Map<String, Object> claims) {

    if (claims == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization header may be missing or invalid .");
    }
    String dealerId = (String) claims.get("userId");

    try {
      String id = payload.get("id").toString();
      service.update(payload, dealerId);
      List<JsonObject> jbl = service2.getVehicleAccessory(id);

      log.trace("Inside Controller of update vehicle accessory");
      log.trace("PUT /api/vehicles/accessory");

      Map<String, Object> resp = new HashMap<>();
      resp.put(SUCCESS, true);
      resp.put(DATA, jbl);
      return ResponseEntity.ok(resp);

    }

    catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

  }

}
