package com.sapient.asde.batch5.vehicleservice.controller.pod_a.pjmb1154;

import java.util.HashMap;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleComparison;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1154.VehicleComparisonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
@Slf4j
@RestController
@RequestMapping("/api/vehicles/comparison")
public class VehicleComparisonController {

  @Autowired
  private VehicleComparisonService service;

  private static final String SUCCESS = "success";
  private static final String DATA = "data";
  private static final String USER_ID = "userId";

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Map<String, Object> comparisionMatrix(@PathVariable String id,@RequestAttribute(required = false, name = "claims") Map<String, Object> claims) throws ServiceException {
    if (claims == null) {
      log.info("GET /api/vehicles/comparison/" + id);
      log.info("UNAUTHORIZED, Authorization header may be missing or invalid.");

      throw new ServiceException("Authorization header may be missing or invalid.", HttpStatus.UNAUTHORIZED);
    }
    log.info("GET /api/vehicles/comparison/" + id);

    String userId = claims.get(USER_ID).toString();
    VehicleComparison matrix = service.getComparisonMatrix(id, userId);

    Map<String, Object> map = new HashMap<>();

    map.put(SUCCESS, true);
    map.put(DATA, matrix);
    return map;
  }
}
