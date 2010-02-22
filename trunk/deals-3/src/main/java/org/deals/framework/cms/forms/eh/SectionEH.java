/*
 * Created on Nov 14, 2006
 *
 */
package org.deals.framework.cms.forms.eh;

import org.apache.cocoon.forms.formmodel.Submit;
import org.deals.framework.cms.forms.eh.abstracts.AbstractSectionEH;
import org.deals.framework.cms.forms.wrapper.SectionFW;

public class SectionEH extends AbstractSectionEH {

	@Override
	public void formOnCreate(SectionFW f) {
		super.formOnCreate(f);
		
	}

	@Override
	public void sectionsubmitOnAction(SectionFW f, Submit source) {
		super.sectionsubmitOnAction(f, source);
	}

	/**
	 * Set attribute deleteMe=true, the delete is performed in the CategoryManager 
	 */
	@Override
	public void sectiondeleteOnAction(SectionFW f, Submit source) {
		super.sectiondeleteOnAction(f, source);
		f.setAttribute("deleteMe", true);
	}

	/**
	 * restituisce il controllo al flow senza fare nulla
	 */
	@Override
	public void sectionnodeleteOnAction(SectionFW f, Submit source) {
		super.sectionnodeleteOnAction(f, source);
	}

}
