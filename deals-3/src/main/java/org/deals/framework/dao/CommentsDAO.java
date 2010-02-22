package org.deals.framework.dao;

import java.util.List;

import org.deals.framework.bean.Comments;

public interface CommentsDAO extends GenericDAO<Comments, Integer> {

	public List<Comments> getAllComments();
	public Comments findById(Long id);

}
