package org.deals.framework.site.ext;

import java.util.Set;

import org.deals.framework.bean.Comments;
import org.deals.framework.bean.WebPage;
import org.deals.framework.exception.SiteException;

/**
 * @author Alessandro Vincelli
 * @email a.vincelli@gmail.com Created on Nov 11, 2008
 */
public class CommentsUtils {

	/**
	 * 
	 * Return all the comments of the passed webpage
	 * 
	 * @param pageSelected
	 * @return
	 */
	public static StringBuffer getMessagesFragment(WebPage pageSelected) throws SiteException {

		Set<Comments> comments = pageSelected.getCommentses();
		StringBuffer xml = new StringBuffer();
		xml.append("<comments-list>");
		for (Comments comment : comments) {
			if(comment.getCommentApproved() == 1){
				xml.append(comment.toXML(null));
			}
		}
		xml.append("</comments-list>");
		return xml;
	}
}
