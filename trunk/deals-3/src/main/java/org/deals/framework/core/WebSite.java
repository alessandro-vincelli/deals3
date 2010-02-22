package org.deals.framework.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.deals.framework.bean.Category;
import org.deals.framework.bean.Comments;
import org.deals.framework.bean.Item;
import org.deals.framework.bean.PageState;
import org.deals.framework.bean.PageType;
import org.deals.framework.bean.Section;
import org.deals.framework.bean.WebPage;
import org.deals.framework.dao.CategoryDAO;
import org.deals.framework.dao.CommentsDAO;
import org.deals.framework.dao.ItemDAO;
import org.deals.framework.dao.SectionDAO;
import org.deals.framework.dao.WebPageDAO;
import org.deals.framework.exception.SiteException;
import org.deals.framework.search.SearchResult;
import org.deals.framework.search.Searcher;
import org.deals.framework.search.WebPageIndexer;
import org.deals.framework.site.ext.CommentsUtils;
import org.deals.framework.util.DateUtils;


public class WebSite {

	protected WebPageDAO dao;
	protected SectionDAO secDao;
	protected CategoryDAO categoryDao;
	protected ItemDAO itemDao;
	private NavigationHandler nh;
	private WebPageIndexer wpi;
	private CommentsDAO comDao;

	private static boolean filterEnabled = true;
	
	
	public void enableFilter() {
		filterEnabled = true;
	}
	
	public void disableFilter() {
		filterEnabled = false;
	}
	
	/**
	 * get Page by URL
	 * @param url
	 * @return
	 */
	public WebPage getPage(String url) {
		HashMap hm = UrlResolver.resolveUrl(url);
		if (hm.size()>0) {
			PageType pt = (PageType)hm.keySet().iterator().next();
			Integer pageId = (Integer)hm.get(pt);
			WebPage page = getPage(pageId, pt);
			if (page!=null) {
				page.setNavigationMenuXML(nh.getNavigationMenu(page, (String)hm.get("queryString")));
				page.setBreadCrumbsMenuXML(nh.getBreadCrumbsMenu(page)); // TODO mettere anche la query string, minchia!
				try {
					page.setCommentsXML(CommentsUtils.getMessagesFragment(page));
				} catch (SiteException e) {
					new SiteException(e);
				} 
				// TODO mettere anche la query string, minchia!
			}
			return page;
		}		
		return null;	
	}
	
	/**
	 * get Page by primary key
	 * @param url
	 * @return
	 */
	public WebPage getPage(int id) {		
		return getPage(id, null);
	}
	
	/**
	 * get section (WebPage casted to section)
	 * @param url
	 * @return
	 */
	public Section getSection(int id) {
		return filterSection(secDao.findById(id), true);
	}
	
	/**
	 * get category (WebPage casted to category)
	 * @param url
	 * @return
	 */
	public Category getCategory(int id) {
		return filterCategory(categoryDao.findById(id), true);
	}
	
	/**
	 * get item (WebPage casted to Item)
	 * @param url
	 * @return
	 */
	public Item getItem(int id) {
		return filterItem(itemDao.findById(id), true);
	}
	
	/**
	 * get Page
	 * @param url
	 * @return
	 */
	protected WebPage getPage(int id, PageType pageType) {
		return filterPage(dao.findById(id, true),true);
	}
		
	/**
	 * Save page
	 * @param url
	 * @return
	 */
	public void savePage(WebPage page) {
		dao.makePersistent((WebPage)page);
	}
	
	/**
	 * Save comment
	 * @param comment
	 * @return
	 */
	public void saveComment(Comments comment) {
		comDao.makePersistent(comment);
	}
	
	/**
	 * Filtra la categoria per controllarne la pubblicazione o altre proprieta'
	 * come ad esempio la scadenza
	 * 
	 * @param page
	 * @param forSite
	 * @return
	 */
	public static WebPage filterPage(WebPage page, boolean forSite) {		
		if (page != null) 
			if (page.getWpExpire()!=null) 
				if (DateUtils.now().after(page.getWpExpire())) { 
					// scaduta			
					// System.out.println("PAGINA " + page.getWpId() + " - " + page.getWpContentTitle() + " SCADUTA dal " + page.getWpExpire());
					page.setPageState(PageState.EXPIRED());		
				}
		
		if (filterEnabled) {
			if (!forSite) {
				if (!PageState.DELETED().getPsId().equals(page.getPageState().getPsId()))
				   return page;
			}
			if (page != null) {
				// SE LA PAGINA NON E' PUBBLICATA RESTITUISCE NULL
				if (!(PageState.PUBLISHED().getPsId().equals(page.getPageState().getPsId()))) {
					//System.out.println("PAGINA " + page.getWpId() + " - " + page.getWpContentTitle() + " NON PUBBLICATA!");
					return null;
				}				
			}			
		}		
		return page;
	}
	
	/**
	 * Filtra la categoria per controllarne la pubblicazione o altre propriet�
	 * come ad esempio la scadenza
	 */
	public static Category filterCategory(Category page, boolean forSite) {		
		if (page != null) 
			if (page.getWpExpire()!=null) 
				if (DateUtils.now().after(page.getWpExpire())) { 
					// scaduta			
					// System.out.println("PAGINA " + page.getWpId() + " - " + page.getWpContentTitle() + " SCADUTA dal " + page.getWpExpire());
					page.setPageState(PageState.EXPIRED());		
				}
		
		if (filterEnabled) {
			if (!forSite) {
				if (!PageState.DELETED().getPsId().equals(page.getPageState().getPsId()))
				   return page;
			}
			if (page != null) {
				// SE LA PAGINA NON E' PUBBLICATA RESTITUISCE NULL
				if (!(PageState.PUBLISHED().getPsId().equals(page.getPageState().getPsId()))) {
					//System.out.println("PAGINA " + page.getWpId() + " - " + page.getWpContentTitle() + " NON PUBBLICATA!");
					return null;
				}				
			}			
		}		
		return page;
	}
	
