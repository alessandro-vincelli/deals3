/*
 * Created on Nov 14, 2006
 *
 */
package org.deals.framework.cms.forms.eh;

import org.apache.cocoon.forms.formmodel.Submit;
import org.apache.log4j.Logger;
import org.deals.framework.cms.forms.eh.abstracts.AbstractCategoryEH;
import org.deals.framework.cms.forms.wrapper.CategoryFW;

public class CategoryEH extends AbstractCategoryEH {


	Logger log = Logger.getLogger(getClass());
	/**
	 * Cancella la sezione
	 */
	@Override
	public void categorydeleteOnAction(CategoryFW f, Submit source) {
		super.categorydeleteOnAction(f, source);
		f.setAttribute("deleteMe", true);
	}

	/**
	 * restituisce il controllo al flow senza fare nulla
	 */
	@Override
	public void categorynodeleteOnAction(CategoryFW f, Submit source) {
		super.categorynodeleteOnAction(f, source);
	}

}
