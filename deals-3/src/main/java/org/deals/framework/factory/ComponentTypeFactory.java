package org.deals.framework.factory;

import org.deals.framework.bean.ComponentType;

public class ComponentTypeFactory {

	public static ComponentType createPageLinkComponentType() {
		ComponentType ct = new ComponentType();
		ct.setCtId(ComponentType.PAGE_LINK_ID);
		ct.setCtName("page_link");
		return ct;
	}
	
	public static ComponentType createResourceLinkComponentType() {
		ComponentType ct = new ComponentType();
		ct.setCtId(ComponentType.RESOURCE_LINK_ID);
		ct.setCtName("resource_link");
		return ct;
	}
	
	public static ComponentType createContainerComponentType() {
		ComponentType ct = new ComponentType();
		ct.setCtId(ComponentType.CONTAINER_ID);
		ct.setCtName("container");
		return ct;
	}
	
	public static ComponentType createMenuComponentType() {
		ComponentType ct = new ComponentType();
		ct.setCtId(ComponentType.MENU_ID);
		ct.setCtName("menu");
		return ct;
	}
	
	public static ComponentType createMenuItemComponentType() {
		ComponentType ct = new ComponentType();
		ct.setCtId(ComponentType.MENU_ITEM_ID);
		ct.setCtName("menu_item");
		return ct;
	}
	
	public static ComponentType createWebResourceComponentType() {
		ComponentType ct = new ComponentType();
		ct.setCtId(ComponentType.WEB_RESOURCE_ID);
		ct.setCtName("web_resource");
		return ct;
	}
	
	public static ComponentType createPageImageComponentType() {
		ComponentType ct = new ComponentType();
		ct.setCtId(ComponentType.PAGE_IMAGE_ID);
		ct.setCtName("page_image");
		return ct;
	}
	
	public static ComponentType createThumbnailComponentType() {
		ComponentType ct = new ComponentType();
		ct.setCtId(ComponentType.THUMBNAIL_ID);
		ct.setCtName("thumbnail");
		return ct;
	}
	
}
