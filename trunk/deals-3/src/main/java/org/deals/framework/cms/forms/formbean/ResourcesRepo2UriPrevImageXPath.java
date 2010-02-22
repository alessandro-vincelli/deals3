package org.deals.framework.cms.forms.formbean;

import org.apache.cocoon.forms.binding.AbstractCustomBinding;
import org.apache.cocoon.forms.formmodel.Widget;
import org.apache.commons.jxpath.JXPathContext;
import org.deals.framework.bean.WebResource;

public class ResourcesRepo2UriPrevImageXPath extends AbstractCustomBinding {

	@Override
	protected void doLoad(Widget frmModel, JXPathContext context) throws Exception {
		if (context.getContextBean() instanceof WebResource) {
			WebResource wr = (WebResource) context.getContextBean();
			frmModel.setValue(wr.getWebResourcePathOnSite());
		}
	}

	@Override
	protected void doSave(Widget frmModel, JXPathContext context) throws Exception {
	}

}
