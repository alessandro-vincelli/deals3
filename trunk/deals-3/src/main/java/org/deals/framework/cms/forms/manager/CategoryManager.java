package org.deals.framework.cms.forms.manager;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.forms.binding.Binding;
import org.apache.cocoon.forms.binding.BindingException;
import org.apache.cocoon.forms.formmodel.WidgetState;
import org.apache.log4j.Logger;
import org.deals.framework.cms.forms.formbean.CategoryBinder;
import org.deals.framework.cms.forms.formbean.CategoryFB;
import org.deals.framework.cms.forms.wrapper.CategoryFW;
import org.deals.framework.core.WebCMS;

public class CategoryManager {
	private CategoryBinder ac;
	private WebCMS cms;
	private Logger log = Logger.getLogger(getClass());
	private CategoryFB fb;

	public CategoryFW load(CategoryFW f, Request request, Binding binding){
		ac = new CategoryBinder(request.getSession(), cms);
		try {
			int pageid = -1;

			if (request.getParameter("pageid") != null) {
				if (!request.getParameter("pageid").equals("")) {
					pageid = Integer.parseInt(request.getParameter("pageid"));
				}
			}
			// 
			if (pageid != -1) {
				fb = ac.getFormBean(pageid);
			} else {
				fb = ac.newFormBean();
			}
			f.load(fb);

			f.getCategoryTemplate().setSelectionList(fb.getTemplateSL(), "teId", "teName");
			f.getCategoryState().setSelectionList(fb.getPageStateSL(), "psId", "psName");

			f.getCategorynodelete().setState(WidgetState.INVISIBLE);
			f.getCategorydelete().setState(WidgetState.INVISIBLE);
			// Attivo i pulsnati per la cancellazione se richiesto
			if (request.getParameter("delete") != null) {
				if (!request.getParameter("delete").equals("")) {
					f.getCategorysubmit().setState(WidgetState.INVISIBLE);
					f.getCategorynodelete().setState(WidgetState.ACTIVE);
					f.getCategorydelete().setState(WidgetState.ACTIVE);
				}
			}

		} catch (BindingException e) {
			log.error("ERROR on binding " + e.getMessage());
			e.printStackTrace();
		}
		return f;
	}

	public CategoryFW persist(CategoryFW f, Request request, Binding binding){
		log.debug("_________________________________Peristing Category ");
		try {
			if(f.getAttribute("deleteMe")!= null && f.getAttribute("deleteMe").equals(true)){
				ac.deleteFormBean(fb);
			}
			else
			{
			f.save(fb);
			f.setAttribute("pageid", fb.getBean().getWpId());
			ac.saveFormBean(fb, f);
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
