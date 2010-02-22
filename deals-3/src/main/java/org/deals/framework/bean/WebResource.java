package org.deals.framework.bean;

import org.deals.framework.core.MimeType;
import org.deals.framework.core.UrlCreator;
import org.deals.framework.util.DateUtils;

public class WebResource extends WebComponent {

	private static final long serialVersionUID = -1080930343535405721L;

	public WebResource() {
	}
	
	/**
	 * Restituisce il path temporaneo su cui salvare la risorsa in attesa
	 * del salvataggio
	 * @return il path temporaneo su cui salvare la risorsa in attesa
	 * del salvataggio
	 */
	public String getWebResourceTempPathOnDisk() {
		return UrlCreator.createTempFilePathOnDisk(getWrName(), MimeType.getMimeType(getWrMimetype()));
	}


	/**
	 * Restituisce il path su file system della risorsa
	 * @return path su file system della risorsa se la risorsa è salvata (possiede un id) altrimenti il path temporaneo
	 */
	public String getWebResourcePathOnDisk() {
		if ((Integer)getWcId() != null) {
			return UrlCreator.createFilePathOnDisk(getWcId(), getWrName(), MimeType.getMimeType(getWrMimetype()));			
		} else {
			return getWebResourceTempPathOnDisk();
		}
	}
	
	
	/**
	 * Restituisce il path temporaneo sul sito da cui recuperare la risorsa
	 * @return il path temporaneo sul sito da cui recuperare la risorsa
	 */
	public String getWebResourceTempPathOnSite() {
		return UrlCreator.createTempFilePathOnSite(getWrName(), MimeType.getMimeType(getWrMimetype()));
	}


	/**
	 * Restituisce il path sul sito della risorsa
	 * @return path sul sito della risorsa se la risorsa è salvata (possiede un id) altrimenti il path temporaneo
	 */
	public String getWebResourcePathOnSite() {
		if ((Integer)getWcId() != null) {
			return UrlCreator.createFilePathOnRepo(getWcId(), getWrName(), MimeType.getMimeType(getWrMimetype()));			
		} else {
			return getWebResourceTempPathOnSite();
		}
	}
	
	@Override
	public String toXML() {
		StringBuffer sb = new StringBuffer();
		sb.append("<web-resource id=\"" + getWcId() + "\">");
		sb.append("<name>" + getWrName() + "</name>");
		sb.append("<description>" + (getWrDescription()==null?"":getWrDescription())+ "</description>");
		sb.append("<mime-type>" + getWrMimetype() + "</mime-type>");
		sb.append("<url>" + getWebResourcePathOnSite() + "</url>");
		sb.append("<alt>" + (getWrAlt()==null?"":getWrAlt()) + "</alt>");
		sb.append("<insert-date>" + (getWcInsertDate()==null?"":DateUtils.toDateStringCustom(getWcInsertDate())) + "</insert-date>");
		sb.append("</web-resource>");
		return sb.toString();
	}

	@Override
	public String toXML2() {
		StringBuffer sb = new StringBuffer();
		sb.append("<name>" + getWrName() + "</name>");
		sb.append("<description_component>" + (getWrDescription()==null?"":getWrDescription())+ "</description_component>");
		sb.append("<mime-type>" + getWrMimetype() + "</mime-type>");
		sb.append("<url>" + getWebResourcePathOnSite() + "</url>");
		sb.append("<alt>" + (getWrAlt()==null?"":getWrAlt()) + "</alt>");
		sb.append("<insert-date>" + (getWcInsertDate()==null?"":DateUtils.toDateStringCustom(getWcInsertDate())) + "</insert-date>");
		return sb.toString();
	}

	
}
