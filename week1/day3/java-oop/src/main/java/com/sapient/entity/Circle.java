package com.sapient.entity;

import lombok.Data;

@Data
public class Circle {
    private double radius;

    public void setRadius(double radius) throws InvalidRadiusException {
        if (radius < 0) {
            throw new InvalidRadiusException(String.format("Invalid value for radius: %f", radius));
        }
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * this.radius * this.radius;
    }
}
