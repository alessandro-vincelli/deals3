package org.deals.framework.test;

import org.deals.framework.bean.PageType;
import org.deals.framework.dao.PageTypeDAO;
import org.hibernate.Session;

public class TestPageTypeDAOHibernate extends DealsTestCase {

	
	private PageTypeDAO dao;
	private Session ses;
	
	public TestPageTypeDAOHibernate() {
		super();
		dao = super.getDAOFactory().getPageTypeDAO();
	}


	public void setUp() throws Exception {
		super.setUp();
	}


	public void tearDown() throws Exception {
		super.tearDown();
	}


	public void testFindByIdIDBoolean() {
		PageType pt = dao.findById(1, false);
		assertNotNull(pt);
		assertEquals(PageType.SECTION_ID, pt.getPtId().intValue());
	}

}
