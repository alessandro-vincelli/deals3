package org.deals.framework.test;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.deals.framework.bean.ComponentType;
import org.deals.framework.bean.HibernateSessionFactory;
import org.deals.framework.bean.WebComponent;
import org.deals.framework.bean.WebPage;
import org.deals.framework.dao.DAOFactory;
import org.deals.framework.dao.WebPageDAO;
import org.hibernate.Session;

public class WebPageComponentsTest extends TestCase {

	protected Logger log = Logger.getLogger(getClass());
	private Session ses;
	private DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
	private WebPageDAO dao = factory.getWebPageDAO();
	
	
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

	public void testGetComponents() {
		WebPage page = dao.findById(48, false);
		List<WebComponent> cps = page.getComponents();
		for (Iterator iter = cps.iterator(); iter.hasNext();) {
			iter.next();
		}
	}

	public void testGetComponentsComponentType() {
		WebPage page = dao.findById(48, false);
		List<WebComponent> cps = page.getComponents(ComponentType.RESOURCE_LINK());
		if (cps.size()>0) {
		   assertEquals(ComponentType.RESOURCE_LINK(),cps.get(0).getComponentType());	
		}
	}

	public void testGetComponentsComponentTypeStringBoolean() {
		WebPage page = dao.findById(48, false);
		List<WebComponent> cps = page.getComponents(ComponentType.RESOURCE_LINK(),"rlHref", false);
		log.info("Test recupero componenti. " +
				"\nRequisito: pagina con id=48 con resource_links." +
				"\nOrdinamento per rlHref discendente");
		assertTrue(cps.size()>0);
		for (Iterator iter = cps.iterator(); iter.hasNext();) {
			WebComponent element = (WebComponent) iter.next();
			log.info(" - " + element.getRlHref());
		}
		if (cps.size()>0) {
		   assertEquals(ComponentType.RESOURCE_LINK(),cps.get(0).getComponentType());	
		}
	}

}
