package org.deals.framework.util;

import org.apache.commons.lang.StringUtils;

public class SafeUtil {

	// TODO utilizzare il più possibile la safe util nelle toString e nelle concatenazioni
	
	private static final String TEXTAREA_BUG = "<html><body/></html>";


	public static String safeToString(Object obj) {
		return safeToString(obj,"");
	}
	
	public static String safeToString(Object obj, String nullString) {
		if (obj == null) return nullString;
		else return obj.toString();
	}
	
	
	public static boolean isNullOrEmpty(String string) {
		if (string==null) return true;
		if (string.equals("")) return true;		
		if (StringUtils.deleteWhitespace(string).equals(TEXTAREA_BUG)) return true;
		return false;
	}
	
}
