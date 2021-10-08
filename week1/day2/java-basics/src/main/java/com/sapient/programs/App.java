package com.sapient.programs;

import java.util.logging.Logger;

public final class App {

    static Logger log = Logger.getAnonymousLogger();

    public App() {
    }

    public long factorial(int num) {
        long f = 1;
        while (num > 1) {
            f *= num--;
        }
        return f;
    }

    public static void main(String[] args) {
        // System.out.println("Hello World!")
        log.info("Hello, world!");
    }
}
