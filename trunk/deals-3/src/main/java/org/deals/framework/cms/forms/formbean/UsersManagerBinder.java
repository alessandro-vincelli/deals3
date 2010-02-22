package org.deals.framework.cms.forms.formbean;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.deals.framework.cms.DealsSession;
import org.deals.framework.core.WebCMS;

public class UsersManagerBinder {

	private Logger log = Logger.getLogger(getClass()); 
	
	DealsSession ds;

	private WebCMS cms;

	UsersManagerFB fb;

	public UsersManagerBinder(HttpSession session, WebCMS cms) {
		ds = new DealsSession(session);
		fb = new UsersManagerFB();
		this.cms = cms;
	}

	public UsersManagerFB getFormBean() {
		// chiede al CMS la lista di tutte le web components presenti
		// e le restituisce
		fb.setUsers(cms.getAllUsers());
		// utilizzato nel custom binding
		fb.setProfileSL(cms.getProfili());
		//huos.putObject(IKeys.CMS_USERS, fb.getUsers());
		return fb;
	}

	public void saveFormBean(UsersManagerFB fb) {

		//FIXME the delete must be reimplemented, in this way doesn't work, is dangerous retrive the same Users Entity, you must work only with the entity in passed from the View
//		List<Users> dbUsers = cms.getAllUsers();
//		dbUsers.removeAll(fb.getUsers());
		/*		for (Iterator<Users> iter = dbUsers.iterator(); iter.hasNext();) {
			Users element = (Users) iter.next();
			if (element != null) {
				if (!element.getWebPagesForWpCreator().isEmpty())
					log.error("ATTENZIONE: - stai cercando di eliminare un utente creatore di " + element.getWebPagesForWpCreator().size() + " pagine");
				else
				if (!element.getWebPagesForWpLastModifier().isEmpty())
					log.error("ATTENZIONE: - stai cercando di eliminare un utente modifier di " + element.getWebPagesForWpLastModifier().size() + " pagine");
				else
					cms.deleteUser(element);
			}
		}
		
		dbUsers.clear();
*/
		cms.saveAllUser(fb.getUsers());
		
		/*for (Iterator<Users> iter = fb.getUsers().iterator(); iter.hasNext();) {
			Users element = (Users) iter.next();
			cms.saveUser(element);
		}*/
	//	dbUsers.addAll(fb.getUsers());
		//huos.putObject(IKeys.CMS_USERS, fb.getUsers());
	}

}
