
package org.deals.framework.cms.forms.eh.abstracts;

import javax.servlet.http.HttpSession;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.cocoon.components.flow.apples.AppleRequest;
import org.apache.cocoon.components.flow.apples.AppleResponse;
import org.apache.cocoon.forms.binding.Binding;
import org.apache.cocoon.forms.event.AbstractFormHandler;
import org.apache.cocoon.forms.event.ActionEvent;
import org.apache.cocoon.forms.event.ValueChangedEvent;
import org.apache.cocoon.forms.formmodel.Field;
import org.apache.cocoon.forms.formmodel.Form;
import org.apache.cocoon.forms.formmodel.Submit;
import org.apache.cocoon.forms.formmodel.Widget;
import org.apache.cocoon.forms.formmodel.WidgetState;
import org.apache.log4j.Logger;
import org.deals.framework.cms.forms.wrapper.ContactsFW;



 public abstract class AbstractContactsEH extends AbstractFormHandler { 

		protected Logger log = Logger.getLogger(getClass());	
        protected ServiceManager service = null;
        protected HttpSession session = null;   
        protected AppleRequest request = null;   
        protected AppleResponse response = null;   
    
	protected ContactsFW fw = null; 
    
		
	/**
	 * InitForm con il solo form
	 * @param f form da gestire
	 */
	public void initForm(Form f) {
		initForm(f, null, null, null, null);
	}
	
	/**
	 * InitForm con il form e l'oggetto Cocoon
	 * @param f form da gestire
	 * @param c oggetto cocoon
	 */
	public void initForm(Form f, ServiceManager service) {
		initForm(f, service, null, null, null);
	}
		
		
    /**
     *  Chiama tutte le onCreate del form e inizializza gli oggetti cocoon
     */
     
   
         public void initForm(Form f, ServiceManager service, AppleRequest request, AppleResponse response, Binding binding) {		
			this.service = service;
			if (request != null) {
		         session = request.getCocoonRequest().getSession();
			} else {
			  log.error("!!! Attenzione - ContactsEH inizializzato senza aver passato il parametro cocoon. Non si ha accesso agli oggetti Session, Request, etc...");
			}
                        
			this.request = request;
            this.response = response;
			
			fw = new ContactsFW(f);
			
			if (binding != null) {
			   fw.setBinding(binding);
			}

			
		
						
			formOnCreate(fw);
			first_nameOnCreate(fw);
			last_nameOnCreate(fw);
			codeOnCreate(fw);
			emailOnCreate(fw);
			phone_numberOnCreate(fw);
			contentOnCreate(fw);
			contactsubmitOnCreate(fw);
			
	}
       
	public void handleActionEvent(ActionEvent arg0) {
            if (arg0.getSourceWidget().getParent().equals(fw.getForm())) {
               // tutte le chiamate pertinenti a widget che stanno nel Form
				 
				  if (arg0.getSourceWidget().equals(fw.getContactsubmit())) {
					   contactsubmitOnAction(fw, (Submit)arg0.getSourceWidget());          
					   return;
				 	}
				      
               log.error("!!! Attenzione - ContactsEH non ho intercettato il widget che ha generato l'evento Action " + arg0.toString());
               return; // non ho intercettato il widget che ha generato l'evento
            }	
            
            // lista di if per controllare se il parent ?? magari un repeater, o meglio una repeater row
            //if (arg0.getSourceWidget().getParent().getParent().equals(fw.getRepeater()) {
               // vecchia intercettazione tramite ID
            //} 

				
	        
	        
	}

	public void handleValueChangedEvent(ValueChangedEvent arg0) {
	        
            if (arg0.getSourceWidget().getParent().equals(fw.getForm())) {
               // tutte le chiamate pertinenti a widget che stanno nel Form
				 
				  if (arg0.getSourceWidget().equals(fw.getFirstName())) {
					   first_nameOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getLastName())) {
					   last_nameOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getCode())) {
					   codeOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getEmail())) {
					   emailOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getPhoneNumber())) {
					   phone_numberOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getContent())) {
					   contentOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				      
               log.error("!!! Attenzione - ContactsEH non ho intercettato il widget che ha generato l'evento ValueChanged " + arg0.toString());
               return; // non ho intercettato il widget che ha generato l'evento
            }	
		
					
            
            // lista di if per controllare se il parent ?? magari un repeater, o meglio una repeater row
            //if (arg0.getSourceWidget().getParent().getParent().equals(fw.getRepeater()) {
               // vecchia intercettazione tramite ID
            //} 
	}



//////###################################################################  ///////
//////###########       ON CREATE HANDLERS          ###############  ///////
//////###################################################################  ///////

	public void formOnCreate(ContactsFW f) {
	}

	
	
	public void first_nameOnCreate(ContactsFW f) {
	}			
	
	
	public void last_nameOnCreate(ContactsFW f) {
	}			
	
	
	public void codeOnCreate(ContactsFW f) {
	}			
	
	
	public void emailOnCreate(ContactsFW f) {
	}			
	
	
	public void phone_numberOnCreate(ContactsFW f) {
	}			
	
	
	public void contentOnCreate(ContactsFW f) {
	}			
	
	
	public void contactsubmitOnCreate(ContactsFW f) {
	}			
	


//////###################################################################  ///////
//////###########       ON ACTION EVENT HANDLERS          ###############  ///////
//////###################################################################  ///////

	
	public void contactsubmitOnAction(ContactsFW f, Submit source) {
	}			
	


    
    
////// ###################################################################  ///////
////// ###########       ON VALUE CHANGED EVENT HANDLERS   ###############  ///////
////// ###################################################################  ///////

    // qui ci devono finire tutti i field, booleanfield, 
    
	
	public void first_nameOnValueChanged(ContactsFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void last_nameOnValueChanged(ContactsFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void codeOnValueChanged(ContactsFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void emailOnValueChanged(ContactsFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void phone_numberOnValueChanged(ContactsFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void contentOnValueChanged(ContactsFW f, Field source, String newValue, String oldValue) {
	}			
	    
    
    
//////////////////////////////////////////////////////////////////////////////////////    
//////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////    

    
    protected void enableWidget(Widget w) {
        w.setState(WidgetState.ACTIVE);
    }
    
    protected void disableWidget(Widget w) {
        w.setState(WidgetState.DISABLED);
    }   
    
    protected void hideWidget(Widget w) {
        w.setState(WidgetState.INVISIBLE);
    }   
    
    protected void readonlyWidget(Widget w) {
        w.setState(WidgetState.OUTPUT);
    }   
    
		
 		
 } 		
