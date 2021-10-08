//@Author Jay
package com.sapient.asde.batch5.vehicleservice.controller.pod_c.pjmb1313;

import java.util.HashMap;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_c.pjmb1313.AverageRatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicles")
public class AverageRatingController {
    private static final String SUCCESS = "success";
    private static final String AVERAGE_RATING = "averageRating";
    private static final String TOTAL_CUSTOMERS = "totalCustomer";
    @Autowired
    AverageRatingService averageRatingService ;

    @GetMapping("/average-rating/{vehicleId}")
    public ResponseEntity<Object> getAverageRating(@PathVariable String vehicleId) throws ServiceException {
        
        Map<String, Object> averageRating = averageRatingService.getAverageRating(vehicleId);
        Map<String, Object> mp = new HashMap<>();
        mp.put(SUCCESS, true);
        mp.put(AVERAGE_RATING , averageRating.get(AVERAGE_RATING));
        mp.put(TOTAL_CUSTOMERS, averageRating.get(TOTAL_CUSTOMERS));
        return ResponseEntity.ok(mp);
    }
}
