package org.ertuo.linliqin.service;


import javax.persistence.EntityManager
import org.ertuo.linliqin.domain.User
import org.ertuo.linliqin.dao.persistence.EMF;

public class UserService {

    private EntityManager entityManager = EMF.get().createEntityManager();

    User getUser(long login) {
        entityManager.find(User.class, login);
    }
}
