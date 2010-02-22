package org.deals.framework.cms.forms.formbean;

import java.util.Collection;
import java.util.Iterator;

import org.apache.cocoon.forms.binding.AbstractCustomBinding;
import org.apache.cocoon.forms.formmodel.Widget;
import org.apache.commons.jxpath.JXPathContext;
import org.deals.framework.bean.WebComponent;

public class Item2Images2WebComponentXPath extends AbstractCustomBinding {

	@Override
	protected void doLoad(Widget frmModel, JXPathContext context) throws Exception {
		WebComponent wct = (WebComponent) context.getValue("webComponent");
		frmModel.setValue(wct.getWcId());
	}

	@Override
	protected void doSave(Widget frmModel, JXPathContext context) throws Exception {
		if (context.getValue("../imagesresources") != null) {
			Collection<WebComponent> wcs = (Collection<WebComponent>) context.getValue("../imagesresources");
			for (Iterator iter = wcs.iterator(); iter.hasNext();) {
				WebComponent wc = (WebComponent) iter.next();
				if (wc.getWcId().equals(frmModel.getValue())) {
					context.setValue("webComponent", wc);
				}
			}
		}
	}
}
