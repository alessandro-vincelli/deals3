package org.deals.framework.cms.forms.manager;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.forms.binding.Binding;
import org.apache.cocoon.forms.binding.BindingException;
import org.apache.cocoon.forms.formmodel.WidgetState;
import org.apache.log4j.Logger;
import org.deals.framework.cms.forms.formbean.SectionBinder;
import org.deals.framework.cms.forms.formbean.SectionFB;
import org.deals.framework.cms.forms.wrapper.SectionFW;
import org.deals.framework.core.WebCMS;

public class SectionManager {
	private SectionBinder as;
	private WebCMS cms;
	private Logger log = Logger.getLogger(getClass());
	private SectionFB fb;

	public SectionFW load(SectionFW f, Request request, Binding binding){
		as = new SectionBinder(request.getSession(), cms);
		try {
			int pageid = -1;

			try {
				if (request.getParameter("pageid") != null) {
					if (!request.getParameter("pageid").equals("")) {
						pageid = Integer.parseInt(request.getParameter("pageid"));
					}
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			// 
			if (pageid != -1) {
				fb = as.getFormBean(pageid);
			} else {
				fb = as.newFormBean();
			}

			f.load(fb);

			f.getSectionTemplate().setSelectionList(fb.getTemplateSL(), "teId", "teName");
			f.getSectionState().setSelectionList(fb.getPageStateSL(), "psId", "psName");

			f.getSectionnodelete().setState(WidgetState.INVISIBLE);
			f.getSectiondelete().setState(WidgetState.INVISIBLE);
			// Attivo i pulsanti per la cancellazione se richiesto
			if (request.getParameter("delete") != null) {
				if (!request.getParameter("delete").equals("")) {
					f.getSectionsubmit().setState(WidgetState.INVISIBLE);
					f.getSectionnodelete().setState(WidgetState.ACTIVE);
					f.getSectiondelete().setState(WidgetState.ACTIVE);
				}
			}

		} catch (BindingException e) {
			log.error("ERROR on binding " + e.getMessage());
			e.printStackTrace();
		}
		return f;
	}

	public SectionFW persist(SectionFW f, Request request, Binding binding){
		log.debug("_________________________________Peristing Section ");
		try {
			if(f.getAttribute("deleteMe")!= null && f.getAttribute("deleteMe").equals(true)){
				as.deleteFormBean(fb);
			}
			else{
				f.save(fb);
				as.saveFormBean(fb, f);
				f.setAttribute("pageid", fb.getBean().getWpId());
			}

		} catch (BindingException e) {
			log.error("ERROR ON Persisting modification (Save or Delete) " + e.toString() + e.getStackTrace()[0]);
			e.printStackTrace();
		}
		return f;
	}

	public void setCms(WebCMS cms) {
		this.cms = cms;
	}
}
