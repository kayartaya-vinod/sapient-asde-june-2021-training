package com.sapient.entity;

import lombok.Data;

@Data
public class Triangle implements Shape {
    // By implementing an interface, it is equivalent to extending and abstract
    // class with only abstract methods. Because of this, the Triangle class already
    // has an abstrct function (getArea from Shape)
    private double base;
    private double height;

    public Triangle() {
    }

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double getArea() {
        return 0.5 * base * height;
    }

}
