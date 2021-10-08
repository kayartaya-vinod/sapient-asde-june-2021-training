package com.sapient.dao;

import java.util.List;

import com.sapient.entity.Employee;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends MongoRepository<Employee, String> {
    public Employee findByEmail(String email);

    public List<Employee> findAllByCity(String city);
}
