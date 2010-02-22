
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
import org.apache.cocoon.forms.formmodel.RepeaterAction;
import org.apache.cocoon.forms.formmodel.Widget;
import org.apache.cocoon.forms.formmodel.WidgetState;
import org.deals.framework.cms.forms.wrapper.GestioneUtentiFW;



 public abstract class AbstractUsersManagerEH extends AbstractFormHandler { 

	protected ServiceManager service = null;
	protected HttpSession session = null;
    protected Request request = null;
    protected AppleResponse response = null;

	protected GestioneUtentiFW fw = null; 
    
		
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
			fw = new GestioneUtentiFW(f);
			
			if (binding != null) {
			   fw.setBinding(binding);
			}

			formOnCreate(fw);
			utentiOnCreate(fw);
			pagefirstOnCreate(fw);
			pageprevOnCreate(fw);
			pagenextOnCreate(fw);
			pagelastOnCreate(fw);
			topageOnCreate(fw);
			pagecustomOnCreate(fw);
			sortnaturalOnCreate(fw);
			removeutenteOnCreate(fw);
			addutenteOnCreate(fw);
			
	}
       
	public void handleActionEvent(ActionEvent arg0) {
            if (arg0.getSourceWidget().getParent().equals(fw.getForm())) {
               // tutte le chiamate pertinenti a widget che stanno nel Form
			   
				  if (arg0.getSourceWidget().equals(fw.getPagefirst())) {
					   pagefirstOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getPageprev())) {
					   pageprevOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getPagenext())) {
					   pagenextOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getPagelast())) {
					   pagelastOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getPagecustom())) {
					   pagecustomOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getSortnatural())) {
					   sortnaturalOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getRemoveutente())) {
					   removeutenteOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getAddutente())) {
					   addutenteOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}

				  if (arg0.getSourceWidget().equals(fw.getUtentiaction())) {
					   utentiactionOnAction(fw, (Action)arg0.getSourceWidget());          
					   return;
				 	}
				  
				  
               System.err.println("!!! Attenzione - GestioneUtentiEH non ho intercettato il widget che ha generato l'evento Action " + arg0.toString());
               return; // non ho intercettato il widget che ha generato l'evento
            }	
            
            // lista di if per controllare se il parent ?? magari un repeater, o meglio una repeater row
            //if (arg0.getSourceWidget().getParent().getParent().equals(fw.getRepeater()) {
               // vecchia intercettazione tramite ID
            //} 

		
            if (arg0.getSourceWidget().getFullName().indexOf("utenti")>-1) {
			   
			   
            } 
        		
        
	        
	        
	}

	public void handleValueChangedEvent(ValueChangedEvent arg0) {
	        
            if (arg0.getSourceWidget().getParent().equals(fw.getForm())) {
               // tutte le chiamate pertinenti a widget che stanno nel Form
				 
				  if (arg0.getSourceWidget().equals(fw.getTopage())) {
					   topageOnValueChanged(fw, (Field)arg0.getSourceWidget(), (Integer)arg0.getNewValue(), (Integer)arg0.getOldValue());          
					   return;
				 	}
				      
               System.err.println("!!! Attenzione - GestioneUtentiEH non ho intercettato il widget che ha generato l'evento ValueChanged " + arg0.toString());
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

	public void formOnCreate(GestioneUtentiFW f) {
	}

	
	
	public void utentiOnCreate(GestioneUtentiFW f) {
	}			
	
	
	public void pagefirstOnCreate(GestioneUtentiFW f) {
	}			
	
	
	public void pageprevOnCreate(GestioneUtentiFW f) {
	}			
	
	
	public void pagenextOnCreate(GestioneUtentiFW f) {
	}			
	
	
	public void pagelastOnCreate(GestioneUtentiFW f) {
	}			
	
	
	public void topageOnCreate(GestioneUtentiFW f) {
	}			
	
	
	public void pagecustomOnCreate(GestioneUtentiFW f) {
	}			
	
	
	public void sortnaturalOnCreate(GestioneUtentiFW f) {
	}			
	
	
	public void removeutenteOnCreate(GestioneUtentiFW f) {
	}			
	
	
	public void addutenteOnCreate(GestioneUtentiFW f) {
	}			
	


//////###################################################################  ///////
//////###########       ON ACTION EVENT HANDLERS          ###############  ///////
//////###################################################################  ///////

	
	public void pagefirstOnAction(GestioneUtentiFW f, RepeaterAction source) {
	}			
	
	public void pageprevOnAction(GestioneUtentiFW f, RepeaterAction source) {
	}			
	
	public void pagenextOnAction(GestioneUtentiFW f, RepeaterAction source) {
	}			
	
	public void pagelastOnAction(GestioneUtentiFW f, RepeaterAction source) {
	}			
	
	public void pagecustomOnAction(GestioneUtentiFW f, RepeaterAction source) {
	}			
	
	public void sortnaturalOnAction(GestioneUtentiFW f, RepeaterAction source) {
	}			
	
	public void removeutenteOnAction(GestioneUtentiFW f, RepeaterAction source) {
	}			
	
	public void addutenteOnAction(GestioneUtentiFW f, RepeaterAction source) {
	}			
			
	public void utentiactionOnAction(GestioneUtentiFW f, Action source) {
	}			
	


    
    
////// ###################################################################  ///////
////// ###########       ON VALUE CHANGED EVENT HANDLERS   ###############  ///////
////// ###################################################################  ///////

    // qui ci devono finire tutti i field, booleanfield, 
    
	
	public void topageOnValueChanged(GestioneUtentiFW f, Field source, Integer newValue, Integer oldValue) {
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
