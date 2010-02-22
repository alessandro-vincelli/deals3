package org.deals.framework.test;

import junit.framework.TestCase;

import org.deals.framework.bean.HibernateSessionFactory;
import org.deals.framework.bean.PageType;
import org.deals.framework.factory.PageTypeFactory;
import org.hibernate.Session;

public class PageTypeFactoryTest extends TestCase {
	
	private Session ses;
	
	protected void setUp() throws Exception {
		super.setUp();
		ses = HibernateSessionFactory.getSession();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		ses.close();
	}

	public void testCreateSectionPageType() {
		ses.beginTransaction();
		PageType pt = PageTypeFactory.createSectionPageType();
		assertNotNull(pt);
		assertEquals(pt.getPtId().intValue(), PageType.SECTION_ID);
		ses.getTransaction().commit();		
	}

	public void testCreateCategoryPageType() {
		ses.beginTransaction();
		PageType pt = PageTypeFactory.createCategoryPageType();
		assertNotNull(pt);
		assertEquals(pt.getPtId().intValue(), PageType.CATEGORY_ID);
		ses.getTransaction().commit();		
	}

	public void testCreateItemPageType() {
		ses.beginTransaction();
		PageType pt = PageTypeFactory.createItemPageType();
		assertNotNull(pt);
		assertEquals(pt.getPtId().intValue(), PageType.ITEM_ID);
		ses.getTransaction().commit();		
	}

}
