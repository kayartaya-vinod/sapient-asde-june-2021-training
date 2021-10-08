package com.sapient.patterns.factory;

public class TemporaryEmployee extends Employee {
    public TemporaryEmployee() {
    }

    public TemporaryEmployee(int id, String name, double salary) {
        super(id, name, salary);
    }

    @Override
    public double getBonus() {
        return this.getSalary() * 0.05;
    }

}
