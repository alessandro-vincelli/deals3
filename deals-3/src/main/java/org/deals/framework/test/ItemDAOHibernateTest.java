package org.deals.framework.test;

import java.util.Iterator;
import java.util.List;

import org.deals.framework.bean.PageType;
import org.deals.framework.bean.WebPage;
import org.deals.framework.dao.WebPageDAO;

public class ItemDAOHibernateTest extends DealsTestCase {

	
	private WebPageDAO dao;
	
	public ItemDAOHibernateTest() {
		dao = getDAOFactory().getItemDAO();
	}
		
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testFindByIdIntegerBoolean() {
		WebPage page = dao.findById(41, false);
		assertNotNull(page);
		assertEquals(PageType.ITEM(), page.getPageType());
	}

	public void testFindAll() {
		List<WebPage> pages = dao.findAll();
		log.info("Trovate " + pages.size() + " schede");		
		for (Iterator iter = pages.iterator(); iter.hasNext();) {
			WebPage page = (WebPage) iter.next();
			assertNotNull(page);
			assertEquals(PageType.ITEM(), page.getPageType());			
		}
	}

}
