package org.deals.framework.util;

import org.apache.commons.lang.StringUtils;

public class TextUtils {

	/**
	 * Effettua un substring senza spezzare le parole contenute nel testo
	 * @param text testo da cui estrarre la sottostringa
	 * @param beginIndex indice nel testo da cui iniziare l'estrazione
	 * @param endIndex indice nel testo a cui terminare l'estrazione
	 * @return sottostringa estratta dal testo senza spezzare le parole
	 */
	public static String intelligentSubstring(String text, int beginIndex, int endIndex) {
		if (text != null) {
		if (endIndex>text.length()) endIndex = text.length();
		
		char carStart = text.charAt(beginIndex);
		while (beginIndex>0 && !StringUtils.isWhitespace(""+carStart)) {
			beginIndex--;
			if (beginIndex<text.length()) carStart = text.charAt(beginIndex);
		}
		
		char carEnd = text.charAt(endIndex-1);
		while (endIndex<text.length() && !StringUtils.isWhitespace(""+carEnd)) {
			endIndex++;
			if (endIndex<text.length())	carEnd = text.charAt(endIndex);
		}
		return text.substring(beginIndex, endIndex);		
		}
		return "";
	}
	
	
	public static String stripHTMLTags(String htmlCode) {
		String result = htmlCode;
		result = htmlCode.replaceAll("<.*>", "");
		return result;
	}
	
	
	public static void main(String[] args) {
		TextUtils ut = new TextUtils();
		ut.stripHTMLTags("<html><body></body></html>code");
	}
	
	
	
}
