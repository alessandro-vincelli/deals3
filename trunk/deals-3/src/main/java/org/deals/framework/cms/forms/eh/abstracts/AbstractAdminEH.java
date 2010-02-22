
package org.deals.framework.cms.forms.eh.abstracts;
import javax.servlet.http.HttpSession;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.cocoon.components.flow.apples.AppleResponse;
import org.apache.cocoon.environment.Request;
import org.apache.cocoon.forms.binding.Binding;
import org.apache.cocoon.forms.event.AbstractFormHandler;
import org.apache.cocoon.forms.event.ActionEvent;
import org.apache.cocoon.forms.event.ValueChangedEvent;
import org.apache.cocoon.forms.formmodel.Action;
import org.apache.cocoon.forms.formmodel.Field;
import org.apache.cocoon.forms.formmodel.Form;
import org.apache.cocoon.forms.formmodel.Widget;
import org.apache.cocoon.forms.formmodel.WidgetState;
import org.deals.framework.cms.forms.wrapper.AdminFW;


 public abstract class AbstractAdminEH extends AbstractFormHandler { 

	protected ServiceManager service = null;
	protected HttpSession session = null;
    	protected Request request = null;
        protected AppleResponse response = null;

	protected AdminFW fw = null; 
    
		
	/**
	 * InitForm con il solo form
	 * @param f form da gestire
	 */
	//public void initForm(Form f) {
	//	initForm(f, null, null, null, null);
	//}
	
	/**
	 * InitForm con il form, il serviceManager, la request e la response
	 * @param f form da gestire
	 * @param service serviceManager, fa le veci dell'oggetto Cocoon
	 * @param request oggetto request
	 * @param response oggetto response
	 */
	//public void initForm(Form f, ServiceManager service, Request request, AppleResponse response) {
	//	initForm(f, service, request, response, null);
	//}
		
		
    /**
     *  Chiama tutte le onCreate del form e inizializza gli oggetti cocoon
     */
	public void initForm(Form f, Request request, Binding binding) {

			this.request = request;
			session = request.getSession();
			fw = new AdminFW(f);
			
			if (binding != null) {
			   fw.setBinding(binding);
			}

			formOnCreate(fw);
			tab_stateOnCreate(fw);
			log_messaggesOnCreate(fw);
			searchreindexOnCreate(fw);
			
	}
       
	public void handleActionEvent(ActionEvent arg0) {
            if (arg0.getSourceWidget().getParent().equals(fw.getForm())) {
               // tutte le chiamate pertinenti a widget che stanno nel Form
			   
				  if (arg0.getSourceWidget().equals(fw.getSearchreindex())) {
					   searchreindexOnAction(fw, (Action)arg0.getSourceWidget());          
					   return;
				 	}
				      
               System.err.println("!!! Attenzione - AdminEH non ho intercettato il widget che ha generato l'evento Action " + arg0.toString());
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
				 
				  if (arg0.getSourceWidget().equals(fw.getTabState())) {
					   tab_stateOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				      
               System.err.println("!!! Attenzione - AdminEH non ho intercettato il widget che ha generato l'evento ValueChanged " + arg0.toString());
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

	public void formOnCreate(AdminFW f) {
	}

	
	
	public void tab_stateOnCreate(AdminFW f) {
	}			
	
	
	public void log_messaggesOnCreate(AdminFW f) {
	}			
	
	
	public void searchreindexOnCreate(AdminFW f) {
	}			
	


//////###################################################################  ///////
//////###########       ON ACTION EVENT HANDLERS          ###############  ///////
//////###################################################################  ///////

	
	public void searchreindexOnAction(AdminFW f, Action source) {
	}			
			
	


    
    
////// ###################################################################  ///////
////// ###########       ON VALUE CHANGED EVENT HANDLERS   ###############  ///////
////// ###################################################################  ///////

    // qui ci devono finire tutti i field, booleanfield, 
    
	
	public void tab_stateOnValueChanged(AdminFW f, Field source, String newValue, String oldValue) {
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
