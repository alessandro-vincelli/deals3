package org.deals.framework.cms.forms.formbean;

import java.util.List;

import org.deals.framework.bean.Comments;

public class CommentsManagerFB {

	private List<Comments> comments;	

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	
	public void addComments(Comments up) {
		comments.add(up);
	}
	
}
