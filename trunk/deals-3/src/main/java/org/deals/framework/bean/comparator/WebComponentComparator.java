package org.deals.framework.bean.comparator;

import java.util.Comparator;

import org.deals.framework.bean.WebComponent;

public class WebComponentComparator implements Comparator<WebComponent> {

	private static final boolean ORDER_ASC = true;
//	private static final boolean ORDER_DESC = false;
	
	public static final String ID_COLUMN = "wcId";
	public static final String TITLE_COLUMN = "rlTitle";
	public static final String CONTENT_COLUMN = "rlContent";
	public static final String TARGET_COLUMN = "rlTarget";
	public static final String HREF_COLUMN = "rlHref";
	
	public static final String NAME_COLUMN = "wrName";
	public static final String DESCRIPTION_COLUMN = "wrDescription";
	public static final String MIMETYPE_COLUMN = "wrMimetype";
	public static final String ALT_COLUMN = "wrAlt";
	
	public static final String COMPONENT_TYPE_COLUMN = "componentType";    
	
	
	private String orderByField;
	@SuppressWarnings("unused")
	private boolean orderAsc;
	private int multiplier = 1;
	
	/**
	 * Costruttore di default, ordina per id ascendente
	 */
	public WebComponentComparator() {
		this(ID_COLUMN, ORDER_ASC);
	}
	
	public WebComponentComparator(String field, boolean orderAsc) {
		this.orderByField = field;
		this.orderAsc = orderAsc;
		if (!orderAsc) 
			multiplier = -1;
	}
		
	public int compare(WebComponent o1, WebComponent o2) {
		if (o1 == null) return -1;
		if (o2 == null) return 1;		
		if (orderByField.equals(TITLE_COLUMN)) {
			return o1.getRlTitle().compareTo(o2.getRlTitle())*multiplier;
		}
		if (orderByField.equals(CONTENT_COLUMN)) {
			return o1.getRlContent().compareTo(o2.getRlContent())*multiplier;
		}
		if (orderByField.equals(TARGET_COLUMN)) {
			return o1.getRlTarget().compareTo(o2.getRlTarget())*multiplier;
		}
		if (orderByField.equals(HREF_COLUMN)) {
			return o1.getRlHref().compareTo(o2.getRlHref())*multiplier;
		}
		if (orderByField.equals(NAME_COLUMN)) {
			return o1.getWrName().compareTo(o2.getWrName())*multiplier;
		}
		if (orderByField.equals(DESCRIPTION_COLUMN)) {
			return o1.getWrDescription().compareTo(o2.getWrDescription())*multiplier;
		}
		if (orderByField.equals(MIMETYPE_COLUMN)) {
			return o1.getWrMimetype().compareTo(o2.getWrMimetype())*multiplier;
		}
		if (orderByField.equals(ALT_COLUMN)) {
			return o1.getWrAlt().compareTo(o2.getWrAlt())*multiplier;
		}
		return o1.getWcId().compareTo(o2.getWcId())*multiplier;
	}

}
