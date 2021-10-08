package com.sapient.asde.batch5.vehicleservice.controller.pod_e.pjmb1349;

import java.util.HashMap;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1349.GetVehicleListByDealerIdService;

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

@Slf4j
@RestController
@RequestMapping("/api/vehicles/dealer")
public class GetVehicleListByDealerIdController {

    @Autowired
    private GetVehicleListByDealerIdService service;

    private static final String SUCCESS = "success";
    private static final String DATA = "data";
    private static final String USER_ID = "userId";
    private static final String USER_TYPE = "userType";

    @GetMapping
    public ResponseEntity<Object> getVehicleList(
            @RequestAttribute(required = false, name = "claims") Map<String, Object> claims,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page) throws ServiceException {
        log.info("in GetVehicleListByController.getVehicleList, claims is {}", claims);

        if (claims == null) {
            throw new ServiceException("Authorization header may be missing or invalid.", HttpStatus.UNAUTHORIZED);
        }
        if (!claims.get(USER_TYPE).toString().equalsIgnoreCase("dealer")) {
            throw new ServiceException("You are not a dealer", HttpStatus.UNAUTHORIZED);
        }

        String dealerId = claims.get(USER_ID).toString();
        Page<Vehicle> result = service.getVehicleListByDealerId(dealerId, page);
        Map<String, Object> resp = new HashMap<>();
        resp.put(SUCCESS, true);
        resp.put(DATA, result);
        return ResponseEntity.ok(resp);
    }
}
