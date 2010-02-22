package org.deals.framework.dao;

import org.deals.framework.bean.Item;


public interface ItemDAO extends WebPageDAO {
	public Item findById(Integer id);
}
