package org.deals.framework.cms.forms.formbean;

import java.io.IOException;
import java.util.List;

import javax.jcr.Session;
import javax.servlet.http.HttpSession;

import org.deals.framework.bean.APagePage;
import org.deals.framework.bean.Category;
import org.deals.framework.bean.Section;
import org.deals.framework.bean.WebPage;
import org.deals.framework.cms.DealsSession;
import org.deals.framework.core.WebCMS;

public class ManagementBinder {

	private final static boolean RETRIEVE_FOR_SITE = false;

	// private Logger log = Logger.getLogger(getClass());
	private WebCMS cms;

	//private HibernateUtiliObjectInHttpSession huos;

	DealsSession ds;

	
	public ManagementBinder(HttpSession session, WebCMS cms) {
		//huos = new HibernateUtiliObjectInHttpSession(session);
		ds = new DealsSession(session);
		this.cms = cms;
	}

	/**
	 * Genera il FormBean contenente la situazione iniziale
	 * 
	 * @return FormBean contenente la situazione iniziale
	 * @throws Exception
	 */
	public ManagementFB reloadFormBean() throws Exception {
		ManagementFB fb = new ManagementFB();
		List<WebPage> sections = cms.getSections(ds.getHttpSession());
		fb.setSections(sections);

		// mette le sezioni in sessione per il riattacco
		//huos.putObject(IKeys.CMS_MANAGEMENT_SECTIONS, sections);

		if (ds.isSectionSelected()) {
			fb = sectionSelected(ds.getIdSection(), fb);
		}

		if (ds.isCategorySelected()) {
			fb = categorySelected(ds.getIdCategory(), fb);
		}

		return fb;
	}

	/**
	 * Restituisce il formBean ottenuto dopo la selezione della sezione passata
	 * 
	 * @param sectionId
	 *            id della sezione selezionata
	 * @return il formBean ottenuto dopo la selezione della sezione passata
	 * 
	 */
	public ManagementFB sectionSelected(int sectionId, ManagementFB fb) {
		WebPage wp = cms.getSection(sectionId);
		wp.getAProfilePages();
		wp.getPageType().getPtName();
		Section sec = (Section)wp;
		
		if (sec != null) {
			List<APagePage> categories = sec.getCategoriesAssoc();
			
			// mette i collegamenti tra la sezione e le sue categorie
			// pronte per il riattacco.... 
			// occhio, i collegamenti, non le categorie!!!
			//huos.putObject(IKeys.CMS_MANAGEMENT_CATEGORIES, categories);
			
			fb.setSectionSelected(sectionId);
			fb.setCategories(categories);
			fb.setCategorySelected(0);
			List<APagePage> items = sec.getItemsAssoc();
			fb.setItems(items);
			
			// mette i collegamenti tra la sezione e le sue schede
			// pronte per il riattacco....
			//occhio, i collegamenti, non le schede
			//huos.putObject(IKeys.CMS_MANAGEMENT_ITEMS, items);

		}
		return fb;
	}

	/**
	 * Restituisce il formBean ottenuto dopo la selezione della categoria passata
	 * 
	 * @param categoryId
	 *            id della categoria selezionata
	 * @return il formBean ottenuto dopo la selezione della categoria passata
	 */
	public ManagementFB categorySelected(int categoryId, ManagementFB fb) {

		Category cat = (Category) cms.getCategory(categoryId);
		List<APagePage> items = cat.getItemsAssoc();
		
		// aggiunge i collegamenti tra la categoria e le sue schede
		//huos.putObject(IKeys.CMS_MANAGEMENT_ITEMS, items);
		fb.setCategorySelected(categoryId);
		fb.setItems(items);

		return fb;
	}

	/**
	 * Restituisce il formBean ottenuto dopo lo spostamento in basso di una sezione
	 * 
	 * @param sectionId
	 *            id della sezione spostata
	 * @return formBean ottenuto dopo lo spostamento in basso di una sezione
	 */
	public ManagementFB sectionDown(int sectionId, ManagementFB fb) throws Exception {
		boolean result = cms.tearDownSection(sectionId, fb.getSections());
		return fb;
	}

	/**
	 * Restituisce il formBean ottenuto dopo lo spostamento in alto di una sezione
	 * 
	 * @param sectionId
	 *            id della sezione spostata
	 * @return formBean ottenuto dopo lo spostamento in alto di una sezione
	 */
	public ManagementFB sectionUp(int sectionId, ManagementFB fb) throws Exception {
		boolean result = cms.setUpSection(sectionId, fb.getSections());
		return fb;
	}

	/**
	 * Restituisce il formBean ottenuto dopo lo spostamento in alto di una categoria
	 * 
	 * @param categoryId
	 *            id della categoria spostata
	 * @return formBean ottenuto dopo lo spostamento in alto di una categoria
	 * @throws Exception
	 * @throws IOException
	 * @throws ComponentTypeNotFoundException
	 */
	public ManagementFB categoryUp(int categoryId, ManagementFB fb) {
		boolean result = cms.setUpCategory(categoryId, fb.getCategories());
		return fb;
	}

	/**
	 * Restituisce il formBean ottenuto dopo lo spostamento in basso di una categoria
	 * 
	 * @param categoryId
	 *            id della categoria spostata
	 * @return formBean ottenuto dopo lo spostamento in basso di una categoria
	 * @throws Exception
	 * @throws IOException
	 * @throws ComponentTypeNotFoundException
	 */
	public ManagementFB categoryDown(int categoryId, ManagementFB fb) {
		boolean result = cms.tearDownCategory(categoryId, fb.getCategories());
		return fb;
	}

	/**
	 * Restituisce il formBean ottenuto dopo lo spostamento in alto di una scheda
	 * 
	 * @param itemDown
	 *            id della scheda spostata
	 * @return formBean ottenuto dopo lo spostamento in alto di una scheda
	 * @throws Exception
	 */
	public ManagementFB itemUp(int itemId, ManagementFB fb) throws Exception {
		boolean result = cms.setUpItem(itemId, fb.getItems());
		return fb;
	}

	/**
	 * Restituisce il formBean ottenuto dopo lo spostamento in basso di una scheda
	 * 
	 * @param itemDown
	 *            id della scheda spostata
	 * @return formBean ottenuto dopo lo spostamento in basso di una scheda
	 * @throws Exception
	 * @throws ComponentTypeNotFoundException
	 */
	public ManagementFB itemDown(int itemId, ManagementFB fb) throws Exception {
		boolean result = cms.tearDownItem(itemId, fb.getItems());
		return fb;
	}

}
