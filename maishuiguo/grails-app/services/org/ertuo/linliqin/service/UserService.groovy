package org.ertuo.linliqin.service;


import javax.persistence.EntityManagerFactory;
import java.util.logging.Logger;
import org.ertuo.linliqin.domain.Flight;
import org.ertuo.linliqin.domain.User;

public class UserService {

	private final Logger log=Logger.getLogger(UserService.class.getName())
	//EMF emf
	EntityManagerFactory entityManagerFactory

    Flight getUser(long login) {
	    entityManagerFactory.createEntityManager().persist(new Flight())
    }

	void save(User user){
	    entityManagerFactory.createEntityManager().persist(user)
	}

	User findByLogin(String login){
	    entityManagerFactory.createEntityManager().createQuery ("select * from User x where x.login="+login).getResultList().get(0);
	}

    
	
}
