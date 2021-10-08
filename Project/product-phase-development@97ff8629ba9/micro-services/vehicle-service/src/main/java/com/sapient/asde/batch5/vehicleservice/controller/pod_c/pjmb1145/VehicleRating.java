package com.sapient.asde.batch5.vehicleservice.controller.pod_c.pjmb1145;

import java.util.HashMap;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.entity.Feedback;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_c.pjmb_1145.RatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Mritunjay Kumar Jay mritunjay.jay@publicissapient.com
 */
@Slf4j
@RestController
@RequestMapping("api/vehicles/rating/")
public class VehicleRating {
    @Autowired
    private RatingService service;

    private static final String SUCCESS = "success";
    private static final String DATA = "data";
    private static final String USER_ID = "userId";


    @PostMapping
    public ResponseEntity<Object> updateRating(@RequestBody Map<String,String> payload,
            @RequestAttribute(required = false, name = "claims") Map<String, Object> claims) throws ServiceException {
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Authorization header may be missing or invalid.");
        }
        String vehicleId = payload.get("vehicleId");
        
        String userId = claims.get(USER_ID).toString();
        String rating = payload.get("rating");
        
        log.info("vehicleId = {}", vehicleId, "userId = {}", userId, "rating = {}", rating);
            Feedback feedback = service.addRating(vehicleId, userId, Integer.parseInt(rating));
            Map<String, Object> resp = new HashMap<>();
            resp.put(SUCCESS, true);
            resp.put(DATA, feedback);
            return ResponseEntity.ok(resp);
        
    }
    
    @GetMapping("{vehicleId}")
    public ResponseEntity<Object> getRating(@PathVariable String vehicleId,
            @RequestAttribute(required = false, name = "claims") Map<String, Object> claims) throws ServiceException {
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Authorization header may be missing or invalid.");
        }

        String userId = claims.get(USER_ID).toString();
        
        log.info("vehicleId = {}", vehicleId, "userId = {}", userId, "payload = {}");
            Integer rating = service.getRating(vehicleId, userId);
            Map<String, Object> resp = new HashMap<>();
            resp.put(SUCCESS, true);
            Feedback feedback = new Feedback();
            feedback.setVehicleId(vehicleId);
            feedback.setRating(rating);
            feedback.setUserId(userId);
            resp.put(DATA, feedback);
            return ResponseEntity.ok(resp);
    }
}