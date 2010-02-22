/*
 * Created on Nov 14, 2006
 *
 */
package org.deals.framework.cms.forms.eh;

import org.apache.cocoon.forms.binding.BindingException;
import org.apache.cocoon.forms.event.ActionEvent;
import org.apache.cocoon.forms.event.ValueChangedEvent;
import org.apache.cocoon.forms.formmodel.Repeater.RepeaterRow;
import org.apache.log4j.Logger;
import org.deals.framework.cms.DealsSession;
import org.deals.framework.cms.forms.eh.abstracts.AbstractManagementEH;
import org.deals.framework.cms.forms.formbean.ManagementBinder;
import org.deals.framework.cms.forms.formbean.ManagementFB;
import org.deals.framework.cms.forms.wrapper.ManagementFW;
import org.deals.framework.core.WebCMS;

public class ManagementEH extends AbstractManagementEH {

	private Logger log = Logger.getLogger(getClass());
	private ManagementBinder bm;
	private ManagementFB fb;
	private DealsSession cSession;
	private WebCMS cms;

	
	@Override
	public void formOnCreate(ManagementFW f) {
		super.formOnCreate(f);
		cSession = new DealsSession(session);
		bm = new ManagementBinder(session, cms);		
		// quando viene creato il form esegue le seguenti azioni:
		// 1 - viene tirato su il form bean iniziale, quello con tutte le sezioni 
		// 2 - se era stata selezionata una sezione viene trasformato il form bean con le pagine risultanti dalla selezione
		// 3 - se era stata selezionata una categoria viene trasformato il bean con le pagine risultanti dalla selezione
		try {			
			
			fb = bm.reloadFormBean();
			
		    // viene caricato il bean...
			f.load(fb);			
		    
		} catch (BindingException e) {
			log.debug("_______________________ECCEZIONE___________ " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e1) {
			log.debug("_______________________ECCEZIONE___________ " + e1.getMessage());
			e1.printStackTrace();
		}		
	}


	/*
	 * (non-Javadoc)
	 * @see org.deals.framework.cms.forms.eh.abstracts.AbstractManagementEH#handleActionEvent(org.apache.cocoon.forms.event.ActionEvent)
	 * 
	 * Gestisco le azioni sulle righe dei repeater
	 * 
	 */	
	public void handleActionEvent(ActionEvent arg0) {
		super.handleActionEvent(arg0);
		
		// PULSANTE selezione SEZIONE
		// 1. viene recuperato l'id della sezione selezionata dalla riga del repeater
		// 2. viene settato l'id in sessione nel caso in cui si debba ritornare a questa situazione
		// 3. si rimanipola il form bean in base alla selezione fatta
		// 4. si carica il bean sul form
		if (arg0.toString().indexOf("bselectionsection") != -1 ){
			try {
				RepeaterRow row = (RepeaterRow)arg0.getSourceWidget().getParent();
				Integer idSection = Integer.parseInt(row.lookupWidget("idsection").getValue().toString());
				cSession.setIdSectionSelected(idSection);				
				fb = bm.sectionSelected(idSection, fb);
				fw.load(fb);
				return;
			} catch (BindingException e) {
				log.error("ERROR ON SELECT SECTION___________________" + e.getStackTrace()[0]);
				return;
			} catch (Exception e) {
				log.error("___________", e);
				return;
			}
		}

	

		
		// PULSANTE selezione CATEGORIA
		// 1. viene recuperato l'id della categoria selezionata dalla riga del repeater
		// 2. viene settato l'id in sessione nel caso in cui si debba ritornare a questa situazione
		// 3. si rimanipola il form bean in base alla selezione fatta
		// 4. si carica il bean sul form
		if (arg0.toString().indexOf("bselectioncategory") != -1 ){
			try {
				RepeaterRow row = (RepeaterRow)arg0.getSourceWidget().getParent();
				Integer idCategory = Integer.parseInt(row.lookupWidget("idcategory").getValue().toString());
				cSession.setIdCategorySelected(idCategory);
				fb = bm.categorySelected(idCategory, fb);
				fw.load(fb);
				return;
			} catch (BindingException e) {
				log.error("ERROR ON SELECT CATEGORY___________________" + e.getStackTrace()[0]);
				return;
			} catch (Exception e) {
				log.error("__________", e);
				return;
			}
		}
		
		// PULSANTE UP SEZIONE
		// 1. viene recuperato l'id della sezione su cui è stato fatto up dal repeater
		// 2. si rimanipola il form bean in base all'azione fatta
		// 3. si carica il bean sul form
		if (arg0.toString().indexOf("bupsection") != -1 ){
			try {
				RepeaterRow row = (RepeaterRow)arg0.getSourceWidget().getParent();
				Integer idSection = Integer.parseInt(row.lookupWidget("idsection").getValue().toString());
				fb = bm.sectionUp(idSection, fb);
				cms.saveAllAPagePage(fb.getItems());
				fw.load(fb);
				return;
			} catch (BindingException e) {
				log.error("ERROR ON UP SECTION ___________________" + e.getStackTrace()[0]);
				return;
			} catch (Exception e) {
				log.error("__________", e);
				return;
			}
		}

		// PULSANTE DOWN SEZIONE
		// 1. viene recuperato l'id della sezione su cui è stato fatto down dal repeater
		// 2. si rimanipola il form bean in base all'azione fatta
		// 3. si carica il bean sul form
		if (arg0.toString().indexOf("bdownsection") != -1 ){
			try {
				RepeaterRow row = (RepeaterRow)arg0.getSourceWidget().getParent();
				Integer idSection = Integer.parseInt(row.lookupWidget("idsection").getValue().toString());
				fb = bm.sectionDown(idSection, fb);
				cms.saveAllAPagePage(fb.getItems());
				fw.load(fb);
				return;
			} catch (BindingException e) {
				log.error("ERROR ON DOWN SECTION ___________________" + e.getStackTrace()[0]);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				log.error("_______", e);
				return;
			}
		}

		
		// PULSANTE UP CATEGORIA
		// 1. viene recuperato l'id della categoria su cui è stato fatto up dal repeater
		// 2. si rimanipola il form bean in base all'azione fatta
		// 3. si carica il bean sul form
		if (arg0.toString().indexOf("bupcategory") != -1 ){
			try {
				RepeaterRow row = (RepeaterRow)arg0.getSourceWidget().getParent();
				Integer idCategory = Integer.parseInt(row.lookupWidget("idcategory").getValue().toString());
				fb = bm.categoryUp(idCategory, fb);
				cms.saveAllAPagePage(fb.getItems());
				fw.load(fb);
				return;
			} catch (BindingException e) {
				log.error("ERROR ON UP CATEGORY ___________________" + e.getStackTrace()[0]);
				return;
			} catch (Exception e) {
				log.error("______", e);
				return;
			}
		}

		// PULSANTE DOWN CATEGORIA
		// 1. viene recuperato l'id della categoria su cui è stato fatto down dal repeater
		// 2. si rimanipola il form bean in base all'azione fatta
		// 3. si carica il bean sul form
		if (arg0.toString().indexOf("bdowncategory") != -1 ){
			try {
				RepeaterRow row = (RepeaterRow)arg0.getSourceWidget().getParent();
				Integer idCategory = Integer.parseInt(row.lookupWidget("idcategory").getValue().toString());
				fb = bm.categoryDown(idCategory, fb);
				cms.saveAllAPagePage(fb.getItems());
				fw.load(fb);
				return;
			} catch (BindingException e) {
				log.error("ERROR ON DOWN CATEGORY ___________________" + e.getStackTrace()[0]);
				return;
			} catch (Exception e) {
				log.error("______",e);
				return;
			}
		}

		
		// PULSANTE UP SCHEDA
		// 1. viene recuperato l'id della scheda su cui è stato fatto up dal repeater
		// 2. si rimanipola il form bean in base all'azione fatta
		// 3. si carica il bean sul form		
		if (arg0.toString().indexOf("bupitem") != -1 ){
			try {
				RepeaterRow row = (RepeaterRow)arg0.getSourceWidget().getParent();
				Integer idItem = Integer.parseInt(row.lookupWidget("iditem").getValue().toString());
				fb = bm.itemUp(idItem, fb);
				cms.saveAllAPagePage(fb.getItems());
				fw.load(fb);
				return;
			} catch (BindingException e) {
				log.error("ERROR ON UP ITEM ___________________" + e.getStackTrace()[0]);
				return;
			} catch (Exception e) {
				log.error("______",e);
				return;
			}
		}

		// PULSANTE DOWN SCHEDA
		// 1. viene recuperato l'id della scheda su cui è stato fatto down dal repeater
		// 2. si rimanipola il form bean in base all'azione fatta
		// 3. si carica il bean sul form		
		if (arg0.toString().indexOf("bdownitem") != -1 ){
			try {
				RepeaterRow row = (RepeaterRow)arg0.getSourceWidget().getParent();
				Integer idCategory = Integer.parseInt(row.lookupWidget("iditem").getValue().toString());
				fb = bm.itemDown(idCategory, fb);
				cms.saveAllAPagePage(fb.getItems());
				fw.load(fb);
				return;
			} catch (BindingException e) {
				log.error("ERROR ON DOWN ITEM ___________________" + e.getStackTrace()[0]);
				return;
			} catch (Exception e) {
				log.error("______",e);
				return;
			}
		}
	}

	
	public void handleValueChangedEvent(ValueChangedEvent arg0) {
	}


	public void setCms(WebCMS cms) {
		this.cms = cms;
	}
}
