
/**
 * @author Rohit Bhatt rohit.bhatt1@publicissapient.com
 */
package com.sapient.asde.batch5.vehicleservice.controller.pod_b.pjmb1683;

import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1683.AddVehicleAccessoryService;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/vehicles")
public class AddVehicleAccessoryController {

    @Autowired
    AddVehicleAccessoryService service;

    @PostMapping("/add-accessory")
    public ResponseEntity<Object> addVehicleAccessory(@RequestBody Document payload,
            @RequestAttribute(required = false, name = "claims") Map<String, Object> claims) {
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Authorization header may be missing or invalid!");
        }
        if (payload == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payload Missing");

        }
        String dealerId = (String) claims.get("userId");

        try {
            service.add(payload, dealerId);
            log.trace("POST /api/vehicles/add-accessory");
            return ResponseEntity.ok("Inserted Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
