package com.sapient.patterns.factory;

// closed for extension
public final class EmployeeFactory {
    private EmployeeFactory() {
    }

    // factory method for creating a type of employee
    public static Employee createEmployee(int id, String name, double salary, String type) {
        if (type.equals("permanent")) {
            return new PermanentEmployee(id, name, salary);
        } else if (type.equals("temporary")) {
            return new TemporaryEmployee(id, name, salary);
        } else {
            throw new IllegalArgumentException("Invalid type for employee");
        }
    }
}
