package org.deals.framework.dao;

import java.util.List;

import org.deals.framework.bean.Item;
import org.deals.framework.bean.PageType;
import org.deals.framework.bean.WebPage;
import org.hibernate.LockMode;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class ItemDAOHibernate extends GenericHibernateDAO<WebPage, Integer> implements ItemDAO {

	public Item findById(Integer id) {
		//WebPage page = super.findById(id, lock, Item.class);
		Item page = (Item)getSession().load(Item.class, id, LockMode.UPGRADE);
		try {
			if (page != null) {
				if (page.getPageType().equals(PageType.ITEM()))
					return page;
			}
		} catch (ObjectNotFoundException ex) {
			// TODO gestire in qualche modo... ho catturato il fatto che non c'è una pagina corrispondente
		}
		return null;
	}

	public List<WebPage> findByExample(WebPage exampleInstance,
			String... excludeProperty) {
		exampleInstance.setPageType(PageType.ITEM());
		//return super.findByExample(exampleInstance, Item.class, excludeProperty);
		//TODO verify thi code when is used getHibernateTemplate probably error on using of resource
		return getHibernateTemplate().findByExample(exampleInstance);
	}

	
	public List<WebPage> findAll() {
		return findByCriteria(Item.class, Order.asc("wpWeight"), new Criterion[] { Restrictions.eq("pageType.ptId", PageType.ITEM_ID) });
	}


}
