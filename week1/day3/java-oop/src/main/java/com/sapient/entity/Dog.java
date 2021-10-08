package com.sapient.entity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Dog extends Animal {
    public void whoAmI() {
        log.debug("I am a Dog");
    }
}