	/**
	 * Filtra la categoria per controllarne la pubblicazione o altre propriet�
	 * come ad esempio la scadenza
	 */
	public static Item filterItem(Item page, boolean forSite) {		
		if (page != null) 
			if (page.getWpExpire()!=null) 
				if (DateUtils.now().after(page.getWpExpire())) { 
					// scaduta			
					// System.out.println("PAGINA " + page.getWpId() + " - " + page.getWpContentTitle() + " SCADUTA dal " + page.getWpExpire());
					page.setPageState(PageState.EXPIRED());		
				}
		
		if (filterEnabled) {
			if (!forSite) {
				if (!PageState.DELETED().getPsId().equals(page.getPageState().getPsId()))
				   return page;
			}
			if (page != null) {
				// SE LA PAGINA NON E' PUBBLICATA RESTITUISCE NULL
				if (!(PageState.PUBLISHED().getPsId().equals(page.getPageState().getPsId()))) {
					//System.out.println("PAGINA " + page.getWpId() + " - " + page.getWpContentTitle() + " NON PUBBLICATA!");
					return null;
				}				
			}			
		}		
		return page;
	}
	

	/**
	 * Filtra la sezione per controllarne la pubblicazione o altre propriet�
	 * come ad esempio la scadenza
	 */
	public static Section filterSection(Section page, boolean forSite) {		
		if (page != null) 
			if (page.getWpExpire()!=null) 
				if (DateUtils.now().after(page.getWpExpire())) { 
					// scaduta			
					// System.out.println("PAGINA " + page.getWpId() + " - " + page.getWpContentTitle() + " SCADUTA dal " + page.getWpExpire());
					page.setPageState(PageState.EXPIRED());		
				}
		
		if (filterEnabled) {
			if (!forSite) {
				if (!PageState.DELETED().getPsId().equals(page.getPageState().getPsId()))
				   return page;
			}
			if (page != null) {
				// SE LA PAGINA NON E' PUBBLICATA RESTITUISCE NULL
				if (!(PageState.PUBLISHED().getPsId().equals(page.getPageState().getPsId()))) {
					//System.out.println("PAGINA " + page.getWpId() + " - " + page.getWpContentTitle() + " NON PUBBLICATA!");
					return null;
				}				
			}			
		}		
		return page;
	}	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  S E A R C H   M E T H O D S ////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	
	private Searcher s;	
	
	public WebPage simpleSearch(WebPage page, String searchCriteria) throws Exception {		
		Vector<SearchResult> result = s.simpleSearch(searchCriteria);
		StringBuffer customXML = new StringBuffer();
		customXML.append("<search-results size=\"" + result.size() + "\">");
		for (Iterator<SearchResult> iter = result.iterator(); iter.hasNext();) {
			SearchResult res = iter.next();
			customXML.append(res.toXML());
		}
		customXML.append("</search-results>");
		page.setCustomXML(customXML.toString());
		return page;
	}
		
	public WebPage advancedSearch(WebPage page, String section, String searchCriteria, String tag) throws Exception {		
		Vector<SearchResult> result = s.advancedSearch(section, searchCriteria, tag);
		StringBuffer customXML = new StringBuffer();
		customXML.append("<search-results size=\"" + result.size() + "\">");
		for (Iterator<SearchResult> iter = result.iterator(); iter.hasNext();) {
			SearchResult res = iter.next();
			customXML.append(res.toXML());
		}
		customXML.append("</search-results>");
		page.setCustomXML(customXML.toString());
		return page;
	}

	public WebPage comunicatiSearch(WebPage page, Integer mese, Integer anno, String societa, String servizio, String searchCriteria, String tag) throws Exception {		
		Vector<SearchResult> result = s.comunicatiSearch(mese, anno, societa, servizio, searchCriteria, tag);
		StringBuffer customXML = new StringBuffer();
		customXML.append("<search-results size=\"" + result.size() + "\">");
		for (Iterator<SearchResult> iter = result.iterator(); iter.hasNext();) {
			SearchResult res = iter.next();
			customXML.append(res.toXML());
		}
		customXML.append("</search-results>");
		page.setCustomXML(customXML.toString());
		return page;
	}


	public void reindexWebPages() throws Exception {		
		wpi.indicizeWebPages();
	}

	public void setDao(WebPageDAO dao) {
		this.dao = dao;
	}
	
	public void setSecDao(SectionDAO secDao) {
		this.secDao = secDao;
	}

	public void setCategoryDao(CategoryDAO categoryDao) {
		this.categoryDao = categoryDao;
	}

	public void setItemDao(ItemDAO itemDao) {
		this.itemDao = itemDao;
	}

	public void setNh(NavigationHandler nh) {
		this.nh = nh;
	}

	public void setWpi(WebPageIndexer wpi) {
		this.wpi = wpi;
	}

	public void setS(Searcher s) {
		this.s = s;
	}
	
	public CommentsDAO getComDao() {
		return comDao;
	}

	public void setComDao(CommentsDAO comDao) {
		this.comDao = comDao;
	}



}
