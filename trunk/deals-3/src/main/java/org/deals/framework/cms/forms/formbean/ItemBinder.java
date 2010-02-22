package org.deals.framework.cms.forms.formbean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.deals.framework.bean.APageComponents;
import org.deals.framework.bean.APagePage;
import org.deals.framework.bean.Category;
import org.deals.framework.bean.ComponentType;
import org.deals.framework.bean.PageType;
import org.deals.framework.bean.Section;
import org.deals.framework.bean.Users;
import org.deals.framework.bean.WebPage;
import org.deals.framework.cms.DealsSession;
import org.deals.framework.cms.forms.formbean.ItemFB.CategoryStructure;
import org.deals.framework.cms.forms.formbean.ItemFB.SectionStructure;
import org.deals.framework.cms.forms.wrapper.ItemFW;
import org.deals.framework.core.WebCMS;
import org.deals.framework.util.DateUtils;

public class ItemBinder extends WebPageBinder {

	private final static boolean RETRIEVE_FOR_SITE = false;

	private Logger log = Logger.getLogger(getClass());
	private WebPage currentPage;
	DealsSession ds;

	public ItemBinder(HttpSession session, WebCMS cms) {
		super(session);
		ds = new DealsSession(session);
		this.cms = cms;
	}

	/*
	 * Restituisce un form bean per una nuova scheda
	 */
	public ItemFB newFormBean() throws Exception {
		currentPage = cms.newItem();
		ItemFB fb = new ItemFB(currentPage);
		try {
			Users utenteLoggato = cms.getUser(hos.getloggedUserId());
			fb.getBean().setUsersByWpCreator(utenteLoggato);
		} catch (Exception e) {
			log.debug("Attenzione utente loggato non trovato in sessione", e);
		}
		fb.setTemplateSL(cms.getTemplates());
		fb.setPageStateSL(cms.getPageStates());
		fb.setTags(cms.getTags());
		createSectionAndCategoriesCB(currentPage, fb);
		fb.setImagesresources(cms.getWebResources());
		// inizializza la collection, anche se vuota
		fb.setLinks(currentPage.getComponentsAssoc(ComponentType.RESOURCE_LINK()));
		// inizializza la collection, anche se vuota
		fb.setImages(currentPage.getComponentsAssocResources());
		return fb;
	}

	public ItemFB getFormBean(int id) {
		currentPage = cms.getPage(id);
		ItemFB fb = new ItemFB(currentPage);
		// createSectionAndCategoriesCB(fb, false);
		// TODO forse sarebbe meglio che le collection si caricassero nel costrtturo del formbean
		fb.setTemplateSL(cms.getTemplates());
		fb.setPageStateSL(cms.getPageStates());
		fb.setTags(cms.getTags());
		fb.setImages(currentPage.getComponentsAssocResources());
		fb.setLinks(currentPage.getComponentsAssoc(ComponentType.RESOURCE_LINK()));
		createSectionAndCategoriesCB(currentPage, fb);
		fb.setImagesresources(cms.getWebResources());		
		return fb;
	}

