package org.deals.framework.cms.forms.eh;

import org.apache.cocoon.forms.binding.BindingException;
import org.apache.cocoon.forms.event.RepeaterEvent;
import org.apache.cocoon.forms.event.RepeaterEventAction;
import org.apache.cocoon.forms.event.RepeaterListener;
import org.apache.cocoon.forms.formmodel.Action;
import org.apache.cocoon.forms.formmodel.Field;
import org.apache.cocoon.forms.formmodel.Repeater;
import org.apache.cocoon.forms.formmodel.Repeater.RepeaterRow;
import org.apache.cocoon.forms.validation.ValidationError;
import org.apache.log4j.Logger;
import org.deals.framework.bean.Users;
import org.deals.framework.cms.forms.eh.abstracts.AbstractUsersManagerEH;
import org.deals.framework.cms.forms.formbean.UsersManagerBinder;
import org.deals.framework.cms.forms.formbean.UsersManagerFB;
import org.deals.framework.cms.forms.wrapper.GestioneUtentiFW;
import org.deals.framework.core.WebCMS;

public class UsersManagerEH extends AbstractUsersManagerEH {

	private Logger log = Logger.getLogger(getClass());
	private UsersManagerBinder bm;
	private UsersManagerFB fb;
	private WebCMS cms;	
	
	public void setCms(WebCMS cms) {
		this.cms = cms;
	}

	@Override
	public void formOnCreate(GestioneUtentiFW f) {
		super.formOnCreate(f);
		f.getUtenti().getRepeater().addRepeaterListener(new UtentiListener());
		bm = new UsersManagerBinder(session, cms);		
		fb = bm.getFormBean();
		
		try {
			f.load(fb);
		} catch (BindingException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void utentiactionOnAction(GestioneUtentiFW f, Action source) {
		// scatena semplicemente il controllo degli errori, il boolean
		// che restituisce ci dice soltanto se la validazione e' stata gia' fatta e non
		// se il form e' valido
		f.getForm().validate();		
		if (f.getForm().isValid()) {
		   // allora posso procedere al salvataggio
		   try {
			 f.save(fb);
  		     bm.saveFormBean(fb);
  		     
  		     // ritira su la situazione aggiornata
  		     fb = bm.getFormBean();
  		     f.load(fb);
		   } catch (BindingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}		
	}

	class UtentiListener implements RepeaterListener {

		public void repeaterModified(RepeaterEvent event) {
			if (event.getAction().equals(RepeaterEventAction.ROW_ADDED)) {
				// add rowid on new row
				fw.getUtenti().setRowidValue(fw.getUtenti().getRepeater().getSize(), fw.getUtenti().getRepeater().getSize()-1);
				RepeaterRow  rr = (RepeaterRow)((Repeater)event.getSourceWidget()).getRow(event.getRow());
				((Field)rr.lookupWidget("profile")).setSelectionList(fb.getProfileSL(), "upId", "upName");
				return;
			}

			if (event.getAction().equals(RepeaterEventAction.ROW_DELETING)) {
				RepeaterRow  rr = (RepeaterRow)((Repeater)event.getSourceWidget()).getRow(event.getRow());
				
				Integer id = (Integer)rr.lookupWidget("us_id").getValue();
				Users userToDelete = cms.getUser(id);
				if (!userToDelete.getWebPagesForWpCreator().isEmpty()){
					log.error("ATTENZIONE: - stai cercando di eliminare un utente creatore di " + userToDelete.getWebPagesForWpCreator().size() + " pagine");
					((Field)rr.lookupWidget("nome")).setValidationError(new ValidationError("EEEEEEEE"));
				}
				else
				if (!userToDelete.getWebPagesForWpLastModifier().isEmpty()){
					log.error("ATTENZIONE: - stai cercando di eliminare un utente modifier di " + userToDelete.getWebPagesForWpLastModifier().size() + " pagine");
					((Field)rr.lookupWidget("nome")).setValidationError(new ValidationError("EEEEEEEE"));
				}
				else
					cms.deleteUser(userToDelete);
				return;	
			}
		}

	}	
	
	
}
