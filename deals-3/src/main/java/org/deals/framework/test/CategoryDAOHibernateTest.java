package org.deals.framework.test;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.deals.framework.bean.PageType;
import org.deals.framework.bean.WebPage;
import org.deals.framework.dao.WebPageDAO;

public class CategoryDAOHibernateTest extends DealsTestCase {

	protected Logger log = Logger.getLogger(getClass());
	private WebPageDAO dao;
	
	public CategoryDAOHibernateTest() {
		dao = super.getDAOFactory().getCategoryDAO();
	}
		
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testFindByIdIntegerBoolean() {
		WebPage page = dao.findById(64, false);
		assertNotNull(page);
		assertEquals(PageType.CATEGORY(), page.getPageType());
	}

	public void testFindAll() {
		List<WebPage> pages = dao.findAll();
		log.info("Trovate " + pages.size() + " categorie");
		for (Iterator iter = pages.iterator(); iter.hasNext();) {
			WebPage page = (WebPage) iter.next();
			assertNotNull(page);
			assertEquals(PageType.CATEGORY(), page.getPageType());			
		}
	}

}
