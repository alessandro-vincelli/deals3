package org.deals.framework.cms.forms.formbean;

import javax.servlet.http.HttpSession;

import org.deals.framework.bean.Users;
import org.deals.framework.core.IKeys;
import org.deals.framework.core.WebCMS;
import org.deals.framework.hb.HibernateUtiliObjectInHttpSession;

public class DealsUserBinder extends AbstractDealsUserBinder {
//	private Logger log = Logger.getLogger(getClass());

	private WebCMS cms = new WebCMS();

	HibernateUtiliObjectInHttpSession hu = null;

	private Users currentUser = null;

	public DealsUserBinder(HttpSession session) {
		hu = new HibernateUtiliObjectInHttpSession(session);
	}

	@Override
	public DealsUserFB newFormBean() {
		Users user = new Users();
		DealsUserFB fb = new DealsUserFB(user);
		return fb;
	}

	@Override
	public DealsUserFB getFormBean(int id) {
		currentUser = cms.getUser(id);		
		hu.putObject(IKeys.CMS_EDIT_USER, currentUser);
		DealsUserFB fb = new DealsUserFB(currentUser);
		return fb;
	}

}
