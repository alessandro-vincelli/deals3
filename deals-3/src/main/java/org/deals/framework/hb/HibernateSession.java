/*
 * Created on Mar 19, 2007
 *
 */
package org.deals.framework.hb;

import javax.servlet.ServletRequest;

import org.apache.log4j.Logger;
import org.deals.framework.bean.HibernateSessionFactory;
import org.hibernate.Session;

/**
 * @author Alessandro Vincelli
 * 
 * Apre e restituisce la sessione hibernate
 * 
 */

public abstract class HibernateSession {
	Logger log = Logger.getLogger(getClass());

	//private ServiceManager serviceManager;
	private Session session = null;

/*	protected void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}*/

	public Session openHibernateSession(ServletRequest request) throws Exception {

		if (session != null) {
			return session;
		}

		//HibernateFactory hf;
		
			log.debug(" Creo la session Hibernate: ");
			/* Implementazione agganciata a cocoon connecton pool
			hf = (HibernateFactory) serviceManager.lookup(PersistenceFactory.ROLE);
			session = hf.createSession();
			serviceManager.release(hf);*/
			
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			
			log.debug("Ho creato la sessione Hibernate: ");
		 

		if (session == null) {
			throw new Exception("La Sessione e' null");
		}
		request.setAttribute("DisposeHibernateSession", session);
		HibernateSessionFactory.setSession(session);
		return session;
	}
	
	protected Session getHibernateSession() throws Exception {
		if (session == null) {
			throw new Exception("La Sessione e' null, hai chiamato openHibernateSession?");
		} 
		return session;
	}

	
	{
		/*
		 * // Make sure Hibernate Sessions are not opened twice if(hs &&
		 * hs.isOpen()) return; // Get new Session from PersistenceFactory var
		 * factory =
		 * cocoon.getComponent(Packages.org.test.PersistenceFactory.ROLE); hs =
		 * factory.createSession(); if (hs == null) { throw new
		 * Packages.org.apache.cocoon.ProcessingException("Hibernate session is
		 * null "); } // Release PersistenceFactory
		 * cocoon.releaseComponent(factory); // Send "Message" to
		 * HibernateFilter to dispose the session after the view was rendered
		 * cocoon.request.setAttribute("DisposeHibernateSession",hs)
		 */
	}

}
