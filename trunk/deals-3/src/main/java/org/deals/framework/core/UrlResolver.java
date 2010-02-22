package org.deals.framework.core;

import java.util.HashMap;

import org.deals.framework.bean.PageType;

public class UrlResolver {

	/**
	 * Restituisce una hashMap con la coppia tipo/id 
	 * @param url url da risolvere
	 * @return una hashMap con la coppia tipo/id
	 */
	public static HashMap<Object,Object> resolveUrl(String url) {
		int slashIndex = url.lastIndexOf("/")+1;
		int pointIndex = url.lastIndexOf('.');
		String number = url.substring(slashIndex, pointIndex);
		int pageId = Integer.parseInt(number);
		HashMap<Object,Object> hm = new HashMap<Object,Object>();
		
		if (url.indexOf("section")>-1) {		
		  hm.put(PageType.SECTION(), pageId);
		} else
		if (url.indexOf("category")>-1) {		
		  hm.put(PageType.CATEGORY(), pageId);
		} else
		if (url.indexOf("item")>-1) {		
		  hm.put(PageType.ITEM(), pageId);
		}
		int idx = -1;
		if ((idx = url.indexOf("?"))>-1) {
			hm.put("queryString", url.substring(idx+1));
		}		
		return hm;
	}
	
	
}
