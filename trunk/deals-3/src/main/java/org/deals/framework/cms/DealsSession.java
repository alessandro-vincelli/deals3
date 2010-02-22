package org.deals.framework.cms;

import javax.servlet.http.HttpSession;

import org.deals.framework.bean.Users;
import org.deals.framework.core.IKeys;

public class DealsSession {


	private HttpSession _session;
	
	public DealsSession(HttpSession session) {
		_session = session;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	/// METODI PER IL WRAPPING DELLA SESSIONE                      ////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private String logged_user_key = "logged-user";
	
	public void setLoggedUser(Users user) {
		_session.setAttribute(IKeys.CMS_LOGGED_USER, user);
	}
	
	public Users getLoggedUser() {
		return (Users)_session.getAttribute(IKeys.CMS_LOGGED_USER);
	}
	
	public void removeLoggedUser() {
		_session.removeAttribute(IKeys.CMS_LOGGED_USER);
	}
	
	//----------------------------------------------------------
	
		
	public void setIdSectionSelected(Integer id) {
		_session.setAttribute("idsection", id);
		_session.removeAttribute("idcategory");
	}

	public void setIdCategorySelected(Integer id) {
		_session.setAttribute("idcategory", id);
	}
	
	public boolean isSectionSelected() {
		return (_session.getAttribute("idsection")!=null);
	}
	
	public void deselectSection() {
		_session.setAttribute("idsection", null);
	}
	
	public boolean isCategorySelected() {
		return (_session.getAttribute("idcategory")!=null);
	}
	
	public void deselectCategory() {
		_session.setAttribute("idcategory", null);
	}
	
	public Integer getIdSection() {
		return (Integer)_session.getAttribute("idsection");
	}
	
	public Integer getIdCategory() {
		return (Integer)_session.getAttribute("idcategory");
	}
	
	public HttpSession getHttpSession(){
		return _session;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	
}
