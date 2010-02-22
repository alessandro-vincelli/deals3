package org.deals.framework.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class GenericHibernateDAO<T, ID extends Serializable>
extends HibernateDaoSupport	implements GenericDAO<T, ID>{
	
	private Class<T> persistentClass;
			
	public GenericHibernateDAO() {
		this.persistentClass = (Class<T>)
			((ParameterizedType)getClass().getGenericSuperclass())
			.getActualTypeArguments()[0];
	}	

	
	public Class<T> getPersistentClass() {
		return persistentClass;
	}
	
	
	@SuppressWarnings("unchecked")
	public T findById(ID id, boolean lock) {
		return findById(id, lock, getPersistentClass());
	}
	
	
	@SuppressWarnings("unchecked")
	protected T findById(ID id, boolean lock, Class actualClass) {
		T entity;
		if (lock)
			entity = (T)getSession()
			.load(actualClass, id, LockMode.UPGRADE);
		else
			entity = (T)getSession()
			.load(actualClass, id);
		
		return entity;
	}
	
	

	@SuppressWarnings("unchecked")
	public List<T> findAll() {		
		return findByCriteria();
	}

	
	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String... excludeProperty) {
		return findByExample(exampleInstance, getPersistentClass(), excludeProperty);
	}
	
	
	@SuppressWarnings("unchecked")
	protected List<T> findByExample(T exampleInstance, Class actualClass, String... excludeProperty) {
		Criteria crit = getSession().createCriteria(actualClass);
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		return crit.list();
	}
	
	public T makePersistent(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
		//getHibernateTemplate().persist(entity);
		// NOTE: to verify, Added in Order to work in UserManangement, refresh of the list on ADD action
		getHibernateTemplate().flush();
		return entity;
	}
	
	public List<T> makePersistenAll(List<T> entities) {
		getHibernateTemplate().saveOrUpdateAll(entities);
		//getHibernateTemplate().persist(entities);
		// NOTE: to verify, Added in Order to work in UserManangement, refresh of the list on ADD action
		getHibernateTemplate().flush();
		return entities;
	}

	public void makeTransient(T entity) {
		getHibernateTemplate().delete(entity);
	}
	
	
	public void flush() {
		getHibernateTemplate().flush();
	}	
	
	public void clear() {
		getHibernateTemplate().clear();
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion) {
		return findByCriteria(getPersistentClass(), null, criterion);
	}	
	
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Order order, Criterion... criterion) {
		return findByCriteria(getPersistentClass(), order, criterion);
	}			
	
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Class actualClass, Order order, Criterion... criterion) {
		
		Criteria crit =	getSession().createCriteria(actualClass);
		//DetachedCriteria dCrit = DetachedCriteria.forClass(actualClass);
		if (order!=null)
			crit.addOrder(order);
			//dCrit.addOrder(order);
		
		for (Criterion c: criterion) {
			crit.add(c);
			//dCrit.add(c);
		}
		return crit.list();
		//return this.getHibernateTemplate().findByCriteria(dCrit);
		
	}
	
	
	
}
