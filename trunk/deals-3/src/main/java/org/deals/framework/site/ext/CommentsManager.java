package org.deals.framework.site.ext;

import java.util.Calendar;
import java.util.TimeZone;

import org.apache.cocoon.environment.Request;
import org.deals.framework.bean.Comments;
import org.deals.framework.bean.WebPage;
import org.deals.framework.core.WebSite;
import org.deals.framework.exception.SiteException;

public class CommentsManager {
	
	private Comments comment;
	private WebSite webSite;
	private Request request;
	public CommentsManager(Request request, WebSite webSite){
		this.request = request;
		this.webSite = webSite;
	}
	
	public Comments process() throws SiteException{
		Calendar cal = Calendar.getInstance();
		//FIXME doesn't work the GMT
		Calendar calGMT = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		calGMT.setTimeZone(TimeZone.getTimeZone("GMT"));
		String clientIP = request.getRemoteAddr();
		comment = new Comments();
		comment.setCommentAuthor(request.getParameter("username"));
		comment.setCommentAuthorEmail(request.getParameter("email"));
		comment.setCommentAuthorUrl(request.getParameter("website"));
		comment.setCommentContent(request.getParameter("postext"));
		comment.setCommentDate(cal.getTime());
		comment.setCommentDateGmt(calGMT.getTime());
		comment.setCommentAgent(request.getHeader("user-agent"));
		comment.setCommentAuthorIp(clientIP);
		comment.setCommentApproved(0);
		WebPage page = webSite.getPage(new Integer(request.getParameter("webpage")));
	    
		if(page != null){
	    	comment.setWebPage(page);
			webSite.saveComment(comment);
			return comment;
	    }
		else {
			throw new SiteException("Webpage ID doesn't exists");
		}

	}

}
