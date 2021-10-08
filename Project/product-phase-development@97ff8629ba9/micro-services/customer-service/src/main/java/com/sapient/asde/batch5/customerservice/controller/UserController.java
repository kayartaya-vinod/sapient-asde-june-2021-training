package com.sapient.asde.batch5.customerservice.controller;

import com.sapient.asde.batch5.customerservice.service.ServiceException;
import com.sapient.asde.batch5.customerservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")

public class UserController {
    @Autowired
    UserService service;
    @GetMapping
  public ResponseEntity<Object> nameOfUser(@RequestParam String q) throws ServiceException {

    if (q.equals("")) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nothing entered");

    } else {
      String name = service.getByUserId(q);
      return ResponseEntity.ok(name);
    }
  }
}
