package com.sapient.entity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Animal {

    // object of abstract class cannot be created
    // this class may have 0 or more abstract methods
    // this class may have 0 or more concrete (non-abstract) methods

    // An object of this class can only be realized via an object of a subclass
    // All subclasses must implement method body for all the inherited abstract
    // methods. Otherwise, the subclass must be marked as abstract.
    public void logAuthorDetails() {
        log.debug("Author name = Vinod, email = vinod@vinod.co");
    }

    // the only reason for this methods is to allow compiler to permit for a
    // reference of this class to invoke this method, which comes from the actual
    // object at the runtime (such as object of Dog or Cat)
    public abstract void whoAmI();

    // while overriding any method in the subclass, the access modifier must have
    // the same or better visibility.
    // private -> package level -> protected -> public
}
