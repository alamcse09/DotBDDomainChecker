package com.bd;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactoryBuilder {

	private static SessionFactory sessionFactory = null;

	private synchronized static void createInstance() {

		if (sessionFactory == null) {
			
			org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
			
			configuration.configure("hibernate.cfg.xml");
			configuration.addAnnotatedClass( Subscriber.class );
			
			StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties());
			sessionFactory = configuration.buildSessionFactory(ssrb.build());
			//sessionFactory.setPackagesToScan(new String[] { "com.bd" });
		}

	}

	public synchronized static SessionFactory getInstance() {

		if (sessionFactory == null) {
			
			createInstance();
		}
		
		return sessionFactory;
	}
	
	private SessionFactoryBuilder() {};
}
