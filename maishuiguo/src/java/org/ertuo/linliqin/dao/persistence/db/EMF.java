package org.ertuo.linliqin.dao.persistence.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.ertuo.linliqin.domain.Flight;
import org.ertuo.linliqin.domain.User;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

public class EMF {

	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("grails_transactions-optional");

	public EMF() {
	}

	public static EntityManagerFactory get() {

		return emfInstance;
	}

	org.springframework.transaction.support.TransactionTemplate transactionTemplate;
	// EMF emf
	EntityManagerFactory entityManagerFactory;

	EntityManager entityManager;

	Flight getUser(long login) {
		entityManagerFactory.createEntityManager().persist(
				new Flight(null, null));
		return null;
	}

	void save(final User user) {
		/*
		 * entityManager.getProperties().each{ println("emf "+it) }
		 */

		transactionTemplate.execute(new TransactionCallback() {

			public Object doInTransaction(TransactionStatus status) {
				entityManagerFactory.createEntityManager().persist(user);
				return null;
			}

		});

	}
}