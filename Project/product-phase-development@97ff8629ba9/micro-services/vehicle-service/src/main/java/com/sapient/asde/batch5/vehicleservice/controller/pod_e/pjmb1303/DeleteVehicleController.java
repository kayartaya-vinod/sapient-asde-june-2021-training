package com.sapient.asde.batch5.vehicleservice.controller.pod_e.pjmb1303;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1303.DeleteVehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Shubham Chaudhari shubham.chaudhari@publicissapeint.com
 */

@Slf4j
@RestController
@RequestMapping("/api/vehicles/dealer")
public class DeleteVehicleController {

    @Autowired
    private DeleteVehicleService deleteVehicleService;

    @Autowired
    ObjectMapper om;

    private static final String SUCCESS = "success";
    private static final String USER_TYPE = "userType";
    private static final String USER_ID = "userId";

    @DeleteMapping()
    public ResponseEntity<Object> deleteVehicle( @RequestParam List <String> ids, 
    @RequestAttribute(required = false, name = "claims") Map<String, Object> claims) throws IOException, ServiceException
    {
          if (claims == null) {
            log.info("DELETE /api/vehicles/dealer/" + ids.toString());
            log.info("UNAUTHORIZED, Authorization header may be missing or invalid.");
            throw new ServiceException("Authorization header may be missing or invalid.",HttpStatus.UNAUTHORIZED);
          }
          if (!claims.get(USER_TYPE).toString().equalsIgnoreCase("dealer")) {
            log.info("DELETE /api/vehicles/" + ids.toString());
            log.info("UNAUTHORIZED, You are not authorized to delete this vehicle"+claims.get(USER_ID));
            throw new ServiceException("Authorization header may be missing or invalid.",HttpStatus.UNAUTHORIZED);
          }
          log.info("DELETE /api/vehicles/dealer/" + ids.toString());  
          
          for(String id : ids)
          {
            deleteVehicleService.deleteVehicleById(id,claims.get(USER_ID).toString());
          }
          
        Map<String, Object> resp = new HashMap<>();
        resp.put(SUCCESS, true);
        return ResponseEntity.ok(resp);
    }
    
}
