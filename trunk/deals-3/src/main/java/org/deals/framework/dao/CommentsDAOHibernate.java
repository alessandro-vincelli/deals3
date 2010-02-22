package org.deals.framework.dao;

import java.util.Iterator;
import java.util.List;

import org.deals.framework.bean.Comments;

public class CommentsDAOHibernate extends GenericHibernateDAO<Comments, Integer> implements CommentsDAO {

	public List<Comments> getAllComments() {
		List<Comments> comments = (List<Comments>) super.findAll();
		int i = 1;
		for (Iterator<Comments> iter = comments.iterator(); iter.hasNext();) {
			Comments element = (Comments) iter.next();
			element.setRowId(i);
			i++;
		}

		return comments;
	}

	public Comments findById(Long id) {
		return (Comments)getSession().load(Comments.class, id);
	}
}