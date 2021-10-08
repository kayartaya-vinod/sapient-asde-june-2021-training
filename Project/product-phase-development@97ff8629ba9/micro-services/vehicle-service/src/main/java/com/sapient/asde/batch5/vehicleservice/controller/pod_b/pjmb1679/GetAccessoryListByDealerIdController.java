package com.sapient.asde.batch5.vehicleservice.controller.pod_b.pjmb1679;

import java.util.HashMap;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleAccessory;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1679.GetAccesssoryListByDealerIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
/**
 * @author Manvendra Singh
 */
@Slf4j
@RestController
@RequestMapping("/api/vehicles/dealer/accessories")
public class GetAccessoryListByDealerIdController {

    @Autowired
    private GetAccesssoryListByDealerIdService service;

    private static final String SUCCESS = "success";
    private static final String DATA = "data";
    private static final String USER_ID = "userId";
    private static final String USER_TYPE = "userType";

    @GetMapping
    public ResponseEntity<Object> getAccessoryList(
            @RequestAttribute(required = false, name = "claims") Map<String, Object> claims,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page) throws ServiceException {
        log.trace("in GetAccessoryListByController.getAccessoryList, claims is {}", claims);

        if (claims == null) {
            throw new ServiceException("Authorization header may be missing or invalid.", HttpStatus.UNAUTHORIZED);
        }
        if (!((String) claims.get(USER_TYPE)).equalsIgnoreCase("dealer")) {
            throw new ServiceException("You are not a dealer", HttpStatus.UNAUTHORIZED);
        }

        String dealerId = claims.get(USER_ID).toString();
        Page<VehicleAccessory> result =service.getAccessoryListByDealerId(dealerId, page);
        Map<String, Object> resp = new HashMap<>();
        resp.put(SUCCESS, true);
        resp.put(DATA, result);
        return ResponseEntity.ok(resp);
    }
}
