package org.deals.framework.cms.forms.eh;

import org.apache.cocoon.forms.event.ValueChangedEvent;
import org.apache.cocoon.forms.event.ValueChangedListener;

public class AuthenticationEvent implements ValueChangedListener
{

	public void valueChanged(ValueChangedEvent arg0) {
		arg0.getSourceWidget().setValue("pippo");
	}
	
}