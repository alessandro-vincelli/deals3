package org.deals.framework.auth;

import java.util.Map;

import org.apache.cocoon.auth.ApplicationManager;
import org.apache.cocoon.auth.AuthenticationException;
import org.apache.cocoon.auth.User;
import org.apache.cocoon.auth.impl.AbstractSecurityHandler;
import org.apache.cocoon.auth.impl.StandardUser;
import org.deals.framework.bean.Users;
import org.deals.framework.core.IKeys;
import org.deals.framework.core.WebCMS;

public class DealsSecurityHandler extends AbstractSecurityHandler {

     
    protected WebCMS cms;
    
    /**
     * @see org.apache.cocoon.auth.SecurityHandler#login(java.util.Map)
     */
    public User login(final Map loginContext)
    throws AuthenticationException {
        // get user name and password
        final String name = (String)loginContext.get(ApplicationManager.LOGIN_CONTEXT_USERNAME_KEY);
        if ( name == null ) {
            throw new AuthenticationException("Required user name property is missing for login.");            
        }
        final String password = (String)loginContext.get(ApplicationManager.LOGIN_CONTEXT_PASSWORD_KEY);
        // compare password
        Users us = cms.checkUser(name, password);
        if ( cms.checkUser(name, password) == null ) {
            return null;
        }
        final User user = new StandardUser(name);
        // check for additional attributes
        final String prefix = name + '.';
        user.setAttribute("firstName", us.getUsFirstname());
        user.setAttribute("lastName", us.getUsSurname());
        user.setAttribute("id", us.getUsId());
        user.setAttribute(IKeys.CMS_LOGGED_USER, us);
        user.setAttribute(IKeys.CMS_LOGGED_ROLE, us.getProfile().getUpName());
        user.setAttribute(IKeys.CMS_LOGGED_MENU_SECTION, cms.getCmsSection());
        user.setAttribute(IKeys.CMS_LOGGED_USER_LANGUAGE, (String)loginContext.get("userlanguage"));
    
        return user;
    }

    /**
     * @see org.apache.cocoon.auth.SecurityHandler#logout(java.util.Map, org.apache.cocoon.auth.User)
     */
    public void logout(final Map logoutContext, final User user) {
        // nothing to do here
    }

	public void setCms(WebCMS cms) {
		this.cms = cms;
	}
}
