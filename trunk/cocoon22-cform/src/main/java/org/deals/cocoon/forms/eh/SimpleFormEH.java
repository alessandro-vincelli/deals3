package org.deals.cocoon.forms.eh;

import org.apache.cocoon.forms.event.AbstractFormHandler;
import org.apache.cocoon.forms.event.ActionEvent;
import org.apache.cocoon.forms.event.ValueChangedEvent;
import org.apache.cocoon.forms.formmodel.WidgetState;

public class SimpleFormEH extends AbstractFormHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.cocoon.forms.event.AbstractFormHandler#handleActionEvent(org
	 * .apache.cocoon.forms.event.ActionEvent)
	 */
	@Override
	public void handleActionEvent(ActionEvent actionEvent) {
		if (actionEvent.getSourceWidget().getId().equals("action_submit")) {
			actionSubmitActionEvent(actionEvent);
		}
		if (actionEvent.getSourceWidget().getId().equals("form_submit")) {
			formSubmitActionEvent(actionEvent);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.cocoon.forms.event.AbstractFormHandler#handleValueChangedEvent
	 * (org.apache.cocoon.forms.event.ValueChangedEvent)
	 */
	@Override
	public void handleValueChangedEvent(ValueChangedEvent valueChangedEvent) {
		if (valueChangedEvent.getSourceWidget().getId().equals("noexpiration")) {
			noexpirationValueChanged(valueChangedEvent);
		}
	}

	/**
	 * On check noExpiration, the expiration date field becomes disabled and
	 * vice-versa
	 * 
	 * @param valueChangedEvent
	 */
	private void noexpirationValueChanged(ValueChangedEvent valueChangedEvent) {
		if ((Boolean) valueChangedEvent.getNewValue()) {
			valueChangedEvent.getSourceWidget().getForm().lookupWidget("expiration").setState(WidgetState.DISABLED);
			valueChangedEvent.getSourceWidget().getForm().lookupWidget("message").setValue("Expiration date field disabled after click on \"Never\" check box ");
		} else {
			valueChangedEvent.getSourceWidget().getForm().lookupWidget("expiration").setState(WidgetState.ACTIVE);
			valueChangedEvent.getSourceWidget().getForm().lookupWidget("message").setValue("Expiration date field activated after click on \"Never\" check box ");
		}
	}

	/**
	 * Perform validation and write results on message field
	 * 
	 * @param actionEvent
	 */
	private void actionSubmitActionEvent(ActionEvent actionEvent) {
		boolean isformValid = actionEvent.getSourceWidget().getForm().validate();
		if (isformValid) {
			actionEvent.getSourceWidget().getForm().lookupWidget("message").setValue("The Form is valid! The submit action force the validation on the form!");
		} else {
			actionEvent.getSourceWidget().getForm().lookupWidget("message").setValue("The Form is not valid! The submit action force the validation on the form!");
		}
	}

	/**
	 * Perform validation and write results on message field
	 * 
	 * @param actionEvent
	 */
	private void formSubmitActionEvent(ActionEvent actionEvent) {
		actionEvent.getSourceWidget().getForm().lookupWidget("message").setValue("Form submitted, but the form is not valid.");
	}
}
