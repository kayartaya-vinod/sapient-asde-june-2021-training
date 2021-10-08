package com.kvinod.controller;

import com.kvinod.entity.User;
import com.kvinod.service.AuthService;
import com.kvinod.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService service;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        try {
            return ResponseEntity.ok(service.login(user.getEmail(), user.getPassword()));
        } catch (ServiceException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You have entered wrong email or password");
        }
    }
}
