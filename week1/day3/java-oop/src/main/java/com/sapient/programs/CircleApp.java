package com.sapient.programs;

import com.sapient.entity.Circle;
import com.sapient.entity.InvalidRadiusException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CircleApp {

    public static void main(String[] args) throws InvalidRadiusException {
        log.debug("Start of CircleApp.main()");

        Circle c1;
        c1 = new Circle();
        c1.setRadius(4.5);
        double area = c1.getArea();
        log.debug("Area of circle with radius {} is {}", c1.getRadius(), area);
        log.debug("End of CircleApp.main()");
    }
}
