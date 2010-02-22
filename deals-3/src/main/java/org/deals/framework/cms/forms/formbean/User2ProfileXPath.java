package org.deals.framework.cms.forms.formbean;

import java.util.Iterator;
import java.util.List;

import org.apache.cocoon.forms.binding.AbstractCustomBinding;
import org.apache.cocoon.forms.formmodel.Widget;
import org.apache.commons.jxpath.JXPathContext;
import org.deals.framework.bean.UserProfile;

public class User2ProfileXPath extends  AbstractCustomBinding {

	@Override
	protected void doLoad(Widget frmModel, JXPathContext context) throws Exception {
        UserProfile up = (UserProfile)context.getValue("profile");
        frmModel.setValue(up.getUpId());
	}

	@Override
	protected void doSave(Widget frmModel, JXPathContext context) throws Exception {
		if (context.getValue("../profileSL") != null){
			List<UserProfile> wcs =  (List<UserProfile>)context.getValue("../profileSL");
			for (Iterator iter = wcs.iterator(); iter.hasNext();) {
				UserProfile up = (UserProfile) iter.next();
				if (up.getUpId().equals(frmModel.getValue())){
					context.setValue("profile", up);
				}
			}
		}
	}

}
