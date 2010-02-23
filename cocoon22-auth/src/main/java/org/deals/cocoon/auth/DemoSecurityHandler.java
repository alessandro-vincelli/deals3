package org.deals.cocoon.auth;

import java.util.Map;

import org.apache.cocoon.auth.ApplicationManager;
import org.apache.cocoon.auth.AuthenticationException;
import org.apache.cocoon.auth.User;
import org.apache.cocoon.auth.impl.AbstractSecurityHandler;
import org.apache.cocoon.auth.impl.StandardUser;

/**
 * @author Alessandro Vincelli
 * @email a.vincelli@gmail.com Created on Aug 15, 2008
 */
public class DemoSecurityHandler extends AbstractSecurityHandler{

	/**
	 * @see org.apache.cocoon.auth.SecurityHandler#login(java.util.Map)
	 */
	public User login(final Map loginContext) throws AuthenticationException {
		// get user name and password
		final String name = (String) loginContext.get(ApplicationManager.LOGIN_CONTEXT_USERNAME_KEY);
		if (name == null) {
			throw new AuthenticationException("Required user name property is missing for login.");
		}
		final String password = (String) loginContext.get(ApplicationManager.LOGIN_CONTEXT_PASSWORD_KEY);
		// compare password
		if (!(name.equals("dante") && password.equals("cruciani"))) {
			return null;
		}
		final User user = new StandardUser(name);
		// setting additional attributes
		user.setAttribute("firstName", "Dante");
		user.setAttribute("lastName", "Cruciani");
		return user;
	}

	/**
	 * @see org.apache.cocoon.auth.SecurityHandler#logout(java.util.Map,
	 *      org.apache.cocoon.auth.User)
	 */
	public void logout(final Map logoutContext, final User user) {
		// nothing to do here
	}
}
