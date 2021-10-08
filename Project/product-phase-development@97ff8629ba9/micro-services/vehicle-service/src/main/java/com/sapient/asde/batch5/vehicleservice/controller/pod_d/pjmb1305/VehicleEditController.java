package com.sapient.asde.batch5.vehicleservice.controller.pod_d.pjmb1305;

import java.util.HashMap;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1305.VehicleEditService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Suraj kumar suraj.kumar3@publicissapient.com
 */
@Slf4j
@RestController
@RequestMapping("/api/vehicles/dealer")
public class VehicleEditController {

    @Autowired
    VehicleEditService service;
    private static final String SUCCESS = "success";
    private static final String DATA = "data";
    private static final String USER_ID = "userId";
    private static final String USER_TYPE = "userType";

    @PutMapping("/update-vehicle")
    public ResponseEntity<Object> updateVehicleByVehicleById(@RequestBody Vehicle vehicle,
            @RequestAttribute(required = false, name = "claims") Map<String, Object> claims) throws ServiceException {
        log.info("PUT /api/vehicles/dealer/update-vehicle");
        if (claims == null) {
            log.info("UNAUTHORIZED, Authorization header may be missing or invalid.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Authorization header may be missing or invalid.");
        } 
        String userId = (String) claims.get(USER_ID);
        String userType = (String) claims.get(USER_TYPE);
        log.info("dealerId = {} userType = {}  vehicle ={}", userId, userType,vehicle);
        if (!userType.equalsIgnoreCase("dealer")) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("UNAUTHORIZED, You are not authorized to edit this vehicle.");
        }
        Vehicle updatedVehicle = service.saveVehicle(vehicle, userId);
        Map<String, Object> resp = new HashMap<>();
        resp.put(SUCCESS, true);
        resp.put(DATA, updatedVehicle);
        return ResponseEntity.ok(resp);

    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<Object> getVehicleByVehicleId(@PathVariable String vehicleId,
            @RequestAttribute(required = false, name = "claims") Map<String, Object> claims) throws ServiceException {
        log.info("GET /api/vehicles/dealer " + vehicleId);
        if (claims == null) {
            log.info("UNAUTHORIZED, Authorization header may be missing or invalid.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Authorization header may be missing or invalid.");
        }
        String userId = (String) claims.get(USER_ID);
        String userType = (String) claims.get(USER_TYPE);
        log.info("dealerId = {} userType = {} vehicleId = {}", userId, userType, vehicleId);
        if (!userType.equalsIgnoreCase("dealer")) {
            log.info("UNAUTHORIZED, You are not authorized to view this vehicle.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED,You are not a dealer");

        }
        Vehicle vehicle = service.getVehicle(vehicleId, userId);
        Map<String, Object> resp = new HashMap<>();
        resp.put(SUCCESS, true);
        resp.put(DATA, vehicle);
        return ResponseEntity.ok(resp);

    }

}
