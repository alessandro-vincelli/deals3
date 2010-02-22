package org.deals.framework.dao;

import java.util.List;

import org.deals.framework.bean.CmsSection;

public interface CmsSectionDAO  extends GenericDAO<CmsSection, Integer> {
	public List<CmsSection> findAllActiveSorted();

}
