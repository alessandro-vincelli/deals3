package org.deals.framework.test;

import java.util.List;

import junit.framework.TestCase;

import org.deals.framework.bean.HibernateSessionFactory;
import org.deals.framework.bean.WebPage;
import org.deals.framework.dao.CategoryDAO;
import org.deals.framework.dao.DAOFactory;
import org.deals.framework.dao.ItemDAO;
import org.deals.framework.dao.SectionDAO;
import org.deals.framework.dao.WebPageDAO;
import org.hibernate.Session;

public class WebPageDAOTest extends TestCase {

	private DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
	private Session ses;
	
	public static final int SECTION_PAGE_ID = 1;
	public static final int CATEGORY_PAGE_ID = 64;
	public static final int ITEM_PAGE_ID = 41;	
	
	protected void setUp() throws Exception {
		super.setUp();
		ses = HibernateSessionFactory.getSession();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		ses.close();
	}

	public void testFindById() {
		ses.beginTransaction();
		WebPageDAO dao = factory.getWebPageDAO();
		WebPage page = dao.findById(ITEM_PAGE_ID, false);
		assertNotNull(page);
		ses.getTransaction().commit();		
	}

	public void testFindAll() {
		ses.beginTransaction();
		WebPageDAO dao = factory.getWebPageDAO();		
		List<WebPage> pages = dao.findAll();
		assertTrue(pages.size()>0);
		ses.getTransaction().commit();		
	}
	
	public void testSavePage() {		
		String oldName = "";
		String newName = "La storiellabla bla bla";
		
		ses.beginTransaction();
		WebPageDAO dao = factory.getWebPageDAO();		
		WebPage page = dao.findById(ITEM_PAGE_ID, false);
		oldName = page.getWpName();
		page.setWpName(newName);
		ses.getTransaction().commit();	
		
		ses.beginTransaction();
		page = dao.findById(ITEM_PAGE_ID, false);
		assertEquals(newName, page.getWpName());
		
		page.setWpName(oldName);
		ses.getTransaction().commit();	
	}
	
	
	public void testRetrieveSection() {
		ses.beginTransaction();
		SectionDAO dao = (SectionDAO)factory.getSectionDAO();
		WebPage page = dao.findById(SECTION_PAGE_ID, false);
		assertNotNull(page);
				
		page = dao.findById(ITEM_PAGE_ID, false);
		assertNull(page);
		
		ses.getTransaction().commit();
	}
	
	public void testRetrieveCategory() {
		ses.beginTransaction();
		CategoryDAO dao = (CategoryDAO)factory.getCategoryDAO();
		WebPage page = dao.findById(CATEGORY_PAGE_ID, false);
		assertNotNull(page);
		
		page = dao.findById(ITEM_PAGE_ID, false);
		assertNull(page);
		
		ses.getTransaction().commit();
	}
	
	public void testRetrieveItem() {
		ses.beginTransaction();
		ItemDAO dao = (ItemDAO)factory.getItemDAO();
		WebPage page = dao.findById(ITEM_PAGE_ID, false);
		assertNotNull(page);
		
		page = dao.findById(SECTION_PAGE_ID, false);
		assertNull(page);
		
		ses.getTransaction().commit();
	}
	
	
	// TODO sistemare questo test
	public void testAddPageDeletePage() {		
//		int newId = 0;		
//		ses.beginTransaction();
//		ItemDAO dao = (ItemDAO)factory.getWebPageDAO(PageType.ITEM());
//		WebPage page = WebPageFactory.createItem("Scheda di prova");
//		dao.makePersistent(page);
//		newId = page.getWpId();
//		ses.getTransaction().commit();
//		
//		ses.beginTransaction();
//		page = dao.findById(newId, false);
//		assertNotNull(page);
//		dao.makeTransient(page);
//		ses.getTransaction().commit();
	}
	
	
	

}
