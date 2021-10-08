package com.sapient.asde.batch5.vehicleservice.controller.pod_a.pjmb1155;

import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1155.VehicleComparisonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Abhishek Singh abhishek.singh8@publicissapient.com
 */
@Slf4j
@RestController
@RequestMapping("/api/vehicles/comparison")
public class VehicleComaprisonController {

  @Autowired
  @Qualifier("pjmb1155_vehiclecomparisonserviceimpl")
  private VehicleComparisonService service;

  private static final String FIRST_NAME = "firstName";
  private static final String USER_ID = "userId";

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteComparisonMatrix(@PathVariable String id,
      @RequestAttribute(required = false, name = "claims") Map<String, Object> claims) throws ServiceException {

    if (claims == null) {
      log.info("DELETE /api/vehicles/comparison/" + id);
      log.info("UNAUTHORIZED, Authorization header may be missing or invalid.");

      throw new ServiceException("Authorization header may be missing or invalid.", HttpStatus.UNAUTHORIZED);
    }

    String userId = claims.get(USER_ID).toString();
    String firstName = claims.get(FIRST_NAME).toString();

    log.info("DELETE /api/vehicles/comparison/{} - {}, {}", id, firstName, userId);

    service.deleteComparisonMatrixById(id, userId);
  }
}
