package org.deals.framework.test;

import org.apache.log4j.Logger;
import org.deals.framework.bean.HibernateSessionFactory;
import org.deals.framework.dao.DAOFactory;
import org.hibernate.Session;

import junit.framework.TestCase;

public abstract class DealsTestCase extends TestCase {
	
	protected Logger log = Logger.getLogger(getClass());
	private Session ses;
	private DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
	
	protected void setUp() throws Exception {
		super.setUp();
		ses = HibernateSessionFactory.getSession();
		ses.beginTransaction();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		ses.getTransaction().commit();
		ses.close();		
	}
	
	protected Session getSession() {
		return ses;
	}

	protected DAOFactory getDAOFactory() {
		return factory;
	}
}
