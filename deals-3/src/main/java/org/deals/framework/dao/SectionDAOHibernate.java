package org.deals.framework.dao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.deals.framework.bean.PageType;
import org.deals.framework.bean.Section;
import org.deals.framework.bean.WebPage;
import org.hibernate.LockMode;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class SectionDAOHibernate extends GenericHibernateDAO<WebPage, Integer> implements SectionDAO {

	
	public Section findById(Integer id) {
		Section page = (Section)getSession().load(Section.class, id, LockMode.UPGRADE);
		try {
			if (page != null) {
				if (page.getPageType().equals(PageType.SECTION()))
					return page;
			}
		} catch (ObjectNotFoundException ex) {
			// TODO gestire in qualche modo... ho catturato il fatto che non
			// c'è una pagina corrispondente
		}
		return null;
	}

	
	public List<WebPage> findByExample(WebPage exampleInstance, String... excludeProperty) {
		exampleInstance.setPageType(PageType.SECTION());
		//return getHibernateTemplate().findByExample(exampleInstance, Section.class, excludeProperty);
		return getHibernateTemplate().findByExample(exampleInstance);
	}

	public List<WebPage> findAll() {
		List<WebPage> pages = findByCriteria(Section.class, Order.asc("wpWeight"), new Criterion[] { Restrictions.eq("pageType.ptId", PageType.SECTION_ID) });
		Collections.sort(pages, new Comparator<WebPage>() {
			public int compare(WebPage o1, WebPage o2) {
				return o1.getWpWeight().compareTo(o2.getWpWeight());
			}
		});
		return pages;
	}


	public int getMaxWeightSection() {
		return 0;
	}
}
