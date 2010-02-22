package org.deals.framework.cms.forms.formbean;

import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.deals.framework.bean.UserProfile;
import org.deals.framework.cms.DealsSession;
import org.deals.framework.core.WebCMS;

public class ProfilesManagerBinder {

	//private HibernateUtiliObjectInHttpSession huos;

	private Logger log = Logger.getLogger(getClass()); 
	
	DealsSession ds;

	private WebCMS cms;

	ProfilesManagerFB fb;

	public ProfilesManagerBinder(HttpSession session, WebCMS cms) {
		//huos = new HibernateUtiliObjectInHttpSession(session);
		ds = new DealsSession(session);
		fb = new ProfilesManagerFB();
		this.cms = cms;
	}

	public ProfilesManagerFB getFormBean() {
		// chiede al CMS la lista di tutte le web components presenti
		// e le restituisce
		fb.setProfiles(cms.getProfili());
		//huos.putObject(IKeys.CMS_EDIT_PROFILES_PROFILES, fb.getProfiles());
		return fb;
	}

	public void saveFormBean(ProfilesManagerFB fb) {

		/*List<UserProfile> dbProfiles = cms.getProfili();
		dbProfiles.removeAll(fb.getProfiles());
		for (Iterator<UserProfile> iter = dbProfiles.iterator(); iter.hasNext();) {
			UserProfile element = (UserProfile) iter.next();
			if (element != null) {
				if (element.getUserses().isEmpty()
						&& element.getAProfilePageses().isEmpty())
					cms.deleteProfile(element);
				else
					log.error("ATTENZIONE - si sta cercando di eliminare un profilo associato a utenti attivi");
			}
		}
		
		dbProfiles.clear();
*/
		for (Iterator<UserProfile> iter = fb.getProfiles().iterator(); iter.hasNext();) {
			UserProfile element = (UserProfile) iter.next();
		//	if (element.getUpId() == null) {
				cms.saveProfile(element);
			//}

		}
		//dbProfiles.addAll(fb.getProfiles());
	}

}
