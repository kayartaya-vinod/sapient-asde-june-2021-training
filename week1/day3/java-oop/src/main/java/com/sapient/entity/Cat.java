package com.sapient.entity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Cat extends Animal { // Cat IS-A Animal

    String info = "I' am a nice cat!";

    public void setInfo(String info){
        this.info = info;
    }

    public void whoAmI() {
        log.debug(this.info);
    }
}
