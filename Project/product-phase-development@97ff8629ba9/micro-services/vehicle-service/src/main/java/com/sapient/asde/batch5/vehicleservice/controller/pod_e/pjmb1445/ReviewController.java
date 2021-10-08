package com.sapient.asde.batch5.vehicleservice.controller.pod_e.pjmb1445;


import java.util.HashMap;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1445.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicles/reviews")
public class ReviewController {
    @Autowired
    ReviewService service;
    private static final String SUCCESS = "success";
  private static final String DATA = "data";
    @GetMapping  
    public ResponseEntity<Object> review(@RequestParam String q ) throws ServiceException {
      
        if (q.equals("")) {
          return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nothing entered");
    
        } else {
          String[][] feedback = service.getfeedbacks(q);
          Map<String, Object> map = new HashMap<>();

          map.put(SUCCESS, true);
          map.put(DATA, feedback);
          return ResponseEntity.ok(map);
        }
      }
}
