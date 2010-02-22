package org.deals.framework.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.deals.framework.bean.WebPage;
import org.deals.framework.dao.WebPageDAO;
import org.deals.framework.util.DateUtils;
import org.deals.framework.util.SafeUtil;

public class RssGenerator {

	private WebPageDAO wpDao;

	private List<Node> pages;

	public void setWpDao(WebPageDAO wpDao) {
		this.wpDao = wpDao;
	}

	public String getXMLList() {
		createListPage();
		StringBuffer xml = new StringBuffer();
		xml.append("<pages>");
		for (Iterator<Node> iterator = pages.iterator(); iterator.hasNext();) {
			Node node = iterator.next();
			xml.append(node.toXML());
		}
		xml.append("</pages>");
		return xml.toString();
	}

	private void createListPage() {
		pages = new ArrayList<Node>();
		List<WebPage> webPages = wpDao.findAll();
		for (Iterator<WebPage> iter = webPages.iterator(); iter.hasNext();) {
			WebPage page = iter.next();
			if (WebSite.filterPage(page, true) != null) {
				Node node = new Node(page);
				pages.add(node);
			}
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////

	class Node {

		private WebPage page;

		private boolean selected = false;

		public Node(WebPage page) {
			this.page = page;

		}

		public String toXML() {
			if (page != null) {
				StringBuffer xml = new StringBuffer();
				xml.append("<" + page.getPageType().getPtName() + " id=\"" + page.getWpId() + "\"" + " selected=\"" + selected + "\">\n" + "<name>"
						+ SafeUtil.safeToString(page.getWpName())
						+ "</name>\n"
						+ "<author>"
						+ SafeUtil.safeToString(page.getUsersByWpCreator().getUsFirstname() + " " + page.getUsersByWpCreator().getUsSurname())
						+ "</author>\n"
						+ "<content-title>"
						+ SafeUtil.safeToString(page.getWpContentTitle())
						+ "</content-title>\n"
						+ "<header>"
						+ SafeUtil.safeToString(page.getWpHeader())
						+ "</header>\n"
						// + "<content>"
						// + SafeUtil.safeToString(page.getContent())
						// + "</content>\n"
						+ "<footer>" + SafeUtil.safeToString(page.getWpFooter()) + "</footer>\n" + "<published>"
						+ (page.getWpPublished() != null ? DateUtils.toDateStringCustom(page.getWpPublished()) : "") + "</published>\n" + "<modified>"
						+ (page.getWpLastModified() != null ? (page.getWpLastModified()) : "") + "</modified>\n" + "<href>"
						+ UrlCreator.createUrl(page.getWpId(), page.getPageType()) + "</href>");
				xml.append("</" + page.getPageType().getPtName() + ">\n");
				return xml.toString();
			} else
				return "";
		}

		public WebPage getWebPage() {
			return page;
		}

	}

}
