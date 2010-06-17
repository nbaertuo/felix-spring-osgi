package org.ertuo.linliqin.dao.persistence.db;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {
    private static final EntityManagerFactory emfInstance = Persistence
                                                              .createEntityManagerFactory("transactions-optional");

    public EMF() {
    }

    public static EntityManagerFactory get() {
        return emfInstance;
    }
}