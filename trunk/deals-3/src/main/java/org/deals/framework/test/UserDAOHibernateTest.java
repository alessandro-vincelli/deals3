package org.deals.framework.test;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.deals.framework.bean.HibernateSessionFactory;
import org.deals.framework.bean.Users;
import org.deals.framework.dao.UserDAOHibernate;
import org.hibernate.Session;

public class UserDAOHibernateTest extends TestCase {

	protected Logger log = Logger.getLogger(getClass());
	private Session ses;
	private UserDAOHibernate dao;
	

	public UserDAOHibernateTest() {
		dao = new UserDAOHibernate();
	}
	
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

	public void testGetUser() {
		Users user = dao.getUser("alex", "alex");
		log.info(user.getUsEmail());
		assertNotNull(user);
	}

}
