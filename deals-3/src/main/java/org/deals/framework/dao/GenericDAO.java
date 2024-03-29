package org.deals.framework.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {

	T findById(ID id, boolean lock);
	
	List<T> findAll();
	
	List<T> findByExample(T exampleInstance, 
							String... excludeProperty);
	
	T makePersistent(T entity);
	
	List<T> makePersistenAll(List<T> entities);
	
	void makeTransient(T entity);
	
	void flush();
	
	void clear();	
	
}
