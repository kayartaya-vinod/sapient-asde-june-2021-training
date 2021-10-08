package com.sapient.entity;

import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class Employee {
    public int id;
    public String name;
    private double salary;

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Invalid salary");
        }
        this.salary = salary;
    }

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public void print() {
        System.out.println("Id = " + id);
        System.out.println("Name = " + name);
        System.out.println("Salary = " + salary);
        System.out.println();
    }

}
