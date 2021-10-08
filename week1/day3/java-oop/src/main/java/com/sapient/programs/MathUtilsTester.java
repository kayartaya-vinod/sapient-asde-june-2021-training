package com.sapient.programs;

import com.sapient.utils.MathUtils;

public class MathUtilsTester {

    static class Person {

    }

    static void createPerson() {
        Person p1;
        p1 = new Person();
    }

    public static void main(String[] args) {

        createPerson();
        // static functions cannot make use of non-static members
        Person p1;
        p1 = new Person();
        MathUtils mu = new MathUtils();
        double num = 12.34;
        mu.setNum(num);

        double sqr = mu.square();
        System.out.printf("Square of %f is %f\n", num, sqr);
    }
}
