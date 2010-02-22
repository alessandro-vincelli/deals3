
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
import org.deals.framework.cms.forms.wrapper.DealsUserFW;



 public abstract class AbstractDealsUserEH extends AbstractFormHandler { 

		protected Logger log = Logger.getLogger(getClass());	
        protected ServiceManager service = null;
        protected HttpSession session = null;   
        protected AppleRequest request = null;   
        protected AppleResponse response = null;   
    
	protected DealsUserFW fw = null; 
    
		
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
			  log.error("!!! Attenzione - DealsUserEH inizializzato senza aver passato il parametro cocoon. Non si ha accesso agli oggetti Session, Request, etc...");
			}
                        
                        this.request = request;
                        this.response = response;
			
			fw = new DealsUserFW(f);
			
			if (binding != null) {
			   fw.setBinding(binding);
			}

			
		
						
			formOnCreate(fw);
			us_idOnCreate(fw);
			us_roleOnCreate(fw);
			us_usernameOnCreate(fw);
			us_passwordOnCreate(fw);
			usersubmitOnCreate(fw);
			
	}
       
	public void handleActionEvent(ActionEvent arg0) {
            if (arg0.getSourceWidget().getParent().equals(fw.getForm())) {
               // tutte le chiamate pertinenti a widget che stanno nel Form
				 
				  if (arg0.getSourceWidget().equals(fw.getUsersubmit())) {
					   usersubmitOnAction(fw, (Submit)arg0.getSourceWidget());          
					   return;
				 	}
				      
               log.error("!!! Attenzione - DealsUserEH non ho intercettato il widget che ha generato l'evento Action " + arg0.toString());
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
				 
				  if (arg0.getSourceWidget().equals(fw.getUsId())) {
					   us_idOnValueChanged(fw, (Field)arg0.getSourceWidget(), (Integer)arg0.getNewValue(), (Integer)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getUsRole())) {
					   us_roleOnValueChanged(fw, (Field)arg0.getSourceWidget(), (Integer)arg0.getNewValue(), (Integer)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getUsUsername())) {
					   us_usernameOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getUsPassword())) {
					   us_passwordOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				      
               log.error("!!! Attenzione - DealsUserEH non ho intercettato il widget che ha generato l'evento ValueChanged " + arg0.toString());
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

	public void formOnCreate(DealsUserFW f) {
	}

	
	
	public void us_idOnCreate(DealsUserFW f) {
	}			
	
	
	public void us_roleOnCreate(DealsUserFW f) {
	}			
	
	
	public void us_usernameOnCreate(DealsUserFW f) {
	}			
	
	
	public void us_passwordOnCreate(DealsUserFW f) {
	}			
	
	
	public void usersubmitOnCreate(DealsUserFW f) {
	}			
	


//////###################################################################  ///////
//////###########       ON ACTION EVENT HANDLERS          ###############  ///////
//////###################################################################  ///////

	
	public void usersubmitOnAction(DealsUserFW f, Submit source) {
	}			
	


    
    
////// ###################################################################  ///////
////// ###########       ON VALUE CHANGED EVENT HANDLERS   ###############  ///////
////// ###################################################################  ///////

    // qui ci devono finire tutti i field, booleanfield, 
    
	
	public void us_idOnValueChanged(DealsUserFW f, Field source, Integer newValue, Integer oldValue) {
	}			
	
	public void us_roleOnValueChanged(DealsUserFW f, Field source, Integer newValue, Integer oldValue) {
	}			
	
	public void us_usernameOnValueChanged(DealsUserFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void us_passwordOnValueChanged(DealsUserFW f, Field source, String newValue, String oldValue) {
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
