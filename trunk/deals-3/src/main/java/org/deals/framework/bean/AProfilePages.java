package org.deals.framework.bean;

public class AProfilePages extends AbstractAProfilePages {

	private static final long serialVersionUID = 5529950875129099245L;

	public AProfilePages() {
		super();
	}

	public AProfilePages(WebPage webPage, UserProfile userProfile, Integer aprLevel) {
		super(webPage, userProfile, aprLevel);
	}

	public AProfilePages(WebPage webPage, UserProfile userProfile) {
		super(webPage, userProfile);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj==null) return false;
		if (obj==this) return true;
		if (getWebPage().getWpId().equals(((AProfilePages)obj).getWebPage().getWpId()) &&
				getProfile().getUpId().equals(((AProfilePages)obj).getProfile().getUpId())) {
			return true;
		} else
			return false;
	}
	
	
	
	
	
}
