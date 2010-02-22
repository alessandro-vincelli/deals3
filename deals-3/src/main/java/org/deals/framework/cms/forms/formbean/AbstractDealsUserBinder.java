package org.deals.framework.cms.forms.formbean;

import javax.servlet.http.HttpSession;

public abstract class AbstractDealsUserBinder {


	public abstract DealsUserFB newFormBean();
	public abstract DealsUserFB getFormBean(int id) ;
	
	public static AbstractDealsUserBinder getBinder(HttpSession session){
		return new DealsUserBinder(session);
	}
	
	
}
