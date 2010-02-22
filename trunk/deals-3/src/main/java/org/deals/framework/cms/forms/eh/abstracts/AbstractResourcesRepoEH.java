
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
import org.apache.cocoon.forms.formmodel.Submit;
import org.apache.cocoon.forms.formmodel.Widget;
import org.apache.cocoon.forms.formmodel.WidgetState;
import org.apache.cocoon.forms.formmodel.Repeater.RepeaterRow;
import org.deals.framework.cms.forms.wrapper.ResourcesRepoFW;



 public abstract class AbstractResourcesRepoEH extends AbstractFormHandler { 

	protected ServiceManager service = null;
	protected HttpSession session = null;
    	protected Request request = null;
        protected AppleResponse response = null;

	protected ResourcesRepoFW fw = null; 
    
		
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
			fw = new ResourcesRepoFW(f);
			
			if (binding != null) {
			   fw.setBinding(binding);
			}

			formOnCreate(fw);
			webcomponentsOnCreate(fw);
			removecomponentsOnCreate(fw);
			pagefirstOnCreate(fw);
			pageprevOnCreate(fw);
			pagenextOnCreate(fw);
			pagelastOnCreate(fw);
			topageOnCreate(fw);
			pagecustomOnCreate(fw);
			sortbydateOnCreate(fw);
			sortnaturalOnCreate(fw);
			newwebcomponentsOnCreate(fw);
			addnewcomponentsOnCreate(fw);
			removenewcomponentsOnCreate(fw);
			componentssubmitOnCreate(fw);
			
	}
       
	public void handleActionEvent(ActionEvent arg0) {
            if (arg0.getSourceWidget().getParent().equals(fw.getForm())) {
               // tutte le chiamate pertinenti a widget che stanno nel Form
			   
				  if (arg0.getSourceWidget().equals(fw.getRemovecomponents())) {
					   removecomponentsOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
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
				 
				  if (arg0.getSourceWidget().equals(fw.getSortbydate())) {
					   sortbydateOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getSortnatural())) {
					   sortnaturalOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getAddnewcomponents())) {
					   addnewcomponentsOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getRemovenewcomponents())) {
					   removenewcomponentsOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getComponentssubmit())) {
					   componentssubmitOnAction(fw, (Submit)arg0.getSourceWidget());          
					   return;
				 	}
				      
               System.err.println("!!! Attenzione - ResourcesRepoEH non ho intercettato il widget che ha generato l'evento Action " + arg0.toString());
               return; // non ho intercettato il widget che ha generato l'evento
            }	
            
            // lista di if per controllare se il parent ?? magari un repeater, o meglio una repeater row
            //if (arg0.getSourceWidget().getParent().getParent().equals(fw.getRepeater()) {
               // vecchia intercettazione tramite ID
            //} 

		
            if (arg0.getSourceWidget().getFullName().indexOf("webcomponents")>-1) {
			   
	            if (arg0.getSourceWidget().getDefinition().equals(fw.getwebcomponents().getDeleteitem(0).getDefinition())) {            	
					// chiamata dell'evento action
                    webcomponents_deleteitemOnAction(fw, (Action)arg0.getSourceWidget(), Integer.parseInt(((RepeaterRow)arg0.getSourceWidget().getParent()).getId()));					
	            }
			   
			   
            } 
        
            if (arg0.getSourceWidget().getFullName().indexOf("newwebcomponents")>-1) {
			   
			   
            } 
        		
        
	        
	        
	}

	public void handleValueChangedEvent(ValueChangedEvent arg0) {
	        
            if (arg0.getSourceWidget().getParent().equals(fw.getForm())) {
               // tutte le chiamate pertinenti a widget che stanno nel Form
				 
				  if (arg0.getSourceWidget().equals(fw.getTopage())) {
					   topageOnValueChanged(fw, (Field)arg0.getSourceWidget(), (Integer)arg0.getNewValue(), (Integer)arg0.getOldValue());          
					   return;
				 	}
				      
               System.err.println("!!! Attenzione - ResourcesRepoEH non ho intercettato il widget che ha generato l'evento ValueChanged " + arg0.toString());
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

	public void formOnCreate(ResourcesRepoFW f) {
	}

	
	
	public void webcomponentsOnCreate(ResourcesRepoFW f) {
	}			
	
	
	public void removecomponentsOnCreate(ResourcesRepoFW f) {
	}			
	
	
	public void pagefirstOnCreate(ResourcesRepoFW f) {
	}			
	
	
	public void pageprevOnCreate(ResourcesRepoFW f) {
	}			
	
	
	public void pagenextOnCreate(ResourcesRepoFW f) {
	}			
	
	
	public void pagelastOnCreate(ResourcesRepoFW f) {
	}			
	
	
	public void topageOnCreate(ResourcesRepoFW f) {
	}			
	
	
	public void pagecustomOnCreate(ResourcesRepoFW f) {
	}			
	
	
	public void sortbydateOnCreate(ResourcesRepoFW f) {
	}			
	
	
	public void sortnaturalOnCreate(ResourcesRepoFW f) {
	}			
	
	
	public void newwebcomponentsOnCreate(ResourcesRepoFW f) {
	}			
	
	
	public void addnewcomponentsOnCreate(ResourcesRepoFW f) {
	}			
	
	
	public void removenewcomponentsOnCreate(ResourcesRepoFW f) {
	}			
	
	
	public void componentssubmitOnCreate(ResourcesRepoFW f) {
	}			
	


//////###################################################################  ///////
//////###########       ON ACTION EVENT HANDLERS          ###############  ///////
//////###################################################################  ///////

	
	public void removecomponentsOnAction(ResourcesRepoFW f, RepeaterAction source) {
	}			
	
	public void pagefirstOnAction(ResourcesRepoFW f, RepeaterAction source) {
	}			
	
	public void pageprevOnAction(ResourcesRepoFW f, RepeaterAction source) {
	}			
	
	public void pagenextOnAction(ResourcesRepoFW f, RepeaterAction source) {
	}			
	
	public void pagelastOnAction(ResourcesRepoFW f, RepeaterAction source) {
	}			
	
	public void pagecustomOnAction(ResourcesRepoFW f, RepeaterAction source) {
	}			
	
	public void sortbydateOnAction(ResourcesRepoFW f, RepeaterAction source) {
	}			
	
	public void sortnaturalOnAction(ResourcesRepoFW f, RepeaterAction source) {
	}			
	
	public void addnewcomponentsOnAction(ResourcesRepoFW f, RepeaterAction source) {
	}			
	
	public void removenewcomponentsOnAction(ResourcesRepoFW f, RepeaterAction source) {
	}			
	
	public void componentssubmitOnAction(ResourcesRepoFW f, Submit source) {
	}			
	
     public void webcomponents_deleteitemOnAction(ResourcesRepoFW f, Action source, int rowIndex) {
	}			 			   
			   		
	


    
    
////// ###################################################################  ///////
////// ###########       ON VALUE CHANGED EVENT HANDLERS   ###############  ///////
////// ###################################################################  ///////

    // qui ci devono finire tutti i field, booleanfield, 
    
	
	public void topageOnValueChanged(ResourcesRepoFW f, Field source, Integer newValue, Integer oldValue) {
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
