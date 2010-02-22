package org.deals.framework.test;

import java.util.Iterator;
import java.util.List;

import org.deals.framework.bean.Category;
import org.deals.framework.bean.PageType;
import org.deals.framework.bean.Section;
import org.deals.framework.bean.WebPage;

public class SectionTest extends DealsTestCase {

	/**
	 * Recupera le categorie di una sezione. 
	 * Test1: section con categorie, i risultati devono essere solo categorie 
	 * Test2: section senza categorie, i risultati non devono essere categorie, non ci deve essere niente
	 */
	public void testGetCategories() {
		// TEST 1
		int idSectionWithCategories = Integer.parseInt(TestConfig
				.getString("DealsTest.ID_SECTION_CON_CATEGORIES")); //$NON-NLS-1$
		if (idSectionWithCategories > 0) {
			WebPage page = getDAOFactory().getSectionDAO().findById(
					idSectionWithCategories, false);
			assertNotNull(
					"SPECIFICARE UNA PAGINA ESISTENTE (" + idSectionWithCategories + " non la trovo)", page); //$NON-NLS-1$ //$NON-NLS-2$
			assertTrue(page.getPageType().equals(PageType.SECTION()));
			Section sec = (Section) page;
			List<Category> cats = sec.getCategories();
			assertTrue("SPECIFICARE UNA PAGINA CON CATEGORIE", cats.size() > 0);
			for (Iterator iter = cats.iterator(); iter.hasNext();) {
				WebPage element = (WebPage) iter.next();
				assertTrue("HA RECUPERATO UNA WEBPAGE NON CATEGORY", element
						.getPageType().equals(PageType.CATEGORY()));
			}
		} else {
			fail("SPECIFICARE L'ID DI UNA SECTION ESISTENTE CON CATEGORIES su deals_test.properties");
		}

		// TEST 2
		int idSectionWithoutCategories = Integer.parseInt(TestConfig
				.getString("DealsTest.ID_SECTION_SENZA_CATEGORIES")); //$NON-NLS-1$
		if (idSectionWithoutCategories > 0) {
			WebPage page = getDAOFactory().getSectionDAO().findById(
					idSectionWithoutCategories, false);
			assertNotNull(
					"SPECIFICARE UNA PAGINA ESISTENTE (" + idSectionWithoutCategories + " non la trovo)", page); //$NON-NLS-1$ //$NON-NLS-2$
			assertTrue(page.getPageType().equals(PageType.SECTION()));
			Section sec = (Section) page;
			assertTrue(sec.getCategories().size()==0);
		} else {
			fail("SPECIFICARE L'ID DI UNA SECTION ESISTENTE SENZA CATEGORIES su deals_test.properties");
		}

	}
	
	
	
	/**
	 * Recupera le schede di una sezione. 
	 * Test1: section con schede, i risultati devono essere solo schede 
	 * Test2: section senza schede, i risultati non devono essere schede, non ci deve essere niente
	 */
	public void testGetItems() {
		// TEST 1
		int idSectionWithItems = Integer.parseInt(TestConfig
				.getString("DealsTest.ID_SECTION_CON_ITEMS")); //$NON-NLS-1$
		if (idSectionWithItems > 0) {
			WebPage page = getDAOFactory().getSectionDAO().findById(
					idSectionWithItems, false);
			assertNotNull(
					"SPECIFICARE UNA PAGINA ESISTENTE (" + idSectionWithItems + " non la trovo)", page); //$NON-NLS-1$ //$NON-NLS-2$
			assertTrue(page.getPageType().equals(PageType.SECTION()));
			Section sec = (Section) page;
			List<WebPage> items = sec.getItems();
			assertTrue("SPECIFICARE UNA PAGINA CON SCHEDE", items.size() > 0);
			for (Iterator iter = items.iterator(); iter.hasNext();) {
				WebPage element = (WebPage) iter.next();
				assertTrue("HA RECUPERATO UNA WEBPAGE NON ITEM", element
						.getPageType().equals(PageType.ITEM()));
			}
		} else {
			fail("SPECIFICARE L'ID DI UNA SECTION ESISTENTE CON ITEMS su deals_test.properties");
		}

		// TEST 2
		int idSectionWithoutItems = Integer.parseInt(TestConfig
				.getString("DealsTest.ID_SECTION_SENZA_ITEMS")); //$NON-NLS-1$
		if (idSectionWithoutItems > 0) {
			WebPage page = getDAOFactory().getSectionDAO().findById(
					idSectionWithoutItems, false);
			assertNotNull(
					"SPECIFICARE UNA PAGINA ESISTENTE (" + idSectionWithoutItems + " non la trovo)", page); //$NON-NLS-1$ //$NON-NLS-2$
			assertTrue(page.getPageType().equals(PageType.SECTION()));
			Section sec = (Section) page;
			assertTrue(sec.getItems().size()==0);
		} else {
			fail("SPECIFICARE L'ID DI UNA SECTION ESISTENTE SENZA ITEMS su deals_test.properties");
		}

	}	

}
