package org.deals.cocoon.forms.manager;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.forms.binding.Binding;
import org.apache.cocoon.forms.binding.BindingException;
import org.apache.cocoon.forms.formmodel.Form;
import org.deals.cocoon.forms.data.SimpleFormMock;

public class SimpleFormManager {
	
	public void load(Form f , Request request, Binding binding) throws BindingException{
		// Simple mock bean, instead of the mock we can call DB or business logic to retrieve real data
		SimpleFormMock bean = new SimpleFormMock();
		binding.loadFormFromModel(f, bean);
	}

	public void persist(Form  f, Request request, Binding binding){
		/*TODO
				f.save(fb);
				as.saveFormBean(fb, f);
				f.setAttribute("pageid", fb.getBean().getWpId());
				*/
	}

}
