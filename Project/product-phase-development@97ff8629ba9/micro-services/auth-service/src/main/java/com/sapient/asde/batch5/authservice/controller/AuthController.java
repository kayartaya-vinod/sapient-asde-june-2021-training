package com.sapient.asde.batch5.authservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sapient.asde.batch5.authservice.JwtUtil;
import com.sapient.asde.batch5.authservice.entity.User;
import com.sapient.asde.batch5.authservice.service.AuthService;
import com.sapient.asde.batch5.authservice.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService service;
    String message = "message";
    String tooken = "token";
    String name = "name";
    String emailString = "email";
    String id = "id";
    private static final String SUCCESS = "success";
    private static final String USERTYPE = "userType";

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        try {
            Map<String, Object> map = new HashMap<>();
            List<String> auth = service.login(user.getEmail(), user.getPassword());
            map.put(tooken, auth.get(0));
            map.put(name, auth.get(1));
            map.put(id, auth.get(2));
            map.put(emailString, auth.get(3));
            map.put(USERTYPE,auth.get(4));
            return ResponseEntity.ok(map);
        } catch (ServiceException e) {
            Map<String, Object> map = new HashMap<>();
            map.put(message, e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User user)  {
        try {
            Map<String, Object> map = new HashMap<>();
            List<String> auth = service.register(user);
            log.info("auth is  is {}", auth);
            map.put(tooken, auth.get(0));
            map.put(name, auth.get(1));
            map.put(id, auth.get(2));
            map.put(emailString, auth.get(3));
            return ResponseEntity.ok(map);
        } catch (ServiceException e) {
            e.printStackTrace();
            Map<String, Object> map = new HashMap<>();
            map.put(message, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<Object> varifyJwt(@RequestParam String jwt) {
        try {
            Map<String, Object> map = JwtUtil.verify2(jwt);
            return ResponseEntity.ok(map);

        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put(message, "Token invalid or expired");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        }

    }
    
    @PostMapping("/verify-account")
    public ResponseEntity<Object> verifyCustomer(@RequestHeader("Authorization") String authHeader) {
        try {
            String jwt = authHeader.split(" ")[1];
            String  email = (String) JwtUtil.verify2(jwt).get("email");

            Map<String, Object> map = new HashMap<>();
            map.put(SUCCESS,true);
            map.put(name, service.verifyCustomer(email));
            log.info("map is {}",map);
            return ResponseEntity.ok(map);
            
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            if(e.getMessage().equals("User already verified")){
                map.put(message, e.getMessage());
            }
            else{
                map.put(message, "Token invalid or expired");
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        }

    }

    @GetMapping("/get-token")
    public ResponseEntity<Object> getToken(@RequestParam String email) {
        try {
            Map<String, Object> map = new HashMap<>();
            List<String> auth = service.createToken(email);
            map.put(tooken, auth.get(0));
            map.put(name, auth.get(1));
            map.put(id, auth.get(2));
            map.put(emailString, auth.get(3));
            return ResponseEntity.ok(map);
        } catch (ServiceException e) {
            Map<String, Object> map = new HashMap<>();
            e.printStackTrace();
            map.put(message, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
    }

    @PostMapping("/update-password")
    public ResponseEntity<Object> updatePassword(@RequestHeader("Authorization") String authHeader,
            @RequestBody Map<String, String> mp) {

        try {
            String token = authHeader.split(" ")[1];
            Map<String, Object> map = new HashMap<>();
            List<String> auth = service.updatePassword(token, mp.get("password"));
            map.put(tooken, auth.get(0));
            map.put(name, auth.get(1));
            map.put(id, auth.get(2));
            map.put(emailString, auth.get(3));
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put(message, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
    }

}
