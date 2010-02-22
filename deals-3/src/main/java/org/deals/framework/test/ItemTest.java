package org.deals.framework.test;

import java.util.Iterator;
import java.util.List;

import org.deals.framework.bean.Item;
import org.deals.framework.bean.PageType;
import org.deals.framework.bean.WebPage;

public class ItemTest extends DealsTestCase {

	
	/**
	 * Recupera le sezioni di una scheda. 
	 * Test1: item con sezioni, i risultati devono essere solo sezioni 
	 * Test2: item senza sezioni, i risultati non devono essere sezioni, non ci deve essere niente
	 */
	public void testGetSections() {
		// TEST 1
		int idItemWithSections = Integer.parseInt(TestConfig
				.getString("DealsTest.ID_ITEM_CON_SECTIONS")); //$NON-NLS-1$
		if (idItemWithSections > 0) {
			WebPage page = getDAOFactory().getItemDAO().findById(
					idItemWithSections, false);
			assertNotNull(
					"SPECIFICARE UNA PAGINA ESISTENTE (" + idItemWithSections + " non la trovo)", page); //$NON-NLS-1$ //$NON-NLS-2$
			assertTrue(page.getPageType().equals(PageType.ITEM()));
			Item item = (Item) page;
			List<WebPage> sex = item.getSections();
			assertTrue("SPECIFICARE UNA PAGINA CON SEZIONI", sex.size() > 0);
			for (Iterator iter = sex.iterator(); iter.hasNext();) {
				WebPage element = (WebPage) iter.next();
				assertTrue("HA RECUPERATO UNA WEBPAGE NON SECTION", element
						.getPageType().equals(PageType.SECTION()));
			}
		} else {
			fail("SPECIFICARE L'ID DI UNA SCHEDA ESISTENTE CON SECTIONS su deals_test.properties");
		}

		// TEST 2
		int idItemWithoutSections = Integer.parseInt(TestConfig
				.getString("DealsTest.ID_ITEM_SENZA_SECTIONS")); //$NON-NLS-1$
		if (idItemWithoutSections > 0) {
			WebPage page = getDAOFactory().getItemDAO().findById(
					idItemWithoutSections, false);
			assertNotNull(
					"SPECIFICARE UNA PAGINA ESISTENTE (" + idItemWithoutSections + " non la trovo)", page); //$NON-NLS-1$ //$NON-NLS-2$
			assertTrue(page.getPageType().equals(PageType.ITEM()));
			Item item = (Item) page;
			assertTrue(item.getSections().size()==0);
		} else {
			fail("SPECIFICARE L'ID DI UNA SCHEDA ESISTENTE SENZA SECTIONS su deals_test.properties");
		}

	}
	
	
	/**
	 * Recupera le categorie di una sezione. 
	 * Test1: section con categorie, i risultati devono essere solo categorie 
	 * Test2: section senza categorie, i risultati non devono essere categorie, non ci deve essere niente
	 */
	public void testGetCategories() {
		// TEST 1
		int idItemWithCategories = Integer.parseInt(TestConfig
				.getString("DealsTest.ID_ITEM_CON_CATEGORIES")); //$NON-NLS-1$
		if (idItemWithCategories > 0) {
			WebPage page = getDAOFactory().getItemDAO().findById(
					idItemWithCategories, false);
			assertNotNull(
					"SPECIFICARE UNA PAGINA ESISTENTE (" + idItemWithCategories + " non la trovo)", page); //$NON-NLS-1$ //$NON-NLS-2$
			assertTrue(page.getPageType().equals(PageType.ITEM()));
			Item item = (Item) page;
			List<WebPage> cats = item.getCategories();
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
		int idItemWithoutCategories = Integer.parseInt(TestConfig
				.getString("DealsTest.ID_ITEM_SENZA_CATEGORIES")); //$NON-NLS-1$
		if (idItemWithoutCategories > 0) {
			WebPage page = getDAOFactory().getItemDAO().findById(
					idItemWithoutCategories, false);
			assertNotNull(
					"SPECIFICARE UNA PAGINA ESISTENTE (" + idItemWithoutCategories + " non la trovo)", page); //$NON-NLS-1$ //$NON-NLS-2$
			assertTrue(page.getPageType().equals(PageType.ITEM()));
			Item item = (Item) page;
			assertTrue(item.getCategories().size()==0);
		} else {
			fail("SPECIFICARE L'ID DI UNA SCHEDA ESISTENTE SENZA CATEGORIES su deals_test.properties");
		}

	}
	
	
	
}
