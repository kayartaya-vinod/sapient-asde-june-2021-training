package com.sapient.asde.batch5.customerservice.controller;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.customerservice.entity.Customer;
import com.sapient.asde.batch5.customerservice.service.CustomerService;
import com.sapient.asde.batch5.customerservice.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/customers")
// @CrossOrigin
public class CustomerController {
  @Autowired
  CustomerService service;

  @Autowired
  ObjectMapper om;

  private static final String SUCCESS = "success";
  private static final String DATA = "data";
  private static final String NAME = "firstName";
  private static final String USER_ID = "userId";
  private static final String ERROR = "error";

  /**
   * @author Neha neha1@publicissapient.com
   */
  @PostMapping("/change-password")
  public ResponseEntity<Object> changePassword(@RequestBody Map<String, Object> payload,
      @RequestAttribute(required = false, name = "claims") Map<String, Object> claims) throws ServiceException {

    log.trace("in CustomerController.changePassword, payload is {} and claims is {}", payload, claims);

    if (claims == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization header is missing or invalid.");
    }

    if (claims.containsKey(ERROR)) {
      return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(claims.get(ERROR));
    }

    log.trace("{}, {} - POST /api/customers/change-password", claims.get(NAME).toString(),
        claims.get(USER_ID).toString());

    Map<String, Object> map = new HashMap<>();
    String password = om.convertValue(payload.get("password"), String.class);
    String oldPassword = om.convertValue(payload.get("old_password"), String.class);
    String confirmPassword = om.convertValue(payload.get("confirm_password"), String.class);
    String userId = om.convertValue(claims.get(USER_ID), String.class);
    String token = service.changePassword(userId, password, oldPassword, confirmPassword);
    map.put(SUCCESS, true);
    map.put("name", claims.get(NAME).toString());
    map.put("token", token);
    return ResponseEntity.ok(map);

  }

  /**
   * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
   */
  @PutMapping
  public ResponseEntity<Object> updateCustomerById(@RequestBody Customer customer,
      @RequestAttribute(required = false, name = "claims") Map<String, Object> claims) throws ServiceException {
    if (claims == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization header may be missing or invalid.");
    }
    if (claims.containsKey(ERROR)) {
      return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(claims.get(ERROR));
    }

    log.trace("{}, {} - POST /api/customers", claims.get(NAME).toString(), claims.get(USER_ID).toString());

    customer.setUserId(claims.get(USER_ID).toString());
    Map<String, Object> resp = new HashMap<>();
    Customer updatedCustomer = service.updateCustomerById(customer);
    resp.put(SUCCESS, true);
    resp.put(DATA, updatedCustomer);
    return ResponseEntity.ok(resp);
  }

  /**
   * @author Abhishek Singh abhishek.singh8@publicissapient.com
   */
  @PostMapping("/reset-password")
  public ResponseEntity<Object> resetPassword(@RequestBody Map<String, Object> payload) throws ServiceException {

    log.trace("POST /api/customers/reset-password");

    Map<String, Object> map = new HashMap<>();

    String token = om.convertValue(payload.get("token"), String.class);
    String password = om.convertValue(payload.get("password"), String.class);
    service.resetPassword(token, password);
    map.put(SUCCESS, true);
    return ResponseEntity.ok(map);

  }

  /**
   * @author Pritam Patel pritam.patel@publicissapient.com
   */
  @GetMapping
  public ResponseEntity<Object> getCustomerByUserId(
      @RequestAttribute(required = false, name = "claims") Map<String, Object> claims)
      throws IllegalArgumentException, ServiceException {

    if (claims.containsKey(ERROR)) {
      return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(claims.get(ERROR));
    }

    log.trace("{} {} - GET /api/customers", claims.get(NAME).toString(), claims.get(USER_ID).toString());

    Map<String, Object> map = new HashMap<>();

    Customer c1 = service.getCustomerByUserId(claims.get(USER_ID).toString());
    map.put(SUCCESS, true);
    map.put(DATA, c1);
    return ResponseEntity.ok(map);
  }

  /**
   * @author Aditya Gheewala aditya.gheewala@publicissapient.com
   */
  @GetMapping("/reset-password-link")
  public ResponseEntity<Object> sendResetPasswordLink(@RequestParam String email) throws ServiceException {

    log.trace("{} - GET /api/customers/reset-password-link", email);

    service.resetPasswordLink(email);
    Map<String, Object> map = new HashMap<>();
    map.put(SUCCESS, true);
    return ResponseEntity.ok(map);
  }

  /**
   * @author Mritunjay Kumar Jay and Jay Aditya Nautiyal
   */
  @PostMapping("/add-customer/")
  public ResponseEntity<Object> addCustomer(@RequestBody Map<String,String> payload) throws ServiceException{
    log.trace("{} - POST /api/customers/add-customer/", payload);
    log.info("{} - POST /api/customers/add-customer/", payload);

    service.addCustomer(payload.get("userId"));
    Map<String, Object> map = new HashMap<>();
    map.put(SUCCESS, true);
    return ResponseEntity.ok(map);
  }
}
