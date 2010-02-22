package org.deals.framework.dao;

import java.util.List;

import org.deals.framework.bean.CmsSection;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class CmsSectionDAOHibernate extends GenericHibernateDAO<CmsSection, Integer> implements CmsSectionDAO {

	public List<CmsSection> findAllActiveSorted() {
		//Object wu = getSession().createQuery("from CmsSection where cmssActive = true order by cmssPosition");

		List<CmsSection> result = findByCriteria(Order.desc("cmssPosition"), new Criterion[] {Restrictions.eq("cmssActive",
				true) });
		return result;

	}

}
