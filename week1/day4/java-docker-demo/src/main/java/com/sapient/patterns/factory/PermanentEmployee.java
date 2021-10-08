package com.sapient.patterns.factory;

public class PermanentEmployee extends Employee {
    public PermanentEmployee() {
    }

    public PermanentEmployee(int id, String name, double salary) {
        super(id, name, salary);
    }

    @Override
    public double getBonus() {
        return super.getSalary() * 0.15;
    }

}
