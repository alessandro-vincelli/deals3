package org.deals.framework.factory;

import org.deals.framework.bean.PageType;

public class PageTypeFactory {

	
	public static PageType createSectionPageType() {
		PageType pt = new PageType(null, "section","",null,null);
		pt.setPtId(PageType.SECTION_ID);
		return pt;  
	}
	
	public static PageType createCategoryPageType() {
		PageType pt = new PageType(createSectionPageType(), "category","",null,null); 
		pt.setPtId(PageType.CATEGORY_ID);
		return pt;  
	}

	public static PageType createItemPageType() {
		PageType pt = new PageType(createCategoryPageType(), "item","",null,null); 
		pt.setPtId(PageType.ITEM_ID);
		return pt;  
	}
	
}
