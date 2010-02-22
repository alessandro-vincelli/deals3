package org.deals.framework.site.ext;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.xml.SaxBuffer;
import org.apache.xerces.parsers.SAXParser;
import org.deals.framework.bean.Comments;
import org.deals.framework.core.WebSite;
import org.deals.framework.core.util.DealsMailSender;
import org.deals.framework.core.util.MailMessage;
import org.deals.framework.exception.SiteException;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * The class manage the Request that contains the Comment posted by the user
 * 
 * 
 * @author Alessandro Vincelli
 * @email a.vincelli@gmail.com
 * Created on Oct 12, 2008
 */
public class CommentsHandle {
	
	private WebSite webSite;
    private DealsMailSender dealsMailSender;

    public void setDealsMailSender(DealsMailSender dealsMailSender) {
        this.dealsMailSender = dealsMailSender;
    }

	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}

	/**
	 * Processe the request that contains the comment posted,
	 * return the XML that contains the documents just inserted
	 * the element <fail> in case of fail
	 * 
	 * @param request
	 * @return
	 * @throws JSONException
	 * @throws IOException
	 * @throws SAXException
	 */
	public Map<String, SaxBuffer> process(Request request) throws JSONException, IOException, SAXException, SiteException{
		request.getRequestURI();
		Comments comment = null;
		
		CommentsManager mm = new CommentsManager(request, webSite);
	
		try {
			comment = mm.process();
		} catch (SiteException e) {
			comment = null;
		}
		
		Map<String, SaxBuffer> viewData = new HashMap<String, SaxBuffer>();
		SaxBuffer sb = new SaxBuffer();
		XMLReader parser = new SAXParser();
		parser.setContentHandler(sb);

		StringBuffer jRepsonse = new StringBuffer();
		jRepsonse.append("<json>");
		if(comment != null){
			jRepsonse.append(toJSONObject(comment));
            dealsMailSender.send(new MailMessage("Nuovo messaggio sul blog", "01", "un nuovo emssaggio e' in attesa di moderazione"));
		}
		else{
			jRepsonse.append(toJSONString("fail"));
            dealsMailSender.send(new MailMessage("Problemi neslla gestione messaggi sul blog", "00", "error"));
		}
		jRepsonse.append("</json>");
		parser.parse(new InputSource(new StringReader(jRepsonse.toString())));
		viewData.put("document", sb);
		return viewData;

	}
	
	private String toJSONObject(Comments comment) throws JSONException {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", "ok");
		jsonObj.put("commentAuthor", comment.getCommentAuthor().toString());
		jsonObj.put("commentContent",comment.getCommentContent().toString());
		jsonObj.put("commentAuthorEmail", comment.getCommentAuthorEmail().toString());
		jsonObj.put("commentAuthorUrl", comment.getCommentAuthorUrl().toString());
		jsonObj.put("commentDate", comment.getCommentDate().toString());
		return jsonObj.toString();
	}
	
	private String toJSONString(String messaggio) throws JSONException {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", messaggio);
		return jsonObj.toString();
	}
}
