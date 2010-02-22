package org.deals.framework.site;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.xml.SaxBuffer;
import org.apache.log4j.Logger;
import org.apache.xerces.parsers.SAXParser;
import org.deals.framework.core.RssGenerator;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
 * 
 * @author Alessandro Vincelli
 * 
 */

public class Rss {

	RssGenerator rssGenerator;
	Logger log = Logger.getLogger(getClass());

	public Map<String, Object> process(Request request) throws Exception {
		if (request.getMethod().equals("GET")) {
			Map<String, Object> viewData = new HashMap<String, Object>();
			SaxBuffer sb = new SaxBuffer();
			XMLReader parser = new SAXParser();
			parser.setContentHandler(sb);
			parser.parse(new InputSource(new StringReader(rssGenerator.getXMLList())));
			viewData.put("document", sb);
			return viewData;
		} else {
			throw new Exception("Unexpected HTTP method: " + request.getMethod());
		}
	}

	public void setRssGenerator(RssGenerator rssGenerator) {
		this.rssGenerator = rssGenerator;
	}

}
