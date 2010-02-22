package org.deals.framework.dao;

import org.deals.framework.bean.Section;

public interface SectionDAO extends WebPageDAO {
	/**
	 * Restituisce il massimo peso tra le sezioni
	 * 
	 */
	public int getMaxWeightSection();
	public Section findById(Integer id);
}
