package org.deals.framework.cms.forms.formbean;

import java.util.Collection;
import java.util.Iterator;

import org.apache.cocoon.forms.binding.AbstractCustomBinding;
import org.apache.cocoon.forms.formmodel.Widget;
import org.apache.commons.jxpath.JXPathContext;
import org.deals.framework.bean.ComponentType;
import org.deals.framework.core.WebCMS;

public class ResourcesRepo2WcTypeXPath extends AbstractCustomBinding {

	@Override
	protected void doLoad(Widget frmModel, JXPathContext context) throws Exception {
		ComponentType wct = (ComponentType) context.getValue("componentType");
		frmModel.setValue(wct.getCtId());
	}

	@Override
	protected void doSave(Widget frmModel, JXPathContext context) throws Exception {
		WebCMS wc = new WebCMS();
		Collection<ComponentType> wcts = (Collection<ComponentType>) wc.getComponentsType();
		for (Iterator iter = wcts.iterator(); iter.hasNext();) {
			ComponentType wct = (ComponentType) iter.next();
			if (wct.getCtId().equals(frmModel.getValue())) {
				context.setValue("componentType", wct);
			}
		}
	}

}
