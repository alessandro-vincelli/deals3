package org.deals.framework.site;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.xml.SaxBuffer;
import org.apache.log4j.Logger;
import org.apache.xerces.parsers.SAXParser;
import org.deals.framework.bean.WebPage;
import org.deals.framework.core.WebSite;
import org.deals.framework.util.SafeUtil;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class Search {
	
	private Logger log = Logger.getLogger(getClass());
	WebSite webSite;
	
	public Map<String, Object> process(Request request, String pagenum) throws Exception {
	
		if ((request.getMethod().equals("GET") || request.getMethod().equals("POST")) && request.getParameter("href") != null) {
			

			// forzo la URL per la paginazione
			String url = request.getRequestURI();
			if (url.indexOf("(") > -1) {
				url = url.substring(0, url.indexOf("(")) + ".html";
			}

			WebPage page = webSite.getPage(request.getParameter("href"));
			log.debug("___________________PAGINA  DI RICERCA" + page);
			log.debug("___________________PARAMETRI DI RICERCA" + request.getParameter("searchtext"));
			if (!(SafeUtil.isNullOrEmpty(request.getParameter("sezione")))) {
				page = webSite.advancedSearch(page, request.getParameter("sezione").toString(), request.getParameter("searchtext").toString(),""/*mettere il valore del tag*/);
			} else {
				page = webSite.simpleSearch(page, request.getParameter("searchtext"));
			}

			request.getSession().setAttribute("page-search-results", page);

			Map<String, Object> viewData = new HashMap<String, Object>();

			SaxBuffer sb = new SaxBuffer();

			XMLReader parser = new SAXParser();
			parser.setContentHandler(sb);
			parser.parse(new InputSource(new StringReader(page.toXML())));

			

			// Acquisisco il template per la pagina
			String pageTemplate = "risultati_ricerca";
			
			viewData.put("document", sb);
			viewData.put("pageTemplate", pageTemplate);
			viewData.put("pageNum", pagenum);

			//appleResponse.sendPage("PagePipe-" + pageTemplate + "-(" + pagenum + ")", viewData);
			return viewData;

		} else if (request.getSession().getAttribute("page-search-results") != null) {
			WebPage page = (WebPage) request.getSession().getAttribute("page-search-results");
			log.debug("___________________PAGINA  DI RICERCA in SESSIONE" + page);
			XMLReader parser = new SAXParser();
			Map<String, Object> viewData = new HashMap<String, Object>();

			SaxBuffer sb = new SaxBuffer();

			parser.setContentHandler(sb);
			parser.parse(new InputSource(new StringReader(page.toXML())));
			viewData.put("document", sb);

			// Acquisisco il template per la pagina
			String pageTemplate = "risultati_ricerca";

			viewData.put("document", sb);
			viewData.put("pageTemplate", pageTemplate);
			viewData.put("pageNum", pagenum);

			//appleResponse.sendPage("PagePipe-" + pageTemplate + "-(" + pagenum + ")", viewData);
			return viewData;

		} else
			throw new Exception("Unexpected HTTP method: " + request.getMethod());
	}


	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}
}
