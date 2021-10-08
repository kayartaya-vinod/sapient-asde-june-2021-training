package com.sapient.programs;

import com.sapient.entity.Employee;

public class EmployeeTester {
    public static void main(String[] args) {

        Employee e1 = new Employee();
        e1.id = 1122;
        e1.name = "Ramesh";
        // e1.salary = -45000;
        e1.setSalary(45000);

        e1.print();
        System.out.println("--------------------");
        System.out.println(e1); // e1 is converted into a String by calling its toString() function
        System.out.println("--------------------");
    }
}
