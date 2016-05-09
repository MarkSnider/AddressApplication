package com.service;

import org.hibernate.cfg.Configuration;

/**
 * This class was created because getting the sessionFactory takes a lot of time
 * so we dont want to have to create it over and over again
 * @author Mark Snider
 *
 */
public class HibernateUtil {
	
	private static org.hibernate.SessionFactory sessionFactory;			// SessionFactory will be used by the whole backend of the application

	/**
	 * Lets make sure that we only create this one time as needed and not over and over again. Its very slow to do that.
	 * @return the session factory.
	 */
	public static org.hibernate.SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		return sessionFactory;
	}

}
