/*
 * Created on Mar 6, 2007
 *
 */
package org.deals.framework.hb;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.deals.framework.bean.HibernateSessionFactory;
import org.deals.framework.bean.PersistentObject;
import org.deals.framework.core.IKeys;
import org.hibernate.LockMode;
import org.hibernate.Session;

/**
 * 
 * @author Alessandro Vincelli
 * 
 * Filtra tutte le request Http, si occupa di chiudere la sessione Hibernate.
 * L'utilizzo è configurato nel web.xml della webapp
 * 
 */
public class HibernateFilter extends HibernateSession implements Filter {
	private Logger log = Logger.getLogger(getClass());

	private FilterConfig config = null;

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		Session session = HibernateSessionFactory.getSession();
		
		log.debug("_____ HIBERNATE FILTRO: APRO la sessione");
		try 
		{
			// openHibernateSession(request);
			session.beginTransaction();
			session.clear();
		} catch (Exception e1) {
			log.debug("_____ HIBERNATE FILTRO: errore su apertura sessione", e1);
		}
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		// httSession.removeAttribute("user_site");

		HashMap<String, Object> m = (HashMap<String, Object>) httpServletRequest.getSession().getAttribute(IKeys.MODELOBJECTS_KEY);

		if (m != null) {
			for (Object object : m.values()) {
				if (object instanceof PersistentObject) {
					PersistentObject persistentObject = (PersistentObject) object;
					// dao.reatachSObject(persistentObject);
					// hsession.lock(user, LockMode.UPGRADE);
					
					log.debug("_____ HIBERNATE FILTRO: riattacco oggetto: " + persistentObject  );
					//hsession.lock(object, LockMode.UPGRADE_NOWAIT);
					session.refresh(object, LockMode.UPGRADE);
				}
			}
			for (Object listsObject : m.values()) {
				log.debug("_____ HIBERNATE FILTRO: trovata di oggetti di tipo: "  + listsObject.getClass() );
				if (listsObject instanceof List) {
					log.debug("_____ HIBERNATE FILTRO: trovata lista di oggetti: "   );
					List objects = (List)listsObject;
					Iterator it = objects.iterator();
					while (it.hasNext()){
						Object object = it.next();
						if (object instanceof PersistentObject) { 							
							log.debug("_____ HIBERNATE FILTRO: riattacco oggetto: " + object  );
							//hsession.lock(object, LockMode.UPGRADE_NOWAIT);
							session.refresh(object, LockMode.UPGRADE);							
						}
					}
					
				}
			}
		}

/*		log.error("_____ HIBERNATE FILTRO: utente da ripristinare" + httSession.getAttribute("currentUser"));
		if (httSession.getAttribute("currentUser") != null) {
			org.hibernate.Session hsession = HibernateSessionFactory.getSession();
			Object user = httSession.getAttribute("currentUser");
			hsession.lock(user, LockMode.UPGRADE);
			httSession.setAttribute("currentUser", user);
			log.error("_____ HIBERNATE FILTRO: utente ripristinato");
		}*/
		// Passa la richiesta a cocoon
		chain.doFilter(request, response);

		// Dopo che che cocoon ha finito, chiudo
		// la corisspondente Hibernate session se questa è stata aperta

		log.debug("_______HIBERNATE FILTRO: CHIUDO la sessione");
		session.getTransaction().commit();
		session.flush();
		HibernateSessionFactory.closeSession();

		log.debug("_____ HIBERNATE FILTRO: Chiudo il filtro");
	}
}
