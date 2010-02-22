package org.deals.framework.test;

import java.util.Iterator;
import java.util.List;

import org.deals.framework.bean.Category;
import org.deals.framework.bean.PageType;
import org.deals.framework.bean.WebPage;


public class CategoryTest extends DealsTestCase {

	/**
	 * Recupera le sections di una categoria. 
	 * Test1: category con sezioni, i risultati devono essere solo categorie 
	 * Test2: category senza sezioni: categorie senza sezioni non ce ne sono!!!
	 */
	public void testGetSections() {
		// TEST 1
		int idCategoryWithSections = Integer.parseInt(TestConfig
				.getString("DealsTest.ID_CATEGORY_CON_SECTIONS")); //$NON-NLS-1$
		if (idCategoryWithSections > 0) {
			WebPage page = getDAOFactory().getCategoryDAO().findById(
					idCategoryWithSections, false);
			assertNotNull(
					"SPECIFICARE UNA PAGINA ESISTENTE (" + idCategoryWithSections + " non la trovo)", page); //$NON-NLS-1$ //$NON-NLS-2$
			assertTrue(page.getPageType().equals(PageType.CATEGORY()));
			Category cat = (Category) page;
			List<WebPage> sex = cat.getSections();
			assertTrue("SPECIFICARE UNA PAGINA CON SEZIONI", sex.size() > 0);
			for (Iterator iter = sex.iterator(); iter.hasNext();) {
				WebPage element = (WebPage) iter.next();
				assertTrue("HA RECUPERATO UNA WEBPAGE NON SECTION", element
						.getPageType().equals(PageType.SECTION()));
			}
		} else {
			fail("SPECIFICARE L'ID DI UNA CATEGORY ESISTENTE CON SECTIONS su deals_test.properties");
		}
	}
	
	
	
	/**
	 * Recupera le schede di una categoria. 
	 * Test1: categoria con schede, i risultati devono essere solo schede 
	 * Test2: categoria senza schede, i risultati non devono essere schede, non ci deve essere niente
	 */
	public void testGetItems() {
		// TEST 1
		int idCategoryWithItems = Integer.parseInt(TestConfig
				.getString("DealsTest.ID_CATEGORY_CON_ITEMS")); //$NON-NLS-1$
		if (idCategoryWithItems > 0) {
			WebPage page = getDAOFactory().getCategoryDAO().findById(
					idCategoryWithItems, false);
			assertNotNull(
					"SPECIFICARE UNA PAGINA ESISTENTE (" + idCategoryWithItems + " non la trovo)", page); //$NON-NLS-1$ //$NON-NLS-2$
			assertTrue(page.getPageType().equals(PageType.CATEGORY()));
			Category cat = (Category) page;
			List<WebPage> items = cat.getItems();
			assertTrue("SPECIFICARE UNA PAGINA CON SCHEDE", items.size() > 0);
			for (Iterator iter = items.iterator(); iter.hasNext();) {
				WebPage element = (WebPage) iter.next();
				assertTrue("HA RECUPERATO UNA WEBPAGE NON ITEM", element
						.getPageType().equals(PageType.ITEM()));
			}
		} else {
			fail("SPECIFICARE L'ID DI UNA CATEGORY ESISTENTE CON ITEMS su deals_test.properties");
		}

		// TEST 2
		int idCategoryWithoutItems = Integer.parseInt(TestConfig
				.getString("DealsTest.ID_CATEGORY_SENZA_ITEMS")); //$NON-NLS-1$
		if (idCategoryWithoutItems > 0) {
			WebPage page = getDAOFactory().getCategoryDAO().findById(
					idCategoryWithoutItems, false);
			assertNotNull(
					"SPECIFICARE UNA PAGINA ESISTENTE (" + idCategoryWithoutItems + " non la trovo)", page); //$NON-NLS-1$ //$NON-NLS-2$
			assertTrue(page.getPageType().equals(PageType.CATEGORY()));
			Category cat = (Category) page;
			assertTrue(cat.getItems().size()==0);
		} else {
			fail("SPECIFICARE L'ID DI UNA CATEGORY ESISTENTE SENZA ITEMS su deals_test.properties");
		}

	}	

}
