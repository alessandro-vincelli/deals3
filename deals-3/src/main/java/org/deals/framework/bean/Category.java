package org.deals.framework.bean;

import java.util.Iterator;
import java.util.List;

import org.deals.framework.core.WebSite;

public class Category extends WebPage {
	
	private static final long serialVersionUID = -4518292037522387663L;

	public Category() {}
	
	public List<WebPage> getSections() {
		return getParents(PageType.SECTION());
	}
	
	public List<WebPage> getItems() {
		return getChildren(PageType.ITEM());
	}
	
	public List<APagePage> getSectionsAssoc() {
		return getParentsAssoc(PageType.SECTION());
	}
	
	public List<APagePage> getItemsAssoc() {
		return getChildrenAssoc(PageType.ITEM());
	}
	

	@Override
	protected String getChildrenXML() {
		StringBuffer sb = new StringBuffer();
		sb.append("<items>");
		for (Iterator<WebPage> iter = getItems().iterator(); iter.hasNext();) {
			WebPage page = iter.next();
			page = WebSite.filterPage(page, true);
			if (page!=null)	sb.append(page.toXML("child-item"));			
		}
		sb.append("</items>");
		return sb.toString();
	}
	
	
	
	
	
}
