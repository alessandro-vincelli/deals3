/*
 * Created on Apr 3, 2007
 *
 */
package org.deals.framework.hb;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.cocoon.environment.Session;
import org.apache.log4j.Logger;
import org.deals.framework.core.IKeys;

public class HibernateUtiliObjectInHttpSession {
	
	private Logger log = Logger.getLogger(getClass());
	
	private HttpSession session;
	
	public void putObject(String keyDesc, Object obj) {
		
		log.debug("______Put Object in sessione INIZIO HTTP: " + keyDesc + ": " + obj);
		HashMap<String, Object> m = (HashMap<String, Object>) session.getAttribute(IKeys.MODELOBJECTS_KEY);
		log.debug("______Put Object in sessione INIZIO 2 HTTP: " + keyDesc + ": " + obj);
		if (m != null) {
			if (m.put(keyDesc, obj) != null) {
				log.warn("______Attenzione oggetto già presente in sessione HTTP: " + keyDesc + ": " + obj);
			}
			log.debug("______ HashMap presente: oggetto in sessione : " + keyDesc + ": " + m.get(keyDesc));
		} else {
			log.debug("______ Creazione HashMap ");
			HashMap<String, Object> m2 = new HashMap<String, Object>();
			log.debug("______ HashMap Creata ");
			log.debug("______ HashMap put di : " +  keyDesc + ": " + obj);
			// SEMETTO così situazione non riconosciuta o da java o da tomcat
			//m2.put(keyDesc, obj);
			m2.put(keyDesc, obj);
			log.debug("______ Oggetto su  HashMap ");
			session.setAttribute(IKeys.MODELOBJECTS_KEY, m2);
			log.debug("______Oggetto inserito in sessione HTTP: " + keyDesc + ": " + obj);
		}
	}
	
	public void removeObject(String keyDesc) {
		HashMap<String, Object> m = (HashMap<String, Object>) session.getAttribute(IKeys.MODELOBJECTS_KEY);

		if (m != null) {
			if (m.get(keyDesc) != null) {
				log.debug("______Oggetto  presente in sessione HTTP e correttamente rimosso: " + keyDesc + m.get(keyDesc));
				m.remove(keyDesc);
			} else {
				log.error("______Oggetto non presente in sessione HTTP: " + keyDesc + m.get(keyDesc));
			}
		} else {
			log.error("______HashMap non settata in sessione: " + keyDesc + m.get(keyDesc));
		}
	}

	public Object getObject(String keyDesc) throws Exception  {
		HashMap<String, Object> m = (HashMap<String, Object>) session.getAttribute(IKeys.MODELOBJECTS_KEY);

		if (m != null) {
			if (m.get(keyDesc) != null) {
				log.debug("______Oggetto presente in sessione HTTP e restituito correttamene: " + keyDesc + m.get(keyDesc));
				return (m.get(keyDesc));
			} else {
				log.error("______Oggetto non presente in sessione HTTP: " + keyDesc + m.get(keyDesc));
				return null;
			}
		} else {
			log.error("______HashMap non settata in sessione: " + keyDesc + m.get(keyDesc));
			throw new Exception("HaspMap MODELOBJECTS_KEY non presente in sessione");
		}
	}
	
	public HibernateUtiliObjectInHttpSession(HttpSession session){
		this.session = session;
	}

}
