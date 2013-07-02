package com.machine.manager;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;


import java.sql.*;

public class Manager
{
	protected Session session;
	
	public void createSession()
	{
		if(this.session != null)
		{
			if(this.session.isOpen())
			{
				this.session.close();
				this.session = null;
			}
		}
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		this.session = sf.openSession();
	}
	
	public Connection closeSession()
	{
		Connection conn = this.session.close();
		this.session = null;
		return conn;
	}
	
	public Transaction startTransaction()
	{
		return session.beginTransaction();
	}

	// public boolean debug(String message) {
	// 	if (true) {
	// 		System.out.println(message);
	// 	}
	// }
	
	//These need to be refactored to a hibernate Utils class.......Now that I think about it
	//TODO: Refactor into Util class!
	
	public static SessionFactory factory = new Configuration().configure().buildSessionFactory();
	
	public static final Session getCurrentSession()
	{
		return factory.getCurrentSession();
	}
	public static final Session openSession()
	{
		return factory.openSession();
	}
	
	public static final void save(Object object, Session sess)
	{
		Transaction tr = sess.beginTransaction();
		sess.save(object);
		tr.commit();
	}

	public static void update(Object object, Session sess)
	{
		Transaction tr = sess.beginTransaction();
		sess.update(object);
		sess.flush();
		tr.commit();
	}
}
