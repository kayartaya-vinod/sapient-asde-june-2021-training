package com.sapient.asde.batch5.vehicleservice.controller.pod_d.pjmb1677;

import java.util.HashMap;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1677.ReviewRatingForDealerUploadsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author Deepthi.S deepthi.s@publicissapient.com
 */
@Slf4j
@RestController
@RequestMapping("/api/vehicles/dealer")
public class ReviewRatingForDealerUploadsController {

    @Autowired
    ReviewRatingForDealerUploadsService service;
    private static final String SUCCESS = "success";
    private static final String DATA = "data";
    private static final String USER_ID = "userId";
    private static final String USER_TYPE = "userType";

    @GetMapping("/feedbacks")
    public ResponseEntity<Object> getFeedbacks(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestAttribute(required = false, name = "claims") Map<String, Object> claims) throws ServiceException {

        log.trace("GET /api/vehicles/dealer/feedbacks");
        if (claims == null) {
            log.trace("UNAUTHORIZED, Authorization header may be missing or invalid.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Authorization header may be missing or invalid.");
        }

        String dealerId = (String) claims.get(USER_ID);
        String userType = (String) claims.get(USER_TYPE);
        log.trace("dealerId = {} userType = {} ", dealerId, userType);

        if (!userType.equalsIgnoreCase("dealer")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("UNAUTHORIZED, You are not authorized as a dealer.");
        }

        Map<String, Object> fb = service.getFeedbackForDealerUploads(dealerId, page);
        Map<String, Object> resp = new HashMap<>();
        resp.put(SUCCESS, true);
        resp.put(DATA, fb);
        return ResponseEntity.ok(resp);

    }
}
