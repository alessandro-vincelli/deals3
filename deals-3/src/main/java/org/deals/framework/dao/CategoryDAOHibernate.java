package org.deals.framework.dao;

import java.util.List;

import org.deals.framework.bean.Category;
import org.deals.framework.bean.PageType;
import org.deals.framework.bean.WebPage;
import org.hibernate.LockMode;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class CategoryDAOHibernate extends GenericHibernateDAO<WebPage, Integer> implements CategoryDAO {

	
	public Category findById(Integer id) {
		//WebPage page = super.findById(id, lock, Category.class);
		Category page = (Category)getSession().load(Category.class, id, LockMode.UPGRADE);
		try {
			if (page != null) {
				if (page.getPageType().equals(PageType.CATEGORY()))
					return page;
			}
		} catch (ObjectNotFoundException ex) {
			// TODO gestire in qualche modo... ho catturato il fatto che non
			// c'è una pagina corrispondente
		}
		return null;
	}

	public List<WebPage> findByExample(WebPage exampleInstance,
			String... excludeProperty) {
		exampleInstance.setPageType(PageType.CATEGORY());
		return getHibernateTemplate().findByExample(exampleInstance);
		//return super.findByExample(exampleInstance, Category.class,	excludeProperty);
	}

	
	public List<WebPage> findAll() {
		return findByCriteria(Category.class, Order.asc("wpWeight"), new Criterion[] { Restrictions.eq("pageType.ptId", PageType.CATEGORY_ID) });
	}

}
