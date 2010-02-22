package org.deals.framework.cms.grant;

import java.util.List;

import org.deals.framework.bean.CmsSection;

public class CmsSections {

	/**
	 * Return only the Cms sections authorized for the user
	 * Partially implemented:
	 * 2 catgeory only:
	 * Administrator: sees all Sections
	 * Other: see only WebPage ad Resources authorized
	 * 
	 * 
	 * @param sections
	 * @return
	 */
	public List<CmsSection> filterCmsSection(List<CmsSection> sections){
		//cocoon.session.getAttribute('cauth-user-deals').getAttribute('CMS_LOGGED_MENU_SECTION')
		return null;
	}
}
