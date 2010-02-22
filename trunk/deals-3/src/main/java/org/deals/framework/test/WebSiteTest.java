package org.deals.framework.test;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.deals.framework.bean.HibernateSessionFactory;
import org.deals.framework.bean.Item;
import org.deals.framework.bean.WebPage;
import org.deals.framework.core.WebSite;
import org.deals.framework.util.XmlUtils;
import org.hibernate.Session;

public class WebSiteTest extends TestCase {

	protected Logger log = Logger.getLogger(getClass());
	private Session ses;
	private WebSite site = new WebSite();
	
	protected void setUp() throws Exception {
		super.setUp();
		ses = HibernateSessionFactory.getSession();
		ses.beginTransaction();		
		site.disableFilter();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		ses.getTransaction().commit();
		ses.close();
		site.enableFilter();
	}

	public void testGetPageString() {
		WebPage page = site.getPage("http://www.deals.org/site/section/286.html");
		assertNotNull(page);
		assertTrue(page.getWpId()==286);
	}

	public void testGetPageInt() {
		WebPage page = site.getPage(1);
		assertNotNull(page);
		assertTrue(page.getWpId()==1);
	}

	public void testGetSection() {
		WebPage page = site.getSection(1);
		assertNotNull(page);
		assertTrue(page.getWpId()==1);
		
		page = site.getSection(64);
		assertNull(page);
	}

	public void testGetCategory() {
		WebPage page = site.getCategory(64);
		assertNotNull(page);
		assertTrue(page.getWpId()==64);
		
		page = site.getCategory(1);
		assertNull(page);
	}

	public void testGetItem() {
		WebPage page = site.getItem(41);
		assertNotNull(page);
		assertTrue(page.getWpId()==41);		
		page = site.getItem(1);
		assertNull(page);
	}


	public void testSavePage() {		
		Item page = (Item)site.getItem(41);
		String oldName = page.getWpName();
		page.setWpName("Bla bla bla");
		ses.getTransaction().commit();
		ses.close();
		
		ses = HibernateSessionFactory.getSession();
		ses.beginTransaction();
		page = (Item)site.getItem(41);
		assertEquals("Bla bla bla", page.getWpName());
		page.setWpName(oldName);
	}
	
	
	public void testGetPage() {
		WebPage page = site.getPage("www.google.it/section/1.html");
		String xml = page.toXML();
		try {
		  xml = XmlUtils.prettyPrint(xml);
  		  log.info(xml);
  		  assertTrue("", true);
		} catch (Exception e) {
		  fail("XML generato non valido!!!");	
		}
	}
	
	
	
	
	
	
	

}
