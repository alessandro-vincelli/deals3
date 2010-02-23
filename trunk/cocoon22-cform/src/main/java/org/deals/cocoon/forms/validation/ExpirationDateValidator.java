package org.deals.cocoon.forms.validation;

import java.util.Date;

import org.apache.cocoon.forms.formmodel.Field;
import org.apache.cocoon.forms.formmodel.Widget;
import org.apache.cocoon.forms.validation.ValidationError;

/**
 * @author Alessandro Vincelli
 * @email a.vincelli@gmail.com Created on Aug 26, 2008
 */
public class ExpirationDateValidator implements org.apache.cocoon.forms.validation.WidgetValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.cocoon.forms.validation.WidgetValidator#validate(org.apache
	 * .cocoon.forms.formmodel.Widget)
	 */
	public boolean validate(Widget widget) {

		if (widget.getValue() != null && widget.lookupWidget("../date").getValue() != null) {
			Date expDate = (Date) widget.getValue();
			Date startDate = (Date) widget.lookupWidget("../date").getValue();
			if (!(expDate.after(startDate))) {
				((Field) widget).setValidationError(new ValidationError("The expiration date must be after the start date."));
				return false;
			}
		}
		return true;
	}

}