	public String saveFormBean(ItemFB fb, ItemFW f) {
		
		// Prendo i valori delle selection list e rimetto sopra l'oggetto relativo all'ID passato
		fb.getBean().setTemplate(cms.getTemplate(f.getItemTemplateValue()));
		fb.getBean().setPageState(cms.getPageState(f.getItemStateValue()));
		fb.getBean().setPageType(cms.getPageType(PageType.ITEM_ID));

		// Valori automatici su dati di modifica
		fb.getBean().setWpName(fb.getBean().getWpContentTitle());
		fb.getBean().setWpCreated(DateUtils.now());
		fb.getBean().setWpLastModified(DateUtils.now());
		try {
			fb.getBean().setUsersByWpLastModifier(cms.getUser(hos.getloggedUserId()));
		} catch (Exception e) {
			log.error("Errore su valorizzazione utente Modifica", e);
		}
		try {
			if (fb.getBean().getUsersByWpCreator() == null){
				fb.getBean().setUsersByWpCreator(cms.getUser(hos.getloggedUserId()));	
			}
		} catch (Exception e) {
			log.error("Errore su valorizzazione utente Creatore", e);
		}

		// ////////////////////////////////////////////////////////////////////////////////////////////////////////

		Set oldAssocs = currentPage.getAPagePagesForAppChild(); // APagePage sono tutte le
																// associazioni in cui currentPage
																// compare come figlia

		// si prende i parent di tutte ste associazioni, finito tutto,
		// riordina comunque tutti i figli
		List<WebPage> parents = new Vector<WebPage>();
		for (Iterator iter = oldAssocs.iterator(); iter.hasNext();) {
			APagePage element = (APagePage) iter.next();
			parents.add(element.getWebPageByAppParent());
		}

		Vector<SectionStructure> fbss = fb.getSectionStructure(); // struttura che contiene tutti
																	// possibili agganci, bindati
																	// dalla view

		// raccolgo tutte le associazioni che ci vorrebbero....alla fine vado a confrontare con
		// quelle preesistenti
		HashMap<WebPage, APagePage> bindedAssocs = new HashMap<WebPage, APagePage>();
		for (Iterator<SectionStructure> iter = fbss.iterator(); iter.hasNext();) {
			SectionStructure ss = iter.next();
			if (ss.isChecked()) {
				// vuol dire che ci vorrebbe un'associazione tra ss.getPage()[padre] e
				// currentPage[figlia]
				APagePage newAssoc = new APagePage(currentPage, ss.getPage(), 0);
				bindedAssocs.put(ss.getPage(), newAssoc);
			}

			// quindi estrare e visita le categorie della sezione ss
			Vector<CategoryStructure> fbcs = ss.getCategoryStructure();
			for (Iterator<CategoryStructure> iterator = fbcs.iterator(); iterator.hasNext();) {
				CategoryStructure cs = iterator.next();
				if (cs.isChecked()) {
					// vuol dire che ci vorrebbe un'associazione tra cs.getPage()[padre] e
					// currentPage[figlia]
					APagePage newAssoc = new APagePage(currentPage, cs.getPage(), 0);
					bindedAssocs.put(cs.getPage(), newAssoc);
				}
			}
		}

		// in bindedAssocs ho tutte le APagePage definite dopo il binding

		// controllo se nelle vecchie associazioni erano già presenti alcune delle mie
		// nel qual caso ne prendo la priorità e la setto sulla mia

		// il motivo di questo controllo è perché sopra ho creato tutte le associazioni
		// con priorità a 0, quindi se ora le ribecco devo risettare la priorità corretta
		for (Iterator iter = oldAssocs.iterator(); iter.hasNext();) {
			APagePage original = (APagePage) iter.next();
			APagePage element = bindedAssocs.get(original.getWebPageByAppParent());
			if (element != null) {
				element.setAppWeight(original.getAppWeight());
			}
		}

		// ora posso andare al confronto con le associazioni precedenti all'edit....
		// ... e per le assocazioni nuove procedo al calcolo della priorità
		// che sarà maxPriorità(fratelli) + 1
		for (Iterator<APagePage> iter = bindedAssocs.values().iterator(); iter.hasNext();) {
			APagePage element = iter.next();
			if (element.getAppWeight().equals(0)) {
				// devo mettere la priorità a tutte le APagePage con priorità a 0, sono quelle che
				// ho aggiunto prima...
				// devo assegnare max(priorità dei fratelli)+1
				int maxWeight = findMaxWeight(element.getWebPageByAppParent().getAPagePagesForAppParent(), element.getWebPageByAppChild());
				element.setAppWeight(maxWeight + 1);
			}
		}

		// azz, ora se ci sono quelle eliminate dovrei andare a risistemare tutte le priorità
		// ...

		// ....e infine....

		oldAssocs.retainAll(bindedAssocs.values()); // rimuove le associazioni eliminate

		// oldAssocs.clear(); // --

		// oldAssocs.addAll(bindedAssocs.values()); // aggiunge le associazioni nuove

		for (Iterator iter = bindedAssocs.values().iterator(); iter.hasNext();) {
			APagePage element = (APagePage) iter.next();
			if (!contains(oldAssocs, element)) {
				oldAssocs.add(element);
			}
		}

		// mi scorro tutti i padri a cui era associata la scheda prima del binding
		for (Iterator iterParent = parents.iterator(); iterParent.hasNext();) {
			WebPage element = (WebPage) iterParent.next();
			// Set assocs = element.getAPagePagesForAppParent();
			// assocs.retainAll(bindedAssocs.values());
			ArrayList<APagePage> assToRemove = new ArrayList<APagePage>();
			boolean trovato = false;
			// scorro tutte lea ssociazioni preesistenti di padre
			for (Iterator iterElementAssocs = element.getAPagePagesForAppParent().iterator(); iterElementAssocs.hasNext();) {
				// associazione presistente
				APagePage ass = (APagePage) iterElementAssocs.next();

				// scorro tutte leassociazioni passate dalla view
				Iterator iterBindedAssocs = bindedAssocs.values().iterator();
				while (iterBindedAssocs.hasNext()) {
					// associazione bindata
					APagePage assBinded = (APagePage) iterBindedAssocs.next();
					// verifica overloading
					// trovo l'associzione presistente tra la associazioni bindate dalla view?
					if (assBinded.equals(ass)) {
						trovato = true;
					}
				}
			}
			// se non l'ho trovato significa che è stato deassociato
			if (!trovato) {
				// scorro tutte leassociazioni preesistenti, per ripescare la mia associazione
				// cancellata dalla view
				Iterator iterElementAssocs2 = element.getAPagePagesForAppParent().iterator();
				while (iterElementAssocs2.hasNext()) {
					APagePage ass = (APagePage) iterElementAssocs2.next();
					// se l'associazione ho come referenza->figlio la scheda stessa, è proprio
					// quella da cancellare
					if (ass.getWebPageByAppChild().equals(fb.getBean())) {
						assToRemove.add(ass);
					}
				}

			}
			// se c'è un elemento da eliminare (da come è fatto l'algoritmo, credo che massimo sia
			// 1)
			if (assToRemove.size() > 0) {
				// rimuovo la associazione, assToRemove puo anche essere vuota
				element.getAPagePagesForAppParent().removeAll(assToRemove);
				// dopo la rimozione, riordino i restanti fratelli dell'associazione
				orderBrothers(element.getAPagePagesForAppParent());
			}
		}

		// Se è una una nuova scheda va fatto il salvataggio esplicito
		if (fb.getBean().getWpId() == null) {
			cms.saveItem(fb.getBean());
		}



		
		// Ritiro su tutti i link dalla WebPage
		List<APageComponents> toRemove = new Vector<APageComponents>();
		for (Iterator iter = currentPage.getAPageComponentses().iterator(); iter.hasNext();) {
			APageComponents element = (APageComponents) iter.next();
			if (element.getWebComponent().getComponentType().getCtId() == ComponentType.RESOURCE_LINK_ID) {
				toRemove.add(element);
			}
		}

		// Rimuovo i link della WebPage  
		currentPage.getAPageComponentses().removeAll(toRemove);
		// Riattacco tutti i link della WebPage provenienti dal formBean, risultato i
		// - i link modificati cambieranno
		// - i link aggiungi verranno aggiunti
		// - i link eliminati verranno modificati
		// ...
		if (fb.getLinks() != null){
			currentPage.getAPageComponentses().addAll(fb.getLinks());
		}
		
		
		
		// Ritiro su tutti i link dalla WebPage
		List<APageComponents> toRemove2 = new Vector<APageComponents>();
		for (Iterator iter = currentPage.getAPageComponentses().iterator(); iter.hasNext();) {
			APageComponents element = (APageComponents) iter.next();
			if (element.getWebComponent().getComponentType().getCtId() != ComponentType.RESOURCE_LINK_ID) {
				toRemove2.add(element);
			}
		}

		// Rimuovo le immagini della WebPage  
		currentPage.getAPageComponentses().removeAll(toRemove2);
		
		// gestire come sopra
		// Fixme
		if (fb.getImages() != null){
			currentPage.getAPageComponentses().addAll(fb.getImages());
		}

		cms.saveItem(fb.getBean());
		//refreshManagement();
		//hu.putObject(IKeys.CMS_EDIT_ITEM, currentPage);


		// Patch da rivedere
		/*hu.removeObject(IKeys.CMS_MANAGEMENT_ITEMS);
		hu.removeObject(IKeys.CMS_MANAGEMENT_CATEGORIES);
		hu.removeObject(IKeys.CMS_EDIT_ITEM);*/
		return null;
	}

