//@Author <Sumitesh Naithani> <sumitesh.naithani@publicissapient.com>
package com.sapient.asde.batch5.vehicleservice.controller.pod_b.pjmb1147;
import java.util.HashMap;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.entity.Feedback;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1147.VehicleReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/vehicles")
public class VehicleReviewController {
  private static final String REVIEW = "review";
  private static final String SUCCESS="success";
  private static final String FEEDBACK="feedback";
  @Autowired
  VehicleReviewService reviewService;
  
  @PostMapping("/{vehicleId}/reviews")
  public ResponseEntity<Object> updateReviewByVehicleId(@PathVariable String vehicleId , @RequestBody Map<String , String> payload , 
      @RequestAttribute(required = false, name = "claims") Map<String, Object> claims) throws ServiceException {
    if (claims == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization header may be missing or invalid.");
    }
    
    String userId = (String) claims.get("userId");
    String review = payload.get(REVIEW);
    log.info("vehicleId = {}" , vehicleId , "userId = {}" , userId , "review = {}" , review);
      Feedback feedback = reviewService.saveReview(userId, vehicleId, review);
      Map<String,Object>mp=new HashMap<>();
      mp.put(SUCCESS,true);
      mp.put(FEEDBACK, feedback);
      return ResponseEntity.ok(mp);
  }
  
  @GetMapping("/{vehicleId}/reviews")
  public ResponseEntity<Object> getReviewByVehicleId(@PathVariable String vehicleId, 
      @RequestAttribute(required = false, name = "claims") Map<String, Object> claims) throws ServiceException {
    if (claims == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization header may be missing or invalid.");
    }
    
    String userId = (String)claims.get("userId");
    log.info("vehicleId = {}" , vehicleId , "userId = {}" , userId);
      String review = reviewService.getReview(userId, vehicleId);
      Map<String,Object>mp = new HashMap<>();
      mp.put(SUCCESS , true);
      mp.put(REVIEW , review);
      return ResponseEntity.ok(mp);
  } 
}
