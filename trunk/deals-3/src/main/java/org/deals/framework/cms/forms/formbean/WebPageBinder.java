package org.deals.framework.cms.forms.formbean;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.deals.framework.bean.APagePage;
import org.deals.framework.bean.Category;
import org.deals.framework.bean.Section;
import org.deals.framework.bean.WebPage;
import org.deals.framework.cms.DealsSession;
import org.deals.framework.core.WebCMS;
import org.deals.framework.core.util.UtilHttpSession;

public abstract class WebPageBinder {

	protected DealsSession ds;
	protected UtilHttpSession hos;;
	protected WebCMS cms;

	protected WebPageBinder(HttpSession session) {
		ds = new DealsSession(session);
		hos = new UtilHttpSession(session);
	}
	
	
	protected void refreshManagement() {
		if (ds.isSectionSelected()) {
			List<WebPage> sections = cms.getSections(ds.getHttpSession());			
			Section sec = (Section) cms.getSection(ds.getIdSection());
			List<APagePage> categories = sec.getCategoriesAssoc();			
			List<APagePage> items = sec.getItemsAssoc();
		}
		if (ds.isCategorySelected()) {
			Category cat = (Category) cms.getCategory(ds.getIdCategory());
			List<APagePage> items = cat.getItemsAssoc();
		}		
	}
	
	
	protected abstract void refreshPriority();
	
	
	protected int findMaxWeight(Set assocs, WebPage brother) {
		int max = 0;
		for (Iterator iter = assocs.iterator(); iter.hasNext();) {
			APagePage element = (APagePage) iter.next();			
			if (element.getAppWeight()>max && element.getWebPageByAppChild().getPageType().equals(brother.getPageType()))
				max = element.getAppWeight(); 
		}
		return max;
	}	
	
	protected int findMaxWeight(Set assocs) {
		int max = 0;
		for (Iterator iter = assocs.iterator(); iter.hasNext();) {
			APagePage element = (APagePage) iter.next();			
			if (element.getAppWeight()>max)
				max = element.getAppWeight(); 
		}
		return max;
	}	
	
	public void setCms(WebCMS cms) {
		this.cms = cms;
	}

	
}
