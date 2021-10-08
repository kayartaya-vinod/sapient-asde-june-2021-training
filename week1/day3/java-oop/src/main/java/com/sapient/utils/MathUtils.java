package com.sapient.utils;

import lombok.Data;

@Data
public class MathUtils {

    private double num;

    public double square() {
        return num * num;
    }
}
