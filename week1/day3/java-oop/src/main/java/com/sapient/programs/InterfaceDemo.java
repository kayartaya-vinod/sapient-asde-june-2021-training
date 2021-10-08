package com.sapient.programs;

import com.sapient.entity.Rectangle;
import com.sapient.entity.Shape;
import com.sapient.entity.Triangle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InterfaceDemo {
    public static void main(String[] args) {
        Shape s1;
        double area;

        s1 = new Triangle(12, 34);
        area = s1.getArea();
        log.debug("Area of triangle = {}", area);

        s1 = new Rectangle(12, 34);
        area = s1.getArea();
        log.debug("Area of rectangle = {}", area);

        // line 16 and 20 are identical for the compiler
        // but produce different result based on what kind of object, s1 refers to.

    }
}
