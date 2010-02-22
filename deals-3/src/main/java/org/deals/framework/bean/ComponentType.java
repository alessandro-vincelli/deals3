package org.deals.framework.bean;
// Generated by MyEclipse - Hibernate Tools

import java.util.Set;

import org.deals.framework.factory.ComponentTypeFactory;


/**
 * ComponentType generated by MyEclipse - Hibernate Tools
 */
public class ComponentType extends AbstractComponentType implements java.io.Serializable {

	private static final long serialVersionUID = -5664436568318912870L;

	public static int PAGE_LINK_ID = 1;
	public static int RESOURCE_LINK_ID = 2;
	public static int CONTAINER_ID = 3;
	public static int MENU_ID = 4;
	public static int MENU_ITEM_ID = 5;
	public static int WEB_RESOURCE_ID = 6;
	public static int PAGE_IMAGE_ID = 7;
	public static int THUMBNAIL_ID = 8;
	
	
    // Constructors

    /** default constructor */
    public ComponentType() {
    }

	/** minimal constructor */
    public ComponentType(String ctName) {
        super(ctName);        
    }
    
    /** full constructor */
    public ComponentType(String ctName, String ctDescri, Set webComponents) {
        super(ctName, ctDescri, webComponents);        
    }
   
    
    public static ComponentType PAGE_LINK() {
    	return ComponentTypeFactory.createPageLinkComponentType();
    }
    
    public static ComponentType RESOURCE_LINK() {
    	return ComponentTypeFactory.createResourceLinkComponentType();
    }
    
    public static ComponentType CONTAINER() {
    	return ComponentTypeFactory.createContainerComponentType();
    }
    
    public static ComponentType MENU() {
    	return ComponentTypeFactory.createMenuComponentType();
    }
    
    public static ComponentType MENU_ITEM() {
    	return ComponentTypeFactory.createMenuItemComponentType();
    }
    
    public static ComponentType WEB_RESOURCE() {
    	return ComponentTypeFactory.createWebResourceComponentType();
    }
    
    public static ComponentType PAGE_IMAGE() {
    	return ComponentTypeFactory.createPageImageComponentType();
    }
    
    public static ComponentType THUMBNAIL() {
    	return ComponentTypeFactory.createThumbnailComponentType();
    }

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (!(obj instanceof ComponentType)) return false;		
		return ((ComponentType)obj).getCtId().equals(getCtId());
	}
    
    
    
    
}
