package com.sapient.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.sapient.dao.CustomerRepository;
import com.sapient.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    CustomerRepository repo;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable String id) {
        Optional<Customer> retVal = repo.findById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date());

        if (retVal.isPresent()) {
            map.put("success", true);
            map.put("data", retVal.get());
            return ResponseEntity.ok(map);
        } else {
            map.put("success", false);
            map.put("message", "No data found for id " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }

    @GetMapping
    public Iterable<Customer> getAll() {
        return repo.findAll();
    }
}
