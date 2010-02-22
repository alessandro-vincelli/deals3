package org.deals.framework.cms.forms.formbean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.deals.framework.bean.APagePage;
import org.deals.framework.bean.AProfilePages;
import org.deals.framework.bean.PageType;
import org.deals.framework.bean.Section;
import org.deals.framework.bean.UserProfile;
import org.deals.framework.bean.Users;
import org.deals.framework.bean.WebPage;
import org.deals.framework.cms.forms.formbean.CategoryFB.SectionStructure;
import org.deals.framework.cms.forms.wrapper.CategoryFW;
import org.deals.framework.core.WebCMS;
import org.deals.framework.util.DateUtils;

public class CategoryBinder extends WebPageBinder {

	private Logger log = Logger.getLogger(getClass());

	private WebPage currentPage = null;

	public CategoryBinder(HttpSession session, WebCMS cms) {
		super(session);
		this.cms = cms;
	}

	/*
	 * Restituisce un form bean per una nuova categoria
	 */
	public CategoryFB newFormBean() {
		currentPage = cms.newCategory();
		CategoryFB fb = new CategoryFB(currentPage);

		try {
			Users utenteLoggato = cms.getUser(hos.getloggedUserId());
			fb.getBean().setUsersByWpCreator(utenteLoggato);
		} catch (Exception e) {
			log.debug("Attenzione utente loggato non trovato in sessione", e);
		}

		fb.setTemplateSL(cms.getTemplates());
		fb.setPageStateSL(cms.getPageStates());
		createSectionsCB(currentPage, fb);

		fb.setProfiles(cms.getProfiliGestiti(currentPage));

		return fb;
	}

	public CategoryFB getFormBean(int id) {
		currentPage = cms.getCategory(id);
		CategoryFB fb = new CategoryFB(currentPage);
		fb.setTemplateSL(cms.getTemplates());
		fb.setPageStateSL(cms.getPageStates());
		createSectionsCB(currentPage, fb);
		// hu.putObject(IKeys.CMS_EDIT_CATEGORY, currentPage);

		fb.setProfiles(cms.getProfiliGestiti(currentPage));
		// hu.putObject(IKeys.CMS_EDIT_CATEGORIES_PROFILES, fb.getProfiles());

		return fb;
	}

	private void bindPageToFormBean(WebPage page, CategoryFB fb) {
		/*
		 * fb.setBean(page); fb.setCategoryTitle(page.getContentTitle());
		 * fb.setCategoryAuthor(page.getAuthor());
		 * fb.setCategoryCreator(page.getCreator().getUsername());
		 * fb.setCategoryLastModifier(page.getLastModifier().getUsername());
		 * fb.setCategoryDate(page.getCreated());
		 * fb.setCategoryShort(page.getHeader()); //// DESCRIPTION o HEADER????
		 * fb.setCategoryBody(page.getContent());
		 * 
		 * if (page.getAuthor()!=null) { if (!page.getAuthor().equals("")) {
		 * fb.setCheckChangeAuthor(true); } }
		 * fb.setCategoryState(page.getPageState().getId()); //DA VERIFICARE LA
		 * ENUM NEL MODEL
		 * 
		 * if (page.getTemplate()!=null)
		 * fb.setCategoryTemplate(page.getTemplate().getId());
		 * 
		 * fb.setCategoryExpiration(page.getExpireDate());
		 * 
		 * if (page.getExpireDate() == null) fb.setCategoryNoexpiration(true);
		 * 
		 * fb.setCategoryPrint(page.canPrint());
		 * fb.setCategorySend(page.canSend());
		 * 
		 * fb.setTemplateSL(createTemplateSL());
		 * fb.setPageStateSL(createPageStateSL());
		 */
	}

