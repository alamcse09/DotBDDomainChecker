package com.bd;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class DataInterface<T> {
	
	@SuppressWarnings("unchecked")
	private String getClassName() {
		
		return ((Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getName();
	}
	
	public void add( T t ) {
		
		Session session = SessionFactoryBuilder.getInstance().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.save( t );
			tx.commit();
		}catch( Exception e ) {
			
			tx.rollback();
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public T getById( Class<?> c, long id ) {
		
		Session session = SessionFactoryBuilder.getInstance().openSession();
		Transaction tx = null;
		T object = null;
		
		try {
			tx = session.beginTransaction();
			object = (T) session.get( c , id );
			tx.commit();
		}catch( Exception e ) {
			
			tx.rollback();
			e.printStackTrace();
		}
		
		return object;
	}
	
	public void update( T t ) {
		
		Session session = SessionFactoryBuilder.getInstance().openSession();
		
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.update( t );
			tx.commit();
		}catch( Exception e ) {
			
			tx.rollback();
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public T getByColumn( String columnName, String value ) {
		
		Session session = SessionFactoryBuilder.getInstance().openSession();
		
		List<T> result = new ArrayList<T>();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery( "from " + getClassName() + " where " + columnName + " = " + value );
			result = query.list();
			tx.commit();
		}
		catch( Exception e ) {
			
			tx.rollback();
			e.printStackTrace();
		}
		
		return result.get(0);
	}
}
