package com.sapient.entity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Lion extends Animal {

    @Override
    public void whoAmI() {
        log.debug("I am a lion");
    }

}
