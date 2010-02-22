package org.deals.framework.bean;

import java.util.List;

public class Item extends WebPage {

	private static final long serialVersionUID = -1443078873130385692L;

	public Item() {		
	}
		
	// usa la getParents(PageType)
	public List<WebPage> getSections() {
		return getParents(PageType.SECTION());
	}

	public List<WebPage> getCategories() {
		return getParents(PageType.CATEGORY());
	}
	
	public List<APagePage> getSectionsAssoc() {
		return getParentsAssoc(PageType.SECTION());
	}

	public List<APagePage> getCategoriesAssoc() {
		return getParentsAssoc(PageType.CATEGORY());
	}	
	
}
