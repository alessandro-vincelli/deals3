package org.deals.framework.cms.forms.eh;

import javax.servlet.http.HttpSession;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.forms.binding.Binding;
import org.apache.cocoon.forms.binding.BindingException;
import org.apache.cocoon.forms.event.AbstractFormHandler;
import org.apache.cocoon.forms.event.ActionEvent;
import org.apache.cocoon.forms.event.RepeaterEvent;
import org.apache.cocoon.forms.event.RepeaterEventAction;
import org.apache.cocoon.forms.event.RepeaterListener;
import org.apache.cocoon.forms.event.ValueChangedEvent;
import org.apache.cocoon.forms.formmodel.Field;
import org.apache.cocoon.forms.formmodel.Form;
import org.apache.cocoon.forms.formmodel.Repeater;
import org.apache.cocoon.forms.formmodel.Repeater.RepeaterRow;
import org.apache.cocoon.forms.validation.ValidationError;
import org.apache.log4j.Logger;
import org.deals.framework.bean.Comments;
import org.deals.framework.cms.forms.formbean.CommentsManagerBinder;
import org.deals.framework.cms.forms.formbean.CommentsManagerFB;
import org.deals.framework.core.WebCMS;

public class CommentsManagerEH extends AbstractFormHandler {

	private HttpSession session = null;
	private Request request = null;
	private Form f;
	private Binding binding;
	private Logger log = Logger.getLogger(getClass());
	private CommentsManagerBinder bm;
	private CommentsManagerFB fb;
	private WebCMS cms;

	public void setCms(WebCMS cms) {
		this.cms = cms;
	}

	public void initForm(Form f, Request request, Binding binding) {
		this.request = request;
		this.session = request.getSession();
		this.f = f;
		this.binding = binding;

		//f.getUtenti().getRepeater().addRepeaterListener(new UtentiListener());
		((Repeater)f.lookupWidget("comments")).addRepeaterListener(new CommentsListener());
		bm = new CommentsManagerBinder(session, cms);
		fb = bm.getFormBean();

		try {
			this.load(fb);
		} catch (BindingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carica l'oggetto sul form
	 * 
	 * @param o
	 *            oggetto da caricare
	 * @throws BindingException
	 *             problemi in fase di binding
	 */
	private void load(Object o) throws BindingException {
		try {
			if (binding != null) {
				binding.loadFormFromModel(f, o);

			} else {
				throw new BindingException("Error! - No binding setted");
			}
		} catch (BindingException bex) {
			throw bex;
		}
	}

	/**
	 * Salva i dati del form sull'oggetto
	 * 
	 * @param o
	 *            oggetto su cui salvare
	 * @throws BindingException
	 *             problemi in fase di binding
	 */
	private void save(Object o) throws BindingException {
		try {
			if (binding != null) {
				binding.saveFormToModel(f, o);
			} else {
				throw new BindingException("Error! - No binding setted");
			}
		} catch (BindingException bex) {
			throw bex;
		}
	}

	@Override
	public void handleActionEvent(ActionEvent actionEvent) {
		if (actionEvent.getSourceWidget().getFullName().equals("commentsubmit")) {
			try {
				this.save(fb);
				bm.saveFormBean(fb);
				this.load(fb);
			} catch (BindingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		/*if (actionEvent.getSourceWidget().getFullName().equals("commentaction")) {
			try {
				this.save(fb);
				bm.saveFormBean(fb);
				this.load(fb);
			} catch (BindingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}*/
	}

	@Override
	public void handleValueChangedEvent(ValueChangedEvent valueChangedEvent) {
		// TODO Auto-generated method stub

	}

	class CommentsListener implements RepeaterListener {

		public void repeaterModified(RepeaterEvent event) {
			if (event.getAction().equals(RepeaterEventAction.ROW_DELETING)) {
				RepeaterRow  rr = (RepeaterRow)((Repeater)event.getSourceWidget()).getRow(event.getRow());
				Long id = (Long)rr.lookupWidget("co_id").getValue();
				cms.deleteComments(cms.getComments(id));
				return;	
			}
		}

	}	

	
	
}
