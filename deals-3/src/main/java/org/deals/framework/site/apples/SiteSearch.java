/*
 * Created on Nov 7, 2006
 * 
 */
package org.deals.framework.site.apples;


import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.cocoon.components.flow.apples.AppleRequest;
import org.apache.cocoon.components.flow.apples.AppleResponse;
import org.apache.cocoon.components.flow.apples.StatelessAppleController;
import org.apache.cocoon.environment.Request;
import org.apache.cocoon.xml.SaxBuffer;
import org.apache.log4j.Logger;
import org.apache.xerces.parsers.SAXParser;
import org.deals.framework.bean.WebPage;
import org.deals.framework.core.WebSite;
import org.deals.framework.util.SafeUtil;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class SiteSearch implements StatelessAppleController, Serviceable {
	private ServiceManager serviceManager;

	private Logger log = Logger.getLogger(getClass());

	private String pagenum = "1";

	public void service(ServiceManager serviceManager) throws ServiceException {
		this.serviceManager = serviceManager;
	}

	public void process(AppleRequest appleRequest, AppleResponse appleResponse) throws Exception {

		pagenum = appleRequest.getSitemapParameter("pagenum");

		Request request = appleRequest.getCocoonRequest();
		if ((request.getMethod().equals("GET") || request.getMethod().equals("POST")) && request.getParameter("href") != null) {
			WebSite site = new WebSite();

			// forzo la URL per la paginazione
			String url = request.getRequestURI();
			if (url.indexOf("(") > -1) {
				url = url.substring(0, url.indexOf("(")) + ".html";
			}

			WebPage page = site.getPage(appleRequest.getCocoonRequest().getParameter("href"));
			log.debug("___________________PAGINA  DI RICERCA" + page);
			log.debug("___________________PARAMETRI DI RICERCA" + request.getParameter("searchtext"));
			if (!(SafeUtil.isNullOrEmpty(request.getParameter("sezione")))) {
				page = site.advancedSearch(page, request.getParameter("sezione").toString(), request.getParameter("searchtext").toString(),""/*mettere il valore del tag*/);
			} else {
				page = site.simpleSearch(page, request.getParameter("searchtext"));
			}

			request.getSession().setAttribute("page-search-results", page);

			Map<String, SaxBuffer> viewData = new HashMap<String, SaxBuffer>();

			SaxBuffer sb = new SaxBuffer();

			XMLReader parser = new SAXParser();
			parser.setContentHandler(sb);
			parser.parse(new InputSource(new StringReader(page.toXML())));

			viewData.put("document", sb);

			// Acquisisco il template per la pagina
			String pageTemplate = "risultati_ricerca";

			appleResponse.sendPage("PagePipe-" + pageTemplate + "-(" + pagenum + ")", viewData);
		} else if (request.getSession().getAttribute("page-search-results") != null) {
			WebPage page = (WebPage) request.getSession().getAttribute("page-search-results");
			log.debug("___________________PAGINA  DI RICERCA in SESSIONE" + page);
			XMLReader parser = new SAXParser();
			Map<String, SaxBuffer> viewData = new HashMap<String, SaxBuffer>();

			SaxBuffer sb = new SaxBuffer();

			parser.setContentHandler(sb);
			parser.parse(new InputSource(new StringReader(page.toXML())));
			viewData.put("document", sb);

			// Acquisisco il template per la pagina
			String pageTemplate = "risultati_ricerca";

			appleResponse.sendPage("PagePipe-" + pageTemplate + "-(" + pagenum + ")", viewData);
		} else
			throw new Exception("Unexpected HTTP method: " + request.getMethod());
	}
}
