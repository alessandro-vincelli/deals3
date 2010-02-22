package org.deals.framework.dao;

import java.util.Iterator;
import java.util.List;

import org.deals.framework.bean.ComponentType;
import org.deals.framework.bean.WebComponent;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class WebComponentDAOHibernate extends GenericHibernateDAO<WebComponent, Integer> implements WebComponentDAO {

	public List<WebComponent> getImages() {
		return findByCriteria(Order.desc("wcId"), new Criterion[] { Restrictions.or(Restrictions.eq("componentType.ctId", ComponentType.PAGE_IMAGE_ID), Restrictions.eq(
				"componentType.ctId", ComponentType.THUMBNAIL_ID)) });
	}

	public List<WebComponent> getWebResources() {
		// Tira su con filtri
		List<WebComponent> result = findByCriteria(Order.desc("wcId"), new Criterion[] { Restrictions.or(Restrictions.or(Restrictions.eq("componentType.ctId",
				ComponentType.PAGE_IMAGE_ID), Restrictions.eq("componentType.ctId", ComponentType.THUMBNAIL_ID)), Restrictions.eq("componentType.ctId",
				ComponentType.WEB_RESOURCE_ID)) });
		//List<WebComponent> result = (List<WebComponent>)super.findAll();

		// ho deciso di tirare su tutto
		//List<WebComponent> result = findAll();
		// Dopo aver ordinato la collection, Setto un id di riga sul campo fittizio rowId, serve
		// per l'aggancio nel repeater
		int rowId = 1;
		for (Iterator iter = result.iterator(); iter.hasNext();) {
			WebComponent element = (WebComponent) iter.next();
			element.setRowId(rowId);
			rowId = rowId + 1;
		}

		return result;
	}

	public List<String> getDistinctMimeType() {
		Session ses = getSession();
		Query q = ses.createQuery("select distinct(wrMimetype) from WebComponent"); 
		return q.list();
	}
}
