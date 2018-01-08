package com.navix.core.util.spring;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * spring hibernateSessionFactory
 * 
 * 
 */
public class HibernateSessionFactory {
	private static final SessionFactory sessionf = (SessionFactory) BeanFactory
			.getBean("sessionFactory");

	public static void closeSession(Session session) {
		session.close();
	}

	public static Session getSession() {
		return sessionf.openSession();
	}

	public static SessionFactory getFactory() {
		return sessionf;
	}
}
