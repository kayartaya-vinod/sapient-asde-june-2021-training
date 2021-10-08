package com.sapient.controller;

import java.util.List;

import com.sapient.entity.Employee;
import com.sapient.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @GetMapping
    public List<Employee> employees() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable String id) {
        Employee emp = service.getEmployeeById(id);
        if (emp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(emp);
    }

    @GetMapping(params = { "email" })
    public Employee getEmployeeByEmail(@RequestParam("email") String email) {
        return service.getEmployeeByEmail(email);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        log.debug("got this employee for adding - {}", employee);
        return service.addNewEmployee(employee);
    }

}