	@SuppressWarnings("unchecked")
	private void orderBrothers(Set assocs) {
		int maxItem = 0;
		int maxCategory = 0;

		List ordered = Arrays.asList(assocs.toArray());
		Collections.sort(ordered, new Comparator() {
			public int compare(Object o1, Object o2) {
				if (o1 == null)
					return -1;
				if (o2 == null)
					return 1;
				if (o1 == o2)
					return 0;
				return ((APagePage) o1).getAppWeight().compareTo(((APagePage) o2).getAppWeight());
			}
		});

		for (Iterator iter = ordered.iterator(); iter.hasNext();) {
			APagePage element = (APagePage) iter.next();
			if (element.getWebPageByAppChild().getPageType().equals(PageType.ITEM())) {
				element.setAppWeight(++maxItem);
			} else {
				element.setAppWeight(++maxCategory);
			}
		}
	}

	private boolean contains(Set oldAssocs, APagePage element) {
		for (Iterator iter = oldAssocs.iterator(); iter.hasNext();) {
			APagePage localElement = (APagePage) iter.next();
			if (localElement.equals(element))
				return true;
		}
		return false;
	}


	public String deleteFormBean(ItemFB fb) {
		try {
			WebPage page = fb.getBean();
			cms.deleteItem(page);
			refreshManagement();
			refreshPriority();

		} catch (RuntimeException e) {
			log.error("_______________Error on remove Item" + e.getMessage());
		}
		return null;
	}

