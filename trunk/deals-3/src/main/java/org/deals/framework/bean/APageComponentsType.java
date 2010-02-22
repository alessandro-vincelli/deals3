package org.deals.framework.bean;

import java.util.Set;

import org.deals.framework.factory.APageComponentsTypeFactory;

// Generated May 22, 2007 5:27:17 PM by Hibernate Tools 3.2.0.beta8

/**
 * APageComponentsType generated by hbm2java
 */
public class APageComponentsType extends AbstractAPageComponentsType implements java.io.Serializable {

	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int PAGE_IMAGE_ID = 1;
	public static int THUMBNAIL_ID = 2;
	public static int PAGE_LINK_ID = 3;
	public static int GENERIC_PAGE_IMAGE_ID = 4;
	public static int WEB_RESOURCE_ID = 5;
	public static int RESOURCE_LINK_ID = 6;
	public static int PHOTOGALLERY_IMAGE = 7;
	
	//	private static final long serialVersionUID = -8911202501574976111L;

	
	// Constructors
	/** default constructor */
	public APageComponentsType() {
	}

	public APageComponentsType(Integer apctId, String apctName, String apctDescri, Set<APageComponents> APageComponentses) {
		super(apctId, apctName, apctDescri, APageComponentses);
	}

	public APageComponentsType(Integer apctId, String apctName) {
		super(apctId, apctName);
	}
	
	public static APageComponentsType PAGE_IMAGE() {
	   	return APageComponentsTypeFactory.getAPageComponentsType(PAGE_IMAGE_ID);
	}
	
	public static APageComponentsType THUMBNAIL() {
	   	return APageComponentsTypeFactory.getAPageComponentsType(THUMBNAIL_ID);
	}
	
	public static APageComponentsType PAGE_LINK() {
	   	return APageComponentsTypeFactory.getAPageComponentsType(PAGE_LINK_ID);
	}
	
	public static APageComponentsType GENERIC_PAGE_IMAGE() {
	   	return APageComponentsTypeFactory.getAPageComponentsType(GENERIC_PAGE_IMAGE_ID);
	}
	
	public static APageComponentsType WEB_RESOURCE() {
	   	return APageComponentsTypeFactory.getAPageComponentsType(WEB_RESOURCE_ID);
	}
	
	public static APageComponentsType RESOURCE_LINK() {
	   	return APageComponentsTypeFactory.getAPageComponentsType(RESOURCE_LINK_ID);
	}
	
	public static APageComponentsType PHOTOGALLERY_IMAGE() {
	   	return APageComponentsTypeFactory.getAPageComponentsType(PHOTOGALLERY_IMAGE);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj==null) return false;
		if (obj==this) return true;
		if (!(obj instanceof APageComponentsType)) return false;
		APageComponentsType apc = (APageComponentsType)obj;
		return (apc.getApctId().equals(getApctId()));
	}

	
	
	
}
