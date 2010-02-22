package org.deals.framework.core.util;

import javax.servlet.http.HttpSession;

import org.apache.cocoon.auth.User;
import org.deals.framework.bean.Users;
import org.deals.framework.core.IKeys;

public class UtilHttpSession {

	private HttpSession session;

	public Object getObject(String key) throws Exception {
		return session.getAttribute(key);
	}

	// fa class cast exceptio, boh!!!!!!!!!!! sembra come OCM
	@Deprecated
	public Users getloggedUser() {
		return (Users) ((User) session.getAttribute("cauth-user-deals")).getAttribute(IKeys.CMS_LOGGED_USER);
	}
	
	public Integer getloggedUserId() {
		return (Integer) ((User) session.getAttribute("cauth-user-deals")).getAttribute("id");
	}

	public UtilHttpSession(HttpSession session) {
		this.session = session;
	}
}
