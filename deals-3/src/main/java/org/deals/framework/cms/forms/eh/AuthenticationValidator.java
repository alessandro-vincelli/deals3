package org.deals.framework.cms.forms.eh;

import org.apache.cocoon.forms.formmodel.Field;
import org.apache.cocoon.forms.formmodel.Widget;
import org.apache.cocoon.forms.validation.ValidationError;
import org.apache.cocoon.forms.validation.WidgetValidator;
import org.apache.log4j.Logger;
import org.deals.framework.bean.Users;
import org.deals.framework.core.WebCMS;

public class AuthenticationValidator implements WidgetValidator {
	Logger log = Logger.getLogger(getClass());
	WebCMS cms;

	public boolean validate(Widget widget) {
		if (widget.getForm().getChild("deals_username").getValue() != null) {
			if (widget.getForm().getChild("deals_password").getValue() != null) {
				String username = widget.getForm().getChild("deals_username")
						.getValue().toString();
				String password = widget.getForm().getChild("deals_password")
						.getValue().toString();
				log
						.debug("_______________________________________________FORM AUTH username: "
								+ username);
				log
						.debug("_______________________________________________FORM AUTH password: "
								+ password);

				Users user = cms.checkUser(username, password);

				if (user == null) {
					Field pippo = (Field)widget;
					pippo.setValidationError(new ValidationError("Nome utente o password errati"));
					return false;
				} else {
					try {
						// ConsiagSession.initSession(session);
						// DealsSession cs = new DealsSession(session);
						// cs.setLoggedUser(user);
						// HibernateUtiliObjectInHttpSession huos = new
						// HibernateUtiliObjectInHttpSession(session);
						// huos.putObject(IKeys.CMS_LOGGED_USER, user);
						// session.setAttribute(IKeys.CMS_NP_LOGGED_USER, user);

						// metto in sessione il ruolo
						// session.setAttribute("ruolo",
						// user.getProfile().getUpName());

						// gestione menu per profili
						// session.setAttribute(IKeys.CMS_NP_MENU_SECTION,
						// cms.getCmsSection());
	/*					if (user.getProfile().getUpName().equals("admin")) {
							Collection<CmsSection> menu = new ArrayList<CmsSection>(
									cms.getCmsSection());
							huos.putObject(IKeys.CMS_NP_MENU_SECTION, menu);
						}
*/
						// Questo attributo è fondamentale perchè serve al test
						// di autenticazione della sitemap. il test su user non
						// funziona, o va verificato
						// session.setAttribute("ruolo", "temp");
						return true;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return false;
	}

	public void setCms(WebCMS cms) {
		this.cms = cms;
	}

}
