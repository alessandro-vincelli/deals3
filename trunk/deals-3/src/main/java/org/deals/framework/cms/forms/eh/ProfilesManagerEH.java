package org.deals.framework.cms.forms.eh;

import org.apache.cocoon.forms.binding.BindingException;
import org.apache.cocoon.forms.event.RepeaterEvent;
import org.apache.cocoon.forms.event.RepeaterEventAction;
import org.apache.cocoon.forms.event.RepeaterListener;
import org.apache.cocoon.forms.formmodel.Action;
import org.apache.cocoon.forms.formmodel.Repeater;
import org.apache.cocoon.forms.formmodel.Repeater.RepeaterRow;
import org.deals.framework.cms.forms.eh.abstracts.AbstractProfilesManagerEH;
import org.deals.framework.cms.forms.formbean.ProfilesManagerBinder;
import org.deals.framework.cms.forms.formbean.ProfilesManagerFB;
import org.deals.framework.cms.forms.wrapper.GestioneProfiliFW;
import org.deals.framework.core.WebCMS;

public class ProfilesManagerEH extends AbstractProfilesManagerEH {

	private ProfilesManagerBinder bm;
	private ProfilesManagerFB fb;
	private WebCMS cms;
	
	@Override
	public void formOnCreate(GestioneProfiliFW f) {
		super.formOnCreate(f);
		f.getProfili().getRepeater().addRepeaterListener(new ProfiliListener());

		bm = new ProfilesManagerBinder(session, cms);
		
		// deve creare il form bean chiedendolo al binder,
		// quindi lo può caricare...
		fb = bm.getFormBean();
		
		try {
			f.load(fb);
		} catch (BindingException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void profiliactionOnAction(GestioneProfiliFW f, Action source) {
		// scatena semplicemente il controllo degli errori, il boolean
		// che restituisce ci dice soltanto se la validazione è stata già fatta e non
		// se il form è valido
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

	


	class ProfiliListener implements RepeaterListener {

		public void repeaterModified(RepeaterEvent event) {

			if (event.getAction().equals(RepeaterEventAction.ROW_ADDED)) {
				// add rowid on new row
				fw.getProfili().setRowidValue(fw.getProfili().getRepeater().getSize(), fw.getProfili().getRepeater().getSize()-1);
				return;
			}

			if (event.getAction().equals(RepeaterEventAction.ROW_DELETING)) {
				RepeaterRow  rr = (RepeaterRow)((Repeater)event.getSourceWidget()).getRow(event.getRow());
				Integer id = (Integer)rr.lookupWidget("up_id").getValue();
				cms.deleteProfile(cms.geProfile(id));
				return;
			}
			
		}

	}




	public void setCms(WebCMS cms) {
		this.cms = cms;
	}	
	
	
}
