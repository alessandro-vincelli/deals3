package org.deals.framework.test;

import java.util.List;

import org.deals.framework.bean.ComponentType;
import org.deals.framework.bean.WebComponent;
import org.deals.framework.bean.WebPage;
import org.deals.framework.util.XmlUtils;


public class WebPageTest extends DealsTestCase {

	
	/**
	 * Recupera le componenti di una pagina.
	 * Test 1: pagina con componenti, quindi le componenti ci devono essere
	 * Test 2: pagina senza componenti, quindi le componenti NON ci devono essere
	 */
	public void testGetComponents() {
		// TEST 1
		int idPageWithComponents = Integer.parseInt(TestConfig.getString("DealsTest.ID_PAGINA_CON_COMPONENTI")); //$NON-NLS-1$
		if (idPageWithComponents>0) {
		   WebPage page = getDAOFactory().getWebPageDAO().findById(idPageWithComponents, false);
		   assertNotNull("SPECIFICARE UNA PAGINA ESISTENTE (" + idPageWithComponents +" non la trovo)",page); //$NON-NLS-1$ //$NON-NLS-2$
		   assertNotNull("SPECIFICARE UNA PAGINA CON COMPONENTI (" + idPageWithComponents +" non ne ha)",page.getComponents()); //$NON-NLS-1$ //$NON-NLS-2$
		   assertTrue("SPECIFICARE UNA PAGINA CON ALMENO UNA COMPONENTE (" + idPageWithComponents +" non ne ha)",page.getComponents().size()>0); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			fail("SPECIFICARE L'ID DI UNA PAGINA ESISTENTE CON COMPONENTI su deals_test.properties");
		}
		
		// TEST 2
		int idPageWithoutComponents = Integer.parseInt(TestConfig.getString("DealsTest.ID_PAGINA_SENZA_COMPONENTI")); //$NON-NLS-1$
		if (idPageWithoutComponents>0) {
		   WebPage page = getDAOFactory().getWebPageDAO().findById(idPageWithoutComponents, false);
		   assertNotNull("SPECIFICARE UNA PAGINA ESISTENTE (" + idPageWithoutComponents +" non la trovo)",page); //$NON-NLS-1$ //$NON-NLS-2$
		   assertTrue("SPECIFICARE UNA PAGINA SENZA COMPONENTI (" + idPageWithoutComponents +" ne ha " +page.getComponents().size()+")",page.getComponents().size()==0); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			fail("SPECIFICARE L'ID DI UNA PAGINA ESISTENTE SENZA COMPONENTI su deals_test.properties");
		}
	}
	
