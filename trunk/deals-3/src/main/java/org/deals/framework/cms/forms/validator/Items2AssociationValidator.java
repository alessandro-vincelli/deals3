package org.deals.framework.cms.forms.validator;

import org.apache.cocoon.forms.formmodel.Repeater;
import org.apache.cocoon.forms.formmodel.Widget;
import org.apache.cocoon.forms.formmodel.Repeater.RepeaterRow;
import org.apache.cocoon.forms.validation.ValidationError;
import org.apache.cocoon.forms.validation.ValidationErrorAware;
import org.apache.cocoon.forms.validation.WidgetValidator;

/**
 * Check the category association to section 
 * 
 * @author alessandro vincelli
 *
 */
public class Items2AssociationValidator implements WidgetValidator {
	
	private static final String VALIDATION_MESSAGE_KEY = "Selezionare almeno una Sezione o una Categoria come padre della Scheda"; 
	
	public boolean validate(Widget widget) {
		Repeater rep = (Repeater) widget;
		int check = 0;
		for (int i = 0; i < rep.getSize(); i++) {
			RepeaterRow rr = rep.getRow(i);
			if (rr.lookupWidget("checksection").getValue().toString() == "true") {
				check = check + 1;
			}
			Repeater repCate = (Repeater) rr.lookupWidget("categories");
			for (int k = 0; k < repCate.getSize(); k++) {
				RepeaterRow rrCate = repCate.getRow(k);
				if (rrCate.lookupWidget("checkcategory").getValue().toString() == "true") {
					check = check + 1;
				}
			}
		}
		
		if (check > 0) {
			return true;
		} else {
            ((ValidationErrorAware) widget).setValidationError(new ValidationError(VALIDATION_MESSAGE_KEY));
    		check = 0;
    		for (int i = 0; i < rep.getSize(); i++) {
    			RepeaterRow rr = rep.getRow(i);
    			((ValidationErrorAware) rr.lookupWidget("checksection")).setValidationError(new ValidationError(VALIDATION_MESSAGE_KEY));
    			Repeater repCate = (Repeater) rr.lookupWidget("categories");
    			for (int k = 0; k < repCate.getSize(); k++) {
    				RepeaterRow rrCate = repCate.getRow(k);
    				((ValidationErrorAware) rrCate.lookupWidget("checkcategory")).setValidationError(new ValidationError(VALIDATION_MESSAGE_KEY));
    			}
    		}
            return false;
		}
	}
}