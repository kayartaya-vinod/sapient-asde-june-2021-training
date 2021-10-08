package com.sapient.service;

import java.util.List;
import java.util.Optional;

import com.sapient.dao.EmployeeDao;
import com.sapient.entity.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDao dao;

    public Employee addNewEmployee(Employee emp) {
        if (emp == null || emp.getName() == null) {
            throw new ServiceException("name cannot be null");
        }

        return dao.insert(emp);
    }

    public Employee getEmployeeById(String id) {
        if (id == null || id.trim().equals("")) {
            throw new ServiceException("id cannot be null or empty string");
        }
        Optional<Employee> op = dao.findById(id);
        if (op.isPresent()) {
            return op.get();
        } else {
            return null;
        }
    }

    public Employee getEmployeeByEmail(String email) {
        return dao.findByEmail(email);
    }

    public List<Employee> getAll() {
        return dao.findAll();
    }

    public List<Employee> getAllInCity(String city) {
        return dao.findAllByCity(city);
    }
}
