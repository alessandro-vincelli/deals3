package org.deals.framework.cms.forms.formbean;

import java.util.Collection;
import java.util.Iterator;

import org.apache.cocoon.forms.binding.AbstractCustomBinding;
import org.apache.cocoon.forms.formmodel.Widget;
import org.apache.commons.jxpath.JXPathContext;
import org.deals.framework.bean.APageComponentsType;
import org.deals.framework.core.WebCMS;

public class Item2Images2apcTypeXPath extends AbstractCustomBinding {
	private WebCMS cms;
	@Override
	protected void doLoad(Widget frmModel, JXPathContext context) throws Exception {
		APageComponentsType wct = (APageComponentsType) context.getValue("APageComponentsType");
		frmModel.setValue(wct.getApctId());
	}

	@Override
	protected void doSave(Widget frmModel, JXPathContext context) throws Exception {
		
		Collection<APageComponentsType> wcts = (Collection<APageComponentsType>) cms.getAPageComponentsTypes();
		for (Iterator iter = wcts.iterator(); iter.hasNext();) {
			APageComponentsType wct = (APageComponentsType) iter.next();
			if (wct.getApctId().equals(frmModel.getValue())) {
				context.setValue("APageComponentsType", wct);
			}
		}
	}

	public void setCms(WebCMS cms) {
		this.cms = cms;
	}

}
