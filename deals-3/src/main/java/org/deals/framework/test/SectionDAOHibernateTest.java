package org.deals.framework.test;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.deals.framework.bean.HibernateSessionFactory;
import org.deals.framework.bean.PageType;
import org.deals.framework.bean.WebPage;
import org.deals.framework.dao.SectionDAOHibernate;
import org.hibernate.Session;

public class SectionDAOHibernateTest extends TestCase {

	protected Logger log = Logger.getLogger(getClass());
	private Session ses;
	private SectionDAOHibernate dao;
	
	public SectionDAOHibernateTest() {
		dao = new SectionDAOHibernate();
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

	public void testFindByIdIntegerBoolean() {
		WebPage page = dao.findById(1, false);
		assertNotNull(page);
		assertEquals(PageType.SECTION(), page.getPageType());
	}

	public void testFindAll() {
		List<WebPage> pages = dao.findAll();
		log.info("Trovate " + pages.size() + " sezioni");		
		for (Iterator iter = pages.iterator(); iter.hasNext();) {
			WebPage page = (WebPage) iter.next();
			assertNotNull(page);
			assertEquals(PageType.SECTION(), page.getPageType());			
		}
	}

}