	@Override
	protected void refreshPriority() {
		// prima controllo se è selezionata una categoria
		if (ds.isCategorySelected()) {
			List<APagePage> assocs = ((Category) cms.getPage(ds.getIdCategory())).getItemsAssoc();
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
		} else // altrimenti controllo se è stata selezionata una sezione
		if (ds.isSectionSelected()) {
			List<APagePage> assocs = ((Section) cms.getPage(ds.getIdSection())).getItemsAssoc();
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

	/**
	 * Effettua la validazione della pagina usando il form bean TODO aggiungere qui il controllo che
	 * se hai checckato una sezione non ci siano cat e viceversa
	 * 
	 * @param fb
	 *            form bean su cui validare la pagina
	 * @return
	 */
	/*
	 * FIXME private String validatePage(ItemFB fb) { int checked = 0; Iterator<SectionStructure>
	 * afterIter = fb.getSectionStructure().iterator(); while (afterIter.hasNext()) {
	 * SectionStructure afterSec = afterIter.next(); if (afterSec.isChecked()) checked++; Iterator<CategoryStructure>
	 * afterCatIter = afterSec.getCategoryStructure().iterator(); while (afterCatIter.hasNext()) {
	 * if (afterCatIter.next().isChecked()) checked++; } } if (checked <= 0) return "Associare la
	 * scheda ad almeno una categoria o una sezione"; else return null; }
	 */


	/*
	 * FIXME private boolean areAssociated(int id, TreeSet<HAPagePage> ts) { for (Iterator iter =
	 * ts.iterator(); iter.hasNext();) { HAPagePage element = (HAPagePage) iter.next(); if
	 * (element.getWebPageByAppChild().getWpId().equals(id)) { return true; } } return false; }
	 */

	/**
	 * Crea tutta la struttura di associazione per le sezioni e le categorie
	 */
	private void createSectionAndCategoriesCB(WebPage item, ItemFB fb) {
		// deve chiedere a qualcuno la lista delle sezioni
		List<APagePage> allLinks = new Vector<APagePage>();
		List<WebPage> sections = cms.getSections(ds.getHttpSession());
		if (sections != null) {
			//hu.putObject(IKeys.CMS_EDIT_ITEM_SECTIONS_ASSOC, sections);
			for (int i = 0; i < sections.size(); i++) {
				SectionStructure sec = fb.new SectionStructure(sections.get(i));
				if (item.getParents().contains(sections.get(i)))
					sec.setChecked(true);
				// scorro le categorie della sezione
				Section section = (Section) sections.get(i);
				List<APagePage> catAssocs = section.getCategoriesAssoc();
				allLinks.addAll(catAssocs);
				List<Category> categories = section.getCategories();
				if (categories != null) {
					for (int k = 0; k < categories.size(); k++) {
						CategoryStructure cas = fb.new CategoryStructure(categories.get(k));
						List<APagePage> itemAssocs = ((Category)categories.get(k)).getItemsAssoc();
						allLinks.addAll(itemAssocs);
						if (item.getParents().contains(categories.get(k)))
							cas.setChecked(true);
						// attacco la categoria alla sezione
						sec.addCategory(cas);
					}
				}
				// attacco la sezione
				fb.addSectionStructure(sec);
			}
		}
		// non deve mettere le web page, ma le associazioni APagePage diavolo becco!!!!!
		//hu.putObject(IKeys.CMS_EDIT_ITEM_CATEGORIES_ASSOC, allLinks);
	}

	// metodo per controllare se una page compare tra i padri di una set di APagePage
	private APagePage contains(Set assocs, WebPage page) {
		for (Iterator iter = assocs.iterator(); iter.hasNext();) {
			APagePage element = (APagePage) iter.next();
			if (element.getWebPageByAppParent().equals(page))
				return element;
		}
		return null;
	}
}
