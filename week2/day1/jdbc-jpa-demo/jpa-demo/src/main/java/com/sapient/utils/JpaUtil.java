package com.sapient.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JpaUtil {
    private JpaUtil() {
    }

    public static EntityManager getEntityManager() {
        // this will look for META-INF/persistence.xml and searches for a
        // <persistence-unit> with name=TRAINING
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TRAINING");
        return factory.createEntityManager();
    }
}