	public void saveFormBean(CategoryFB fb, CategoryFW f) {
		currentPage = fb.getBean();

		// gestione dei permessi

		// questa è la situazione dei permessi all'atto del save
		List<UserProfile> profili = fb.getProfiles();

		// queste sono le associazioni attuali
		Set apps = currentPage.getAProfilePages();

		// per ogni profilo controllo:
		for (Iterator iter = profili.iterator(); iter.hasNext();) {
			UserProfile prof = (UserProfile) iter.next();
			// 1. se è associato nel bean e non lo trovo nelle app lo aggiungo
			if (prof.isAssociated()) {
				boolean found = false;
				for (Iterator iterator = apps.iterator(); iterator.hasNext();) {
					AProfilePages app = (AProfilePages) iterator.next();
					if (app.getProfile().equals(prof)) {
						found = true;
						break;
					}
				}
				if (!found) {
					apps.add(new AProfilePages(currentPage, prof, 777));
				}
			}
			// 2. se non è associato nel bean e lo trovo nelle app lo elimino
			else {
				for (Iterator iterator = apps.iterator(); iterator.hasNext();) {
					AProfilePages app = (AProfilePages) iterator.next();
					if (app.getProfile().equals(prof)) {
						apps.remove(app);
						break;
					}
				}
			}
		}

		// Prendo i valori delle selection list e rimetto sopra l'oggetto
		// relativo all'ID passato
		fb.getBean().setTemplate(cms.getTemplate(f.getCategoryTemplateValue()));
		fb.getBean().setPageState(cms.getPageState(f.getCategoryStateValue()));

		fb.getBean().setPageType(cms.getPageType(PageType.CATEGORY_ID));

		// Valori automatici su dati di modifica
		fb.getBean().setWpName(fb.getBean().getWpContentTitle());
		fb.getBean().setWpCreated(DateUtils.now());
		fb.getBean().setWpLastModified(DateUtils.now());
		try {
			fb.getBean().setUsersByWpLastModifier(cms.getUser(hos.getloggedUserId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (fb.getBean().getUsersByWpCreator() == null){
				fb.getBean().setUsersByWpCreator(cms.getUser(hos.getloggedUserId()));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Va gestito il caso non fosse selezionata alcuna associazione a
		// Sezione, qui va fatto
		// il test ma la validazione ma vatta sul form
		// Inizio Gestione associazioni
		Set assocs = currentPage.getAPagePagesForAppChild();
		Vector<SectionStructure> fbss = fb.getSectionStructure();
		// scorro fbss e per ogni section devo controllare:
		// se c'era la elimino, se non c'era lo devo aggiungere

		for (Iterator<SectionStructure> iter = fbss.iterator(); iter.hasNext();) {
			SectionStructure ss = iter.next();
			// TODO va gestita la priorità delle associazioni
			// deve trovare il max delle associazioni...

			APagePage newAssoc = new APagePage(currentPage, ss.getPage(),
					findMaxWeight(assocs) + 1);
			List<APagePage> lass = ((Section) ss.getPage())
					.getCategoriesAssoc();
			if (ss.isChecked()) {
				// controllare se nelle mie assocs c'è questa pagina, altrimenti
				// la aggiungo
				if (contains(assocs, ss.getPage()) == null) {
					// ho aggiunto una associazione, quindi ora ordino e
					// riscrivo le priorità
					int max = 0;
					for (int i = 0; i < lass.size(); i++) {
						if (lass.get(i).getAppWeight() > max)
							max = lass.get(i).getAppWeight();
					}
					newAssoc.setAppWeight(max + 1);
					assocs.add(newAssoc);
				}
			} else {
				// controllare se nelle mie assocs c'è questa pagina, allora la
				// devo eliminare
				if ((newAssoc = contains(assocs, ss.getPage())) != null) {
					assocs.remove(newAssoc);
					lass.remove(newAssoc);
				}
			}

			// riordinamento delle associazioni
			Collections.sort(lass, new Comparator<APagePage>() {
				public int compare(APagePage o1, APagePage o2) {
					if (o1.getAppWeight() == null)
						return -1;
					if (o2.getAppWeight() == null)
						return 1;
					return o1.getAppWeight().compareTo(o2.getAppWeight());
				}
			});
			for (int i = 0; i < lass.size(); i++) {
				lass.get(i).setAppWeight(i + 1);
			}
		}

		cms.saveCategory(fb.getBean());

		refreshManagement();
		
	}

	// metodo per controllare se una page compare tra i padri di una set di
	// APagePage
	private APagePage contains(Set assocs, WebPage page) {
		for (Iterator iter = assocs.iterator(); iter.hasNext();) {
			APagePage element = (APagePage) iter.next();
			if (element.getWebPageByAppParent().equals(page))
				return element;
		}
		return null;
	}

	private void eliminaAssociazione(int id, TreeSet assoc) {
		/*
		 * HAPagePageDAO dao = new HAPagePageDAO(); for (Iterator iter =
		 * assoc.iterator(); iter.hasNext();) { HAPagePage element =
		 * (HAPagePage) iter.next(); if
		 * (element.getWebPageByAppParent().getWpId().equals(id)) {
		 * dao.delete(element); // assoc.remove(element); return; } }
		 */
	}

	private void creaAssociazione(WebPage child, Section parent) {
		/*
		 * HAPagePageDAO dao = new HAPagePageDAO(); HAPagePage hp = new
		 * HAPagePage(child.getEntity(),parent.getEntity()); dao.save(hp); //
		 * hp.setAppWeight(parent.getEntity().getAPagePagesForAppParent().size()+1); //
		 * parent.getEntity().getAPagePagesForAppParent().add(hp);
		 * 
		 */
	}

	/**
	 * Crea tutta la struttura di associazione per le sezioni
	 */
	private void createSectionsCB(WebPage category, CategoryFB fb) {
		// deve chiedere a qualcuno la lista delle sezioni
		List<WebPage> sections = cms.getSections(ds.getHttpSession());
		List<APagePage> catAssocs = new ArrayList<APagePage>();
		if (sections != null) {
			for (int i = 0; i < sections.size(); i++) {
				SectionStructure sec = fb.new SectionStructure(sections.get(i));
				catAssocs
						.addAll(((Section) sec.getPage()).getCategoriesAssoc());
				if (category.getParents().contains(sections.get(i)))
					sec.setChecked(true);
				fb.addSectionStructure(sec);
			}
			// hu.putObject(IKeys.CMS_EDIT_CATEGORY_SECTIONS_ASSOC, catAssocs);
		}
	}

	/**
	 * 
	 * cancella una categoria
	 * 
	 * @param fb
	 */
	public void deleteFormBean(CategoryFB fb) {
		cms.deleteCategory(fb.getBean());
		// check if category is select in Deals Session 
		if(ds.isCategorySelected() && fb.getBean().getWpId().compareTo(ds.getIdCategory()) == 0 ){
			ds.deselectCategory();
		}
		refreshManagement();
		refreshPriority();
	}

	@Override
	protected void refreshPriority() {
		// deve aggiornare le priorità di tutte le associazioni tra
		// la sezioni attualmente selezionata e le sue categories

		// per sicurezza, perché la sezione selezionata CI DEVE essere
		if (ds.isSectionSelected()) {
			List<APagePage> assocs = ((Section) cms.getPage(ds.getIdSection()))
					.getCategoriesAssoc();
			Collections.sort(assocs, new Comparator<APagePage>() {
				public int compare(APagePage o1, APagePage o2) {
					if (o1.getAppWeight() == null)
						return -1;
					if (o2.getAppWeight() == null)
						return 1;
					return o1.getAppWeight().compareTo(o2.getAppWeight());
				}
			});
			for (int i = 0; i < assocs.size(); i++) {
				assocs.get(i).setAppWeight(i + 1);
			}
		}
	}

}
