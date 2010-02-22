package org.deals.framework.site;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.xml.SaxBuffer;
import org.apache.log4j.Logger;
import org.apache.xerces.parsers.SAXParser;
import org.deals.framework.bean.WebPage;
import org.deals.framework.core.Config;
import org.deals.framework.core.WebSite;
import org.deals.framework.site.ext.UserSite;
import org.deals.framework.util.SafeUtil;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
 * 
 * @author Alessandro Vincelli
 *
 *	Gestisce il flusso di tutte le pagine del sito.
 *	- Url per la paginazione
 *	- Aggancio XML utente in sessione
 *	- Controllo pagine ad accesso ristretto  
 */

public class Page {
	
	WebSite webSite;
	Logger log = Logger.getLogger(getClass());

	private String pagenum = "1";

	private static final String homePageID = Config.getProperty("Config.SITE_HOMEPAGE");
	
	private static final String restrictedPageIDS = Config.getProperty("Config.SITE_SECTIONS_RESTRICTED");
	
	private static final String redirectAccesDenied = Config.getProperty("Config.SITE_ACCESS_SECTIONS_RESTRICTED_DENIED");
	

	public Map<String, Object> process(Request request, String pagenum) throws Exception {

		String customXML = "";

		if (request.getMethod().equals("GET")) {

			String url = request.getRequestURI();
			log.debug("____DEBUG_URL richiesta        " + url);
			
			this.pagenum = pagenum;

			// SE non chiede una pagina specifica viene sempre mostrata home page
			if (url.indexOf(".html") == -1) {
				url = "section/" + homePageID + ".html";
			}

			// forzo la URL per la paginazione
			if (url.indexOf("(") > -1) {
				url = url.substring(0, url.indexOf("(")) + ".html";
			}

			WebPage page = null;
			String pageTemplate = "";

			log.debug("_____DEBUG_URL elaborata  " + url);
			log.debug("_____DEBUG_REFERER URL richiesta" + request.getHeader("referer"));
			log.debug("_____DEBUG_1 URL richiesta" + request.getRemoteAddr());
			log.debug("_____DEBUG_2 richiesta" + request.getRemoteHost());
			log.debug("_____DEBUG_3 richiesta" + request.getRemoteUser());
			// Accesso Area riservata autenticato
			if (url.indexOf("/" + restrictedPageIDS + ".html") > 0 && request.getSession().getAttribute("user_site") != null) {
				page = webSite.getPage(url);
				UserSite user = (UserSite) request.getSession().getAttribute("user_site");
				// Setto l'autore della pagina con l'utente loggato
				page.setWpAuthor(user.getNome() + " " + user.getCognome());
			}
			// Accesso Area riservata autenticato NON autenticato, ridirigo ad home page
			else if (url.indexOf("/" + restrictedPageIDS + ".html") > 0) {
				page = webSite.getPage(redirectAccesDenied);
				log.debug("_____Accesso non autorizzato, ridirigo in home page Clienti");
			}
			// Pagine pubbliche
			else {
				// Risoolve un bug, non ricordo quale
				if (url.lastIndexOf("site") > 0) {
					url = url.substring(url.lastIndexOf("site") + 5);
				}
				page = webSite.getPage(url);
			}

			HttpSession session = request.getSession(true);
			if (session.getAttribute("user_site") != null) {
				UserSite user = (UserSite) session.getAttribute("user_site");
				customXML += user.toXML();
			}

			if (session.getAttribute("utente_altocontrasto") != null) {
				customXML += ("<utente_altocontrasto/>");
			}

			// Aggiungo XML, per ora  dati utente aggiuntivi su utente in sessione  
			if (!SafeUtil.isNullOrEmpty(customXML))
				page.setCustomXML(customXML);

			// TODO controllo, perché page.getTemplate potrebbe essere null
			// non ha un template agganciato...
			log.debug("____page" + page);
			log.debug("____titolo" + page.getWpPageTitle());
			log.debug("____tem" + page.getTemplate());
			log.debug("____tem nome	" + page.getTemplate().getTeName());
			if (page.getTemplate().getTeName() != null)
				pageTemplate = page.getTemplate().getTeName();
			else
				throw new Exception("Template non settato per la pagina corrente");

			Map<String, Object> viewData = new HashMap<String, Object>();

			SaxBuffer sb = new SaxBuffer();

			XMLReader parser = new SAXParser();
			parser.setContentHandler(sb);
			parser.parse(new InputSource(new StringReader(page.toXML())));


			// Se il prametro print=true imposto il template stampa.xsl
			if (request.getParameter("print") != null) {
				if (request.getParameter("print").equals("true")) {
					pageTemplate = "stampa";
				}
			}

			viewData.put("document", sb);
			viewData.put("pageTemplate", pageTemplate);
			viewData.put("pageNum", pagenum);

			//appleResponse.sendPage("PagePipe-" + pageTemplate + "-(" + pagenum + ")", viewData);
			return viewData;
		} else {
			throw new Exception("Unexpected HTTP method: " + request.getMethod());
		}
	}

	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}


}
