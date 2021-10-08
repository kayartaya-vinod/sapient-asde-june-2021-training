package com.sapient.patterns.factory;

import lombok.Data;

@Data
public abstract class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee() {
    }

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public abstract double getBonus();
}
