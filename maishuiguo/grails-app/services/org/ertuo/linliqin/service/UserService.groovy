package org.ertuo.linliqin.service;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.logging.Logger;
import org.ertuo.linliqin.domain.Flight;
import org.ertuo.linliqin.domain.User;


public class UserService {

	boolean transactional = true

	private static final Logger logger = Logger.getLogger(UserService.class.getName());
	
	EntityManagerFactory entityManagerFactory
	

    Flight getUser(long login) {
	    entityManagerFactory.createEntityManager().persist(new Flight())
    }

	void save(final User user){
		/*entityManager.getProperties().each{
			println("emf "+it)
		}*/
		logger.info("begin persist user");
		EntityManager em=entityManagerFactory.createEntityManager()
        try {
        	em.persist(user)
		} finally{
			em.close()
		}
		
		
		//entityManager.persist(user)
		user.getProperties().each{
			println("user. "+it)
		}
		
	}

	List<User> findByLogin(){
	  entityManagerFactory.createEntityManager().createQuery (" select x from User x ").getResultList();
	  //entityManager.createQuery (" select x from User x ").getResultList();
	  //EMF.get().createEntityManager().createQuery (" select x from User x ").getResultList();
	}

    
	
}
