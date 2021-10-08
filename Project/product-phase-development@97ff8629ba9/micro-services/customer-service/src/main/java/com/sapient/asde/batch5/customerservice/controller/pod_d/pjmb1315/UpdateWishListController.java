package com.sapient.asde.batch5.customerservice.controller.pod_d.pjmb1315;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sapient.asde.batch5.customerservice.service.ServiceException;
import com.sapient.asde.batch5.customerservice.service.pod_d.pjmb1315.UpdateWishListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/customers")
public class UpdateWishListController {
    @Autowired
    UpdateWishListService updateService;

    private static final String SUCCESS = "success";
    private static final String DATA = "data"; 
    private static final String USER_ID = "userId";
    private static final String USER_TYPE = "userType";

    /**
     *  @author Abhishek Rajgaria, abhishek.rajgaria@publicissapient.com
     */

    @PutMapping("/favorites/{vehicleId}")
    public ResponseEntity<Object> updateCustomerWishList(
        @PathVariable String vehicleId, @RequestAttribute(required= false, name="claims") Map<String,Object> claims) throws ServiceException{
            log.info("PUT /api/customers/favorites/" + vehicleId);
            if(claims==null){
                log.info("UNAUTHORIZED: Authorization may be missing or invalid");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization may be missing or invalid");
            }

            String userId = (String) claims.get(USER_ID);
            String userType = (String) claims.get(USER_TYPE);

            log.info("userId = {} userType = {}  vehicleId ={}", userId, userType,vehicleId);
            if (!userType.equalsIgnoreCase("customer")) {
                log.info("UNAUTHORIZED, You are not authorized to update favorite vehicle");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED,You are not a customer");
            }
            List<String> ids = updateService.updateWishList(userId, vehicleId);
            Map<String, Object> response = new HashMap<>();
            response.put(SUCCESS,true);
            response.put(DATA, ids);
            return ResponseEntity.ok(response);
        }
    
}
