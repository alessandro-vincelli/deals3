package org.deals.framework.bean;

import org.deals.framework.util.DateUtils;

public class ResourceLink extends WebComponent {
	
	private static final long serialVersionUID = 5268639062933564248L;

	public ResourceLink() {
	}

	@Override
	public String toXML() {
		StringBuffer sb = new StringBuffer();
		sb.append("<resource-link id=\"" + getWcId() + "\">");
		sb.append("<title>" + (getRlTitle()==null?"":getRlTitle()) + "</title>");
		sb.append("<content>" + (getRlContent()==null?"":getRlContent())+ "</content>");
		sb.append("<target>" + (getRlTarget()==null?"":getRlTarget()) + "</target>");
		sb.append("<href>" + getRlHref() + "</href>");
		sb.append("<insert-date>" + (getWcInsertDate()==null?"":DateUtils.toDateStringCustom(getWcInsertDate())) + "</insert-date>");
		sb.append("</resource-link>");
		return sb.toString();
	}

	@Override
	public String toXML2() {		
		return toXML();
	}
	
	
	
	
	
}
