package com.sapient.asde.batch5.vehicleservice.controller.pod_d.pjmb1681;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1681.DeleteVehicleAccessoryService;
import java.io.IOException;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import java.util.Map;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Aravind M Krishnan aravind.m.krishnan@publicissapeint.com
 */

@Slf4j
@RestController
@RequestMapping("/api/vehicles/dealer/delete-accessory")
public class DeleteVehicleAccessoryController {

    @Autowired
    private DeleteVehicleAccessoryService service;

    @Autowired
    ObjectMapper om;

    private static final String SUCCESS = "success";
    private static final String MESSAGE = "message";
    private static final String USER_TYPE = "userType";
    private static final String USER_ID = "userId";

    @DeleteMapping()
    public ResponseEntity<Object> deleteVehicleAccessory(@RequestParam String id,
            @RequestAttribute(required = false, name = "claims") Map<String, Object> claims)
            throws IOException, ServiceException {
        if (claims == null) {
            log.info("DELETE /api/vehicle/delete-accessory" + id);
            log.info("UNAUTHORIZED, Authorization header may be missing or invalid.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Authorization header may be missing or invalid.");
        }
        String userType = (String) claims.get(USER_TYPE);

        if (!userType.equalsIgnoreCase("dealer")) {
            log.info("DELETE /api/vehicle" + id);
            log.info("UNAUTHORIZED, You are not authorized to delete this vehicle accessory" + claims.get(USER_ID));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("UNAUTHORIZED, You are not authorized to delete this vehicle accessory.");
        }
        log.info("DELETE /api/vehicle/delete-accessory/" + id);
        log.info("dealerId = {}", claims.get(USER_ID).toString());
        service.deleteVehicleAccessoryById(id, claims.get(USER_ID).toString());

        Map<String, Object> resp = new HashMap<>();
        resp.put(SUCCESS, true);
        resp.put(MESSAGE, "Successfully deleted");
        return ResponseEntity.ok(resp);
    }

}
