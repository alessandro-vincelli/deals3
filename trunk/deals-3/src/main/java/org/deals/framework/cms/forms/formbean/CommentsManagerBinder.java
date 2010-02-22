package org.deals.framework.cms.forms.formbean;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.deals.framework.cms.DealsSession;
import org.deals.framework.core.WebCMS;

public class CommentsManagerBinder {

	//private HibernateUtiliObjectInHttpSession huos;

	private Logger log = Logger.getLogger(getClass()); 
	
	DealsSession ds;

	private WebCMS cms;

	CommentsManagerFB fb;

	public CommentsManagerBinder(HttpSession session, WebCMS cms) {
		//huos = new HibernateUtiliObjectInHttpSession(session);
		ds = new DealsSession(session);
		fb = new CommentsManagerFB();
		this.cms = cms;
	}

	public CommentsManagerFB getFormBean() {
		// chiede al CMS la lista di tutte le web components presenti
		// e le restituisce
		fb.setComments(cms.getComments());
		//huos.putObject(IKeys.CMS_EDIT_PROFILES_PROFILES, fb.getProfiles());
		return fb;
	}

	public void saveFormBean(CommentsManagerFB fb) {
		cms.saveAllComments(fb.getComments());
		/*for (Iterator<Comments> iter = fb.getComments().iterator(); iter.hasNext();) {
			Comments element = (Comments) iter.next();
		//	if (element.getUpId() == null) {
				cms.saveComment(element);
			//}

		}*/
		//dbProfiles.addAll(fb.getProfiles());
	}

}
