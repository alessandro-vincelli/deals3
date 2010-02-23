package org.deals.cocoon.forms.eh;

import org.apache.cocoon.forms.event.ValueChangedEvent;

/**
 * @author Alessandro Vincelli
 * @email a.vincelli@gmail.com
 * Created on Aug 29, 2008
 */
public class HeaderValueChanged implements org.apache.cocoon.forms.event.ValueChangedListener {

	/* (non-Javadoc)
	 * @see org.apache.cocoon.forms.event.ValueChangedListener#valueChanged(org.apache.cocoon.forms.event.ValueChangedEvent)
	 */
	public void valueChanged(ValueChangedEvent event) {
		if(event.getOldValue() != null){
			event.getSourceWidget().getForm().lookupWidget("message").setValue("The value of the Header is changed.");
		}
	}

}
