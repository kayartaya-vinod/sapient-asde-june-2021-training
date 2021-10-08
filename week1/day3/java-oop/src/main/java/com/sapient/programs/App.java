package com.sapient.programs;

import lombok.extern.slf4j.Slf4j;

/**
 * Hello world!
 *
 */
@Slf4j
public class App {
    public static void main(String[] args) {
        // System.out.println("Hello World!")
        String name = "Vinod";
        String message = "Hello, World!";
        final String pattern = "{} from {}";
        log.error(pattern, message, name);
        log.warn(pattern, message, name);
        log.info(pattern, message, name);
        log.debug(pattern, message, name);
        log.trace(pattern, message, name);
    }
}
