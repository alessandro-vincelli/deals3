package org.deals.framework.bean;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.deals.framework.core.WebSite;

public class Section extends WebPage {
	
	private static final long serialVersionUID = 8170387202850476692L;

	public Section() {
		super();
	}
		
	// usa la getChildren(PageType)
	
	public List<Category> getCategories() {
		Vector<Category> children = new Vector<Category>();
		List<APagePage> assoc = getChildrenAssoc(PageType.CATEGORY());
		for (Iterator<APagePage> iter = assoc.iterator(); iter.hasNext();) {		
			APagePage obj = iter.next();
			if(obj!=null){
				children.add((Category)obj.getWebPageByAppChild());	
			}
			
		}
		return children;
	}
	
	public List<WebPage> getItems() {
		return getChildren(PageType.ITEM());
	}
		
	public List<APagePage> getCategoriesAssoc() {
		return getChildrenAssoc(PageType.CATEGORY());
	}
	
	public List<APagePage> getItemsAssoc() {
		return getChildrenAssoc(PageType.ITEM());
	}


	@Override
	protected String getChildrenXML() {
		StringBuffer sb = new StringBuffer();
		sb.append("<categories>");
		for (Iterator<Category> iter = getCategories().iterator(); iter.hasNext();) {
			WebPage page = iter.next();
			page = WebSite.filterPage(page, true);
			if (page!=null)	sb.append(page.toXML("child-category"));			
		}
		sb.append("</categories>");
		
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
