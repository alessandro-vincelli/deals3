package org.deals.framework.test;

import junit.framework.TestCase;

import org.deals.framework.bean.HibernateSessionFactory;
import org.deals.framework.bean.PageType;
import org.deals.framework.core.WebCMS;
import org.hibernate.Session;

public class WebCMSTest extends TestCase {

	WebCMS  cms = new WebCMS();
	private Session ses;
	
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
	
	
	public void testGetMaxWeightSection() {
		assertNotNull(cms.getMaxWeightSection());
		assertTrue(cms.getMaxWeightSection() > 0);
	}

	public void testGetPageType() {
		PageType pt = cms.getPageType(1);
		assertEquals(PageType.SECTION_ID, pt.getPtId().intValue());	
	}
	

}
