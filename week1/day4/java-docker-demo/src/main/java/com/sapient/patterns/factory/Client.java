package com.sapient.patterns.factory;

import java.sql.DriverManager;

public class Client {
    public static void main(String[] args) {
        Employee e1 = EmployeeFactory.createEmployee(1122, "Samuel", 3899, "permanent");
        Employee e2 = EmployeeFactory.createEmployee(8272, "John", 2999, "temporary");

        // DriverManager.getConnection(url, username, password);

        System.out.println("e1 = " + e1);
        System.out.println("e2 = " + e2);

        System.out.println(e1.getName() + " gets a bonus of $" + e1.getBonus());
        System.out.println(e2.getName() + " gets a bonus of $" + e2.getBonus());

    }
}
