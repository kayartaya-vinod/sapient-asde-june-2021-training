package com.sapient.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student extends Person {
    private int rollno;
    private String major;
    private double gpa;

    @Override
    public String toString() {
        return "Student [" + super.toString() + ", gpa=" + gpa + ", major=" + major + ", rollno=" + rollno + "]";
    }

    // the toString() of this class hides the visibility of the inherited toString()
    // from Person class

}
