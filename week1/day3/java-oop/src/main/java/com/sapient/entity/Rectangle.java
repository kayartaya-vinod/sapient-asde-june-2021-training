package com.sapient.entity;

import lombok.Data;

@Data
public class Rectangle implements Shape {
    private double width;
    private double breadth;

    public Rectangle() {
    }

    public Rectangle(double width, double breadth) {
        this.width = width;
        this.breadth = breadth;
    }

    @Override
    public double getArea() {
        return 2 * width * breadth;
    }

}
