/*
 * Created on Nov 14, 2006
 *
 */
package org.deals.framework.cms.forms.eh;

import org.apache.cocoon.forms.formmodel.Action;
import org.apache.log4j.Logger;
import org.deals.framework.cms.forms.eh.abstracts.AbstractAdminEH;
import org.deals.framework.cms.forms.wrapper.AdminFW;
import org.deals.framework.core.WebSite;

public class AdminEH extends AbstractAdminEH {

	Logger log = Logger.getLogger(getClass());

	WebSite ws;

	@Override
	public void searchreindexOnAction(AdminFW f, Action source) {
		super.searchreindexOnAction(f, source);
		try {
			ws.reindexWebPages();
			f.setLogMessaggesValue("Il sito e' stato correttamente reindicizzato");
		} catch (Exception e) {
			log.error("errore in indicizzazione sito", e);
			f.setLogMessaggesValue(e.getLocalizedMessage());
		}
	}

	public void setWs(WebSite ws) {
		this.ws = ws;
	}

}
