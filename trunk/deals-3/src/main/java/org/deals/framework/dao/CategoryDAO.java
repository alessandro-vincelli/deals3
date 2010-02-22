package org.deals.framework.dao;

import org.deals.framework.bean.Category;

public interface CategoryDAO extends WebPageDAO {
	public Category findById(Integer id);
}
