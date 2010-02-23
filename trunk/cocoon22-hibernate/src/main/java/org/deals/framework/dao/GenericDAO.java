package org.deals.framework.dao;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author Alessandro Vincelli
 * @email a.vincelli@gmail.com
 * Created on Aug 14, 2008
 * @param <T>
 * @param <ID>
 */
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
