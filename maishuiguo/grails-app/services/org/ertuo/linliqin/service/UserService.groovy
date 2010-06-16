package org.ertuo.linliqin.service;


import javax.persistence.EntityManager
import org.apache.log4j.Logger;
import org.ertuo.linliqin.dao.persistence.EMF;
import org.ertuo.linliqin.domain.Flight;

public class UserService {

	private final Logger log=Logger.getLogger(UserService.class)
    //private EntityManager entityManager = EMF.get().createEntityManager();

    Flight getUser(long login) {
		log.info("fuck "+login)
		log.error("error "+login)
		//entityManager.persist(new Flight())
    }
}
