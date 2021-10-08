package com.sapient;

public class FactorialGenerator {

    public long getFactorial(int num) {

        if (num < 0) {
            throw new IllegalArgumentException("factorial of negative number does not exist");
        }

        long f = 1;
        while (num > 0) {
            f *= num--;
        }
        return f;
    }

}
