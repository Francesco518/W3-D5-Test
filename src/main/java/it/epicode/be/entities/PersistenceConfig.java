package it.epicode.be.entities;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceConfig {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Test W3-D5");

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
