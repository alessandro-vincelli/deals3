package org.deals.framework.bean;
// Generated by MyEclipse - Hibernate Tools

import java.util.Set;

import org.deals.framework.factory.PageTypeFactory;


/**
 * PageType generated by MyEclipse - Hibernate Tools
 */
public class PageType extends AbstractPageType implements java.io.Serializable {

	private static final long serialVersionUID = 1416440528187132143L;
	
    public static final int SECTION_ID = 1;
    public static final int CATEGORY_ID = 2;
    public static final int ITEM_ID = 3;
	
    // Constructors

    /** default constructor */
    public PageType() {
    }

    
    /** full constructor */
    public PageType(PageType pageType, String ptName, String ptDescription, Set pageTypes, Set webPages) {
        super(pageType, ptName, ptDescription, pageTypes, webPages);        
    }

    
    public static final PageType SECTION() {
    	return PageTypeFactory.createSectionPageType();
    }
    
    public static final PageType CATEGORY() {
    	return PageTypeFactory.createCategoryPageType();
    }
    
    public static final PageType ITEM() {
    	return PageTypeFactory.createItemPageType();
    }
    

	@Override
	public boolean equals(Object obj) {
		if (obj==null) return false;
		if (this == obj) return true;
		if (!(obj instanceof PageType)) return false;
		PageType pt = (PageType)obj;
		return pt.getPtId().equals(getPtId());
	}
    
    
    
    
    
    
    
   
}