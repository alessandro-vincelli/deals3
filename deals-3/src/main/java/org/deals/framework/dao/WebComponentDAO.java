package org.deals.framework.dao;

import java.util.List;

import org.deals.framework.bean.WebComponent;

public interface WebComponentDAO extends GenericDAO<WebComponent, Integer> {

	List<WebComponent> getImages();
	
	List<WebComponent> getWebResources();
	
	List<String> getDistinctMimeType();	
}
