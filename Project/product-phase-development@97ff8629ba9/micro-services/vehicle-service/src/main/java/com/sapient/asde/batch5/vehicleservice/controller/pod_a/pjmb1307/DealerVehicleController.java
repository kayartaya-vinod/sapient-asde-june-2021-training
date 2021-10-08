

/**
 * @author Pritam Patel pritam.patel@publicissapient.com
 */

package com.sapient.asde.batch5.vehicleservice.controller.pod_a.pjmb1307;

import java.util.HashMap;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1307.DealerVehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;



@RequestMapping("/api/vehicles")
@RestController
@Slf4j
public class DealerVehicleController {

    @Autowired
    private DealerVehicleService service;
     
    private static final String SUCCESS = "success";
    private static final String DATA = "data";
    private static final String USER_ID= "userId";
    private static final String USER_TYPE= "userType";
    private static final String MESSAGE= "message";
    private static final String SUCCESSMESSAGE= "Vehicle is added successfully";


    @PostMapping
    public ResponseEntity<Object> store(@RequestBody Vehicle vehicle,
     @RequestAttribute(required = false , name = "claims") Map<String, Object> claims) throws ServiceException{

        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization header may be missing or invalid.");    
        }
        if(!claims.get(USER_TYPE).toString().equalsIgnoreCase("dealer"))
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not a dealer");     
        }
        log.info("POST /api/vehicles");

        Map<String, Object> map = new HashMap<>();
        Vehicle saveDealer = service.addNewVehicle(vehicle, claims.get(USER_ID).toString());
        map.put(SUCCESS, true);
        map.put(DATA, saveDealer);
        map.put(MESSAGE,SUCCESSMESSAGE);
        return ResponseEntity.ok(map); 
     }
   
    
}
