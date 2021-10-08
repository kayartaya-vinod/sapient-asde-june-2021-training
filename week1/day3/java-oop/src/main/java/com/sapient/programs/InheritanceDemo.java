package com.sapient.programs;

import com.sapient.entity.Person;
import com.sapient.entity.Student;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InheritanceDemo {
    public static void main(String[] args) {
        Person p1;

        p1 = new Person();
        p1.setName("Vinod");
        p1.setEmail("vinod@vinod.co");

        p1.log();

        Student s1;
        s1 = new Student();
        s1.setName("Kumar");
        s1.setEmail("kumar@xmpl.com");
        s1.setRollno(1234);
        s1.setMajor("Physics");
        s1.setGpa(3.8);
        s1.log();

        log.debug("p1 = {}", p1);
        log.debug("s1 = {}", s1);
    }
}
