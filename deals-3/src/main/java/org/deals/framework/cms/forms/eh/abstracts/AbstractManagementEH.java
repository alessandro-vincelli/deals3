
package org.deals.framework.cms.forms.eh.abstracts;

import javax.servlet.http.HttpSession;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.cocoon.components.flow.apples.AppleResponse;
import org.apache.cocoon.environment.Request;
import org.apache.cocoon.forms.binding.Binding;
import org.apache.cocoon.forms.event.AbstractFormHandler;
import org.apache.cocoon.forms.event.ActionEvent;
import org.apache.cocoon.forms.event.ValueChangedEvent;
import org.apache.cocoon.forms.formmodel.Form;
import org.apache.cocoon.forms.formmodel.Widget;
import org.apache.cocoon.forms.formmodel.WidgetState;
import org.apache.log4j.Logger;
import org.deals.framework.cms.forms.wrapper.ManagementFW;



 public abstract class AbstractManagementEH extends AbstractFormHandler { 

		protected Logger log = Logger.getLogger(getClass());	
        protected ServiceManager service = null;
        protected HttpSession session = null;   
        protected Request request = null;   
        protected AppleResponse response = null;   
    
    
	protected ManagementFW fw = null; 
    
		
	/**
	 * InitForm con il solo form
	 * @param f form da gestire
	 */
	/*public void initForm(Form f) {
		initForm(f, null, null, null, null);
	}*/
	
	/**
	 * InitForm con il form e l'oggetto Cocoon
	 * @param f form da gestire
	 * @param c oggetto cocoon
	 */
	/*public void initForm(Form f, ServiceManager service) {
		initForm(f, service, null, null, null);
	}*/
		
		
    /**
     *  Chiama tutte le onCreate del form e inizializza gli oggetti cocoon
     */
     
   
	  /**
     *  Chiama tutte le onCreate del form e inizializza gli oggetti cocoon
     */
	public void initForm(Form f, Request request, Binding binding) {

			
			this.request = request;
			session = request.getSession();
			fw = new ManagementFW(f);
			
			if (binding != null) {
			   fw.setBinding(binding);
			}
						
			formOnCreate(fw);
			itemsOnCreate(fw);
			categoriesOnCreate(fw);
			sectionsOnCreate(fw);
			
	}
       
         public void initForm(Form f, Binding binding, Request request) {		
  			//this.service = service;
  			if (request != null) {
  		         session = request.getSession(true);
  			} else {
  			  log.error("!!! Attenzione - AuthenticationEH inizializzato senza aver passato il parametro cocoon. Non si ha accesso agli oggetti Session, Request, etc...");
  			}
                          
            this.request = request;
                          
  			
  			fw = new ManagementFW(f);
  			

 			if (binding != null) {
 			   fw.setBinding(binding);
 			}

			
			formOnCreate(fw);
			itemsOnCreate(fw);
			categoriesOnCreate(fw);
			sectionsOnCreate(fw);
 			
         }
         
	public void handleActionEvent(ActionEvent arg0) {
            if (arg0.getSourceWidget().getParent().equals(fw.getForm())) {
               // tutte le chiamate pertinenti a widget che stanno nel Form
				      
               log.error("!!! Attenzione - ManagementEH non ho intercettato il widget che ha generato l'evento Action " + arg0.toString());
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
				      
               log.error("!!! Attenzione - ManagementEH non ho intercettato il widget che ha generato l'evento ValueChanged " + arg0.toString());
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

	public void formOnCreate(ManagementFW f) {
	}

	
	
	public void itemsOnCreate(ManagementFW f) {
	}			
	
	
	public void categoriesOnCreate(ManagementFW f) {
	}			
	
	
	public void sectionsOnCreate(ManagementFW f) {
	}			
	


//////###################################################################  ///////
//////###########       ON ACTION EVENT HANDLERS          ###############  ///////
//////###################################################################  ///////

	


    
    
////// ###################################################################  ///////
////// ###########       ON VALUE CHANGED EVENT HANDLERS   ###############  ///////
////// ###################################################################  ///////

    // qui ci devono finire tutti i field, booleanfield, 
    
	    
    
    
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
