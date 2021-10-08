package com.sapient.asde.batch5.customerservice.controller.pod_d.pjmb1317;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.customerservice.entity.Vehicle;
import com.sapient.asde.batch5.customerservice.service.ServiceException;
import com.sapient.asde.batch5.customerservice.service.pod_d.pjmb1317.FavoriteVehicleService;

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
@RequestMapping("/api/customers")
public class FavoriteVehicleController {
  @Autowired
  FavoriteVehicleService service;

  @Autowired
  ObjectMapper om;

  private static final String SUCCESS = "success";
  private static final String DATA = "data";
  private static final String NAME = "firstName";
  private static final String USER_ID = "userId";

  private static final String ERROR = "error";

  /**
   * @author vikash kumar gupta
   */
  @GetMapping("/favorites")
  public ResponseEntity<Object> getCustomerWishlist(
      @RequestAttribute(required = false, name = "claims") Map<String, Object> claims)
      throws IllegalArgumentException, ServiceException {

    if (claims == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization header may be missing or invalid.");
    }
    if (claims.containsKey(ERROR)) {
      return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(claims.get(ERROR));
    }

    Map<String, Object> map = new HashMap<>();

    List<Vehicle> list = service.customerWishlist(claims.get(USER_ID).toString());
    map.put(SUCCESS, true);
    map.put(DATA, list);
    return ResponseEntity.ok(map);
  }
}
