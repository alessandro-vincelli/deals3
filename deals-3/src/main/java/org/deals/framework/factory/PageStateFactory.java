package org.deals.framework.factory;

import org.deals.framework.bean.PageState;

public class PageStateFactory {

	public static PageState createDraftPageState() {
		PageState ps = new PageState();
		ps.setPsId(PageState.DRAFT_ID);
		ps.setPsName("bozza");
		return ps;		
	}

	public static PageState createPublishedPageState() {
		PageState ps = new PageState();
		ps.setPsId(PageState.PUBLISHED_ID);
		ps.setPsName("pubblicato");
		return ps;		
	}

	public static PageState createExpiredPageState() {
		PageState ps = new PageState();
		ps.setPsId(PageState.EXPIRED_ID);
		ps.setPsName("scaduto");
		return ps;		
	}

	public static PageState createDeletedPageState() {
		PageState ps = new PageState();
		ps.setPsId(PageState.DELETED_ID);
		ps.setPsName("eliminato");
		return ps;		
	}
	
}
