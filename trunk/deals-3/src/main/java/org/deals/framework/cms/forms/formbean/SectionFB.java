package org.deals.framework.cms.forms.formbean;

import java.util.List;

import org.deals.framework.bean.PageState;
import org.deals.framework.bean.Template;
import org.deals.framework.bean.UserProfile;
import org.deals.framework.bean.WebPage;

public class SectionFB {

	private WebPage bean;

	private boolean checkChangeAuthor = false; // 

	private boolean sectionNoexpiration = false; // se data scadenza == null true

	public SectionFB(WebPage bean) {
		this.bean = bean;
		if (bean.getWpAuthor() != null) {
			if (!bean.getWpAuthor().equals("")) {
				checkChangeAuthor = true;
			}
		}
		if (bean.getWpExpire() == null)
			sectionNoexpiration = true;
	}

	public WebPage getBean() {
		return bean;
	}

	public void setBean(WebPage bean) {
		this.bean = bean;
	}

	public boolean isCheckChangeAuthor() {
		return checkChangeAuthor;
	}

	public void setCheckChangeAuthor(boolean checkChangeAuthor) {
		this.checkChangeAuthor = checkChangeAuthor;
	}

	public boolean isSectionNoexpiration() {
		return sectionNoexpiration;
	}

	public void setSectionNoexpiration(boolean sectionNoexpiration) {
		this.sectionNoexpiration = sectionNoexpiration;
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////

	private List<Template> templateSL;

	private List<PageState> pageStateSL;

	public List<PageState> getPageStateSL() {
		return pageStateSL;
	}

	public void setPageStateSL(List<PageState> pageStateSL) {
		this.pageStateSL = pageStateSL;
	}

	public List<Template> getTemplateSL() {
		return templateSL;
	}

	public void setTemplateSL(List<Template> templateSL) {
		this.templateSL = templateSL;
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////

	private List<UserProfile> profiles;	

	public List<UserProfile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<UserProfile> profiles) {
		this.profiles = profiles;
	}

	
	public void addProfile(UserProfile up) {
		profiles.add(up);
	}
	
}