	/**
	 * Recupera le componenti di una pagina filtrandole per tipo componente
	 * TEST 1: pagina con componenti: provo qualche filtro e controllo che le componenti
	 * restituite siano del tipo richiesto
	 * TEST 2: pagina senza componenti: deve restituire liste vuote senza dare errori
	 */
	public void testGetComponentsComponentType() {
		// TEST 1
		int idPageWithComponents = Integer.parseInt(TestConfig.getString("DealsTest.ID_PAGINA_CON_COMPONENTI")); //$NON-NLS-1$
		if (idPageWithComponents>0) {
			   WebPage page = getDAOFactory().getWebPageDAO().findById(idPageWithComponents, false);
			   List<WebComponent> resLinks = page.getComponents(ComponentType.RESOURCE_LINK());
			   if (resLinks.size()>0) {
				   for (WebComponent component : resLinks) {
					assertTrue(component.getComponentType().equals(ComponentType.RESOURCE_LINK()));
				}
			   }
			   List<WebComponent> webRes = page.getComponents(ComponentType.WEB_RESOURCE());
			   if (webRes.size()>0) {
				   for (WebComponent component : webRes) {
					assertTrue(component.getComponentType().equals(ComponentType.WEB_RESOURCE()));
				}
			   }			   
			   List<WebComponent> pageImage = page.getComponents(ComponentType.PAGE_IMAGE());
			   if (pageImage.size()>0) {
				   for (WebComponent component : pageImage) {
					assertTrue(component.getComponentType().equals(ComponentType.PAGE_IMAGE()));
				}
			   }
			   List<WebComponent> thumbs = page.getComponents(ComponentType.THUMBNAIL());
			   if (thumbs.size()>0) {
				   for (WebComponent component : thumbs) {
					assertTrue(component.getComponentType().equals(ComponentType.THUMBNAIL()));
				}
			   }			   
		} else {
			fail("SPECIFICARE L'ID DI UNA PAGINA ESISTENTE CON COMPONENTI su deals_test.properties");
		}
		
		   //TEST 2
		int idPageWithoutComponents = Integer.parseInt(TestConfig.getString("DealsTest.ID_PAGINA_SENZA_COMPONENTI")); //$NON-NLS-1$
		if (idPageWithoutComponents>0) {
			   WebPage page = getDAOFactory().getWebPageDAO().findById(idPageWithoutComponents, false);
			   List<WebComponent> resLinks = page.getComponents(ComponentType.RESOURCE_LINK());
			   assertTrue(resLinks.size()==0);
			   
			   List<WebComponent> webRes = page.getComponents(ComponentType.WEB_RESOURCE());
			   assertTrue(webRes.size()==0);					   
			   
			   List<WebComponent> pageImage = page.getComponents(ComponentType.PAGE_IMAGE());
			   assertTrue(pageImage.size()==0);
			   
			   List<WebComponent> thumbs = page.getComponents(ComponentType.THUMBNAIL());
			   assertTrue(thumbs.size()==0);
		} else {
			fail("SPECIFICARE L'ID DI UNA PAGINA ESISTENTE SENZA COMPONENTI su deals_test.properties");
		}
		
	}
	
	
	/**
	 * Recupera l'immagine principale associata alla pagina
	 * TEST 1: pagina con pageImage: controllo che ci sia la pageImage
	 * TEST 2: pagina senza pageImage: controllo che non ci sia
	 */
	public void testPageImage() {
		// TEST 1
		int idPageWithPageImage = Integer.parseInt(TestConfig.getString("DealsTest.ID_PAGINA_CON_PAGE_IMAGE")); //$NON-NLS-1$
		if (idPageWithPageImage>0) {
			WebPage page = getDAOFactory().getWebPageDAO().findById(idPageWithPageImage, false);
			assertNotNull(page.getPageImage());
		} else {
			fail("SPECIFICARE L'ID DI UNA PAGINA ESISTENTE CON PAGE IMAGE su deals_test.properties");
		}

		// TEST 2
		int idPageWithoutPageImage = Integer.parseInt(TestConfig.getString("DealsTest.ID_PAGINA_SENZA_PAGE_IMAGE")); //$NON-NLS-1$
		if (idPageWithoutPageImage>0) {
			WebPage page = getDAOFactory().getWebPageDAO().findById(idPageWithoutPageImage, false);
			assertNull(page.getPageImage());
		} else {
			fail("SPECIFICARE L'ID DI UNA PAGINA ESISTENTE SENZA PAGE IMAGE su deals_test.properties");
		}
		
	}
	
	
	/**
	 * Recupera la thumbnail associata alla pagina
	 * TEST 1: pagina con thumbnail: controllo che ci sia la thumbnail
	 * TEST 2: pagina senza thumbnail: controllo che non ci sia
	 */
	public void testThumbnail() {
		// TEST 1
		int idPageWithThumbnail = Integer.parseInt(TestConfig.getString("DealsTest.ID_PAGINA_CON_THUMBNAIL")); //$NON-NLS-1$
		if (idPageWithThumbnail>0) {
			WebPage page = getDAOFactory().getWebPageDAO().findById(idPageWithThumbnail, false);
			assertNotNull(page.getPageThumbnail());
		} else {
			fail("SPECIFICARE L'ID DI UNA PAGINA ESISTENTE CON THUMBNAIL su deals_test.properties");
		}

		// TEST 2
		int idPageWithoutThumbnail = Integer.parseInt(TestConfig.getString("DealsTest.ID_PAGINA_SENZA_THUMBNAIL")); //$NON-NLS-1$
		if (idPageWithoutThumbnail>0) {
			WebPage page = getDAOFactory().getWebPageDAO().findById(idPageWithoutThumbnail, false);
			assertNull(page.getPageThumbnail());
		} else {
			fail("SPECIFICARE L'ID DI UNA PAGINA ESISTENTE SENZA THUMBNAIL su deals_test.properties");
		}
		
	}
	
	/**
	 * Controllo che l'xml prodotto da una pagina sia ben formato
	 */
	public void testPageGetXML() {
		int idPageWithComponents = Integer.parseInt(TestConfig.getString("DealsTest.ID_PAGINA_CON_COMPONENTI")); //$NON-NLS-1$
		if (idPageWithComponents>0) {
		   WebPage page = getDAOFactory().getWebPageDAO().findById(idPageWithComponents, false);
		   assertNotNull("SPECIFICARE UNA PAGINA ESISTENTE (" + idPageWithComponents +" non la trovo)",page); //$NON-NLS-1$ //$NON-NLS-2$
		   assertNotNull("SPECIFICARE UNA PAGINA CON COMPONENTI (" + idPageWithComponents +" non ne ha)",page.getComponents()); //$NON-NLS-1$ //$NON-NLS-2$
		   assertTrue("SPECIFICARE UNA PAGINA CON ALMENO UNA COMPONENTE (" + idPageWithComponents +" non ne ha)",page.getComponents().size()>0); //$NON-NLS-1$ //$NON-NLS-2$

		   try {
			   XmlUtils.parseXmlContent(XmlUtils.prettyPrint(page.toXML()),true);
		   } catch (Exception e) {
			   fail("XML PRODOTTO NON VALIDO");
		   }
		   
		} else {
			fail("SPECIFICARE L'ID DI UNA PAGINA ESISTENTE CON COMPONENTI su deals_test.properties");
		}
	}

}
