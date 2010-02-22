package org.deals.framework.cms.forms.formbean;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.deals.framework.bean.AProfilePages;
import org.deals.framework.bean.PageType;
import org.deals.framework.bean.UserProfile;
import org.deals.framework.bean.Users;
import org.deals.framework.bean.WebPage;
import org.deals.framework.cms.forms.wrapper.SectionFW;
import org.deals.framework.core.WebCMS;
import org.deals.framework.util.DateUtils;

public class SectionBinder extends WebPageBinder {
	
	private Logger log = Logger.getLogger(getClass());

	private WebPage currentPage = null;

	public SectionBinder(HttpSession session, WebCMS cms) {
		super(session);
		this.cms = cms;
	}

	public SectionFB newFormBean() {
		currentPage = cms.newSection();
		SectionFB fb = new SectionFB(currentPage);
		try {
			Users utenteLoggato = cms.getUser(hos.getloggedUserId());
			fb.getBean().setUsersByWpCreator(utenteLoggato);
		} catch (Exception e) {
			log.debug("Attenzione utente loggato non trovato in sessione", e);
		}
		fb.setTemplateSL(cms.getTemplates());
		fb.setPageStateSL(cms.getPageStates());
		
		
		fb.setProfiles(cms.getProfiliGestiti());
		//hu.putObject(IKeys.CMS_EDIT_SECTIONS_PROFILES, fb.getProfiles());	
		
		return fb;
	}

	
	
	public SectionFB getFormBean(int id) {
		currentPage = cms.getSection(id);
		SectionFB fb = new SectionFB(currentPage);
		fb.setTemplateSL(cms.getTemplates());
		fb.setPageStateSL(cms.getPageStates());
		//hu.putObject(IKeys.CMS_EDIT_SECTION, currentPage);
		
		fb.setProfiles(cms.getProfiliGestiti(currentPage));
		//hu.putObject(IKeys.CMS_EDIT_SECTIONS_PROFILES, fb.getProfiles());	
		
		return fb;
	}

	public void saveFormBean(SectionFB fb, SectionFW f) {

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
		
		
		
		// WebPage page = fb.getBean();
		// page.setContentTitle(fb.getSectionTitle());
		// page.setAuthor(fb.getSectionAuthor());
		//
		// // TODO il creator non e' modificabile però va preso dalla sessione o da dove veniva
		// creato all'inizio
		// //page.setCreator(User.ALEX());
		//		
		// // last modifier va modificato ma in automatico dalla sessione
		// page.setCreated(fb.getSectionDate());
		// page.setHeader(fb.getSectionShort());
		// page.setContent(fb.getSectionBody());
		// // bindare lo state se e' cambiato
		// page.setPageState(PageState.getStateById(fb.getSectionState()));
		// page.setTemplate(new TemplateDAL().getTemplate(fb.getSectionTemplate()));
		// page.setExpireDate(fb.getSectionExpiration());
		// page.setCanPrint(fb.isSectionPrint());
		// page.setCanSend(fb.isSectionSend());
		//		
		// page.setName(fb.getSectionTitle());
		//		
		// // qui tra le altre cose se ho una weight=-1 devo pescare il max tra le weight
		// // e settarla all'amico fritz
		//		
		// // Vector<WebPage> secs = cms.getSections();
		// // if (secs.lastElement()!=null)
		// // page.setWeight(secs.lastElement().getWeight()+1);
		// // else
		// // page.setWeight(1);
		//
		// cms.saveWebPage(page);
		// // // // //////////////////////////////////////// // // //

		// Prendo i valori delle selection list e rimetto sopra l'oggetto relativo all'ID passato
		fb.getBean().setTemplate(cms.getTemplate(f.getSectionTemplateValue()));
		fb.getBean().setPageState(cms.getPageState(f.getSectionStateValue()));

		
		fb.getBean().setPageType(cms.getPageType(PageType.SECTION_ID));

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
		
		
		// Se è una una nuova sezione va fatto il salvataggio esplicito, e setta la priorità 
		if (fb.getBean().getWpId() == null) {
			// setta la priorità
			fb.getBean().setWpWeight(cms.getMaxWeightSection() + 1);
			// persiste il nuovo oggetto
			cms.saveSection(fb.getBean());
		}
		else{
			cms.saveSection(fb.getBean());
		}
	}

	/**
	 * 
	 * cancella una sezione
	 * 
	 * @param fb
	 */
	public void deleteFormBean(SectionFB fb) {
		cms.deleteSection(fb.getBean());
		// check if category is select in Deals Session 
		if(ds.isSectionSelected() && fb.getBean().getWpId().compareTo(ds.getIdSection()) == 0 ){
			ds.deselectSection();
		}
		// ha eliminato una sezione, deve riordinare le priorità delle sezioni
		// in modo da non lasciare buchi
		// Devo aggiornare le sezioni nel management
		refreshManagement();
		refreshPriority();
	}

	
	@Override
	protected void refreshPriority() {
		List<WebPage> sections = cms.getSections();			
		
		for (int i = 0; i < sections.size(); i++) {
			sections.get(i).setWpWeight(i+1);
		}		
	}
}
