package com.sapient.asde.batch5.vehicleservice.controller.pod_e.pjmb1208;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.vehicleservice.entity.VehicleComparison;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1208.ComparisonMatrixMetadataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/vehicles/matrix-metadata")
public class ComparisonMatrixMetadataController {

    @Autowired
    private ComparisonMatrixMetadataService service;

    @Autowired
    ObjectMapper om;

    private static final String SUCCESS = "success";
    private static final String DATA = "data";
    private static final String USER_ID = "userId";

    /**
     * @author Akhilesh Kushwaha akhilesh.kushwaha1@publicissapient.com
     */
    @GetMapping
    public ResponseEntity<Object> getMatrixMetadata(
            @RequestAttribute(required = false, name = "claims") Map<String, Object> claims) throws ServiceException {
        log.info("in ComparisonMatrixMetadataController.getMatrixMetadata, claims is {}", claims);

        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization header is missing or invalid.");
        }

        log.info("{}, {} - GET /api/cutomers/matrix-metadata", claims.get(USER_ID).toString());

        String userId = claims.get(USER_ID).toString();
        List<VehicleComparison> result = service.getMetadataByUserId(userId);
        Map<String, Object> resp = new HashMap<>();
        resp.put(SUCCESS, true);
        resp.put(DATA, result);
        return ResponseEntity.ok(resp);
    }
}
