package com.sapient.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

// Access modifiers:
// private, package-level/default (not-mentioned), protected, public
@Data
@Slf4j
public class Person {
    private String name;
    private String email;

    public void log() {
        log.debug("Name = {}, Email = {}", name, email);
    }
}
