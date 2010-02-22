
package org.deals.framework.cms.forms.eh.abstracts;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.avalon.framework.parameters.Parameters;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.cocoon.components.flow.apples.AppleResponse;
import org.apache.cocoon.environment.Request;
import org.apache.cocoon.forms.binding.Binding;
import org.apache.cocoon.forms.event.AbstractFormHandler;
import org.apache.cocoon.forms.event.ActionEvent;
import org.apache.cocoon.forms.event.ValueChangedEvent;
import org.apache.cocoon.forms.formmodel.BooleanField;
import org.apache.cocoon.forms.formmodel.Field;
import org.apache.cocoon.forms.formmodel.Form;
import org.apache.cocoon.forms.formmodel.Submit;
import org.apache.cocoon.forms.formmodel.Upload;
import org.apache.cocoon.forms.formmodel.Widget;
import org.apache.cocoon.forms.formmodel.WidgetState;
import org.apache.log4j.Logger;
import org.deals.framework.cms.forms.wrapper.CategoryFW;



 public abstract class AbstractCategoryEH extends AbstractFormHandler { 

	protected Logger log = Logger.getLogger(getClass());
	protected ServiceManager service = null;
	protected HttpSession session = null;
    protected Request request = null;
    protected Parameters params = null;
    protected AppleResponse response = null;

	protected CategoryFW fw = null; 
    
		
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
	public CategoryFW initForm(Form f, Request request, Binding binding) {

			this.params = params;
			this.request = request;
			session = request.getSession();
			fw = new CategoryFW(f);
			
			if (binding != null) {
			   fw.setBinding(binding);
			}

			formOnCreate(fw);
			tab_stateOnCreate(fw);
			check_change_authorOnCreate(fw);
			category_authorOnCreate(fw);
			category_creatorOnCreate(fw);
			category_templateOnCreate(fw);
			category_dateOnCreate(fw);
			category_expirationOnCreate(fw);
			category_noexpirationOnCreate(fw);
			category_titleOnCreate(fw);
			category_shortOnCreate(fw);
			category_bodyOnCreate(fw);
			category_stateOnCreate(fw);
			upload_imageOnCreate(fw);
			category_printOnCreate(fw);
			category_sendOnCreate(fw);
			categorysubmitOnCreate(fw);
			categorydeleteOnCreate(fw);
			categorynodeleteOnCreate(fw);
			sectionsOnCreate(fw);
			return fw;
	}
       
	public void handleActionEvent(ActionEvent arg0) {
            if (arg0.getSourceWidget().getParent().equals(fw.getForm())) {
               // tutte le chiamate pertinenti a widget che stanno nel Form
			   
				  if (arg0.getSourceWidget().equals(fw.getCategorysubmit())) {
					   categorysubmitOnAction(fw, (Submit)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getCategorydelete())) {
					   categorydeleteOnAction(fw, (Submit)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getCategorynodelete())) {
					   categorynodeleteOnAction(fw, (Submit)arg0.getSourceWidget());          
					   return;
				 	}
				      
               log.error("!!! Attenzione - CategoryEH non ho intercettato il widget che ha generato l'evento Action " + arg0.toString());
               return; // non ho intercettato il widget che ha generato l'evento
            }	
            
            // lista di if per controllare se il parent ?? magari un repeater, o meglio una repeater row
            //if (arg0.getSourceWidget().getParent().getParent().equals(fw.getRepeater()) {
               // vecchia intercettazione tramite ID
            //} 

		
            if (arg0.getSourceWidget().getFullName().indexOf("sections")>-1) {
			   
			   
            } 
        		
        
	        
	        
	}

	public void handleValueChangedEvent(ValueChangedEvent arg0) {
	        
            if (arg0.getSourceWidget().getParent().equals(fw.getForm())) {
               // tutte le chiamate pertinenti a widget che stanno nel Form
				 
				  if (arg0.getSourceWidget().equals(fw.getTabState())) {
					   tab_stateOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getCheckChangeAuthor())) {
					   check_change_authorOnValueChanged(fw, (BooleanField)arg0.getSourceWidget(), (Boolean)arg0.getNewValue(), (Boolean)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getCategoryAuthor())) {
					   category_authorOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getCategoryCreator())) {
					   category_creatorOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getCategoryTemplate())) {
					   category_templateOnValueChanged(fw, (Field)arg0.getSourceWidget(), (Integer)arg0.getNewValue(), (Integer)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getCategoryDate())) {
					   category_dateOnValueChanged(fw, (Field)arg0.getSourceWidget(), (Date)arg0.getNewValue(), (Date)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getCategoryExpiration())) {
					   category_expirationOnValueChanged(fw, (Field)arg0.getSourceWidget(), (Date)arg0.getNewValue(), (Date)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getCategoryNoexpiration())) {
					   category_noexpirationOnValueChanged(fw, (BooleanField)arg0.getSourceWidget(), (Boolean)arg0.getNewValue(), (Boolean)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getCategoryTitle())) {
					   category_titleOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getCategoryShort())) {
					   category_shortOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getCategoryBody())) {
					   category_bodyOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getCategoryState())) {
					   category_stateOnValueChanged(fw, (Field)arg0.getSourceWidget(), (Integer)arg0.getNewValue(), (Integer)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getUploadImage())) {
					   upload_imageOnValueChanged(fw, (Upload)arg0.getSourceWidget(), (Object)arg0.getNewValue(), (Object)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getCategoryPrint())) {
					   category_printOnValueChanged(fw, (BooleanField)arg0.getSourceWidget(), (Boolean)arg0.getNewValue(), (Boolean)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getCategorySend())) {
					   category_sendOnValueChanged(fw, (BooleanField)arg0.getSourceWidget(), (Boolean)arg0.getNewValue(), (Boolean)arg0.getOldValue());          
					   return;
				 	}
				      
               log.error("!!! Attenzione - CategoryEH non ho intercettato il widget che ha generato l'evento ValueChanged " + arg0.toString());
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

	public void formOnCreate(CategoryFW f) {
	}

	
	
	public void tab_stateOnCreate(CategoryFW f) {
	}			
	
	
	public void check_change_authorOnCreate(CategoryFW f) {
	}			
	
	
	public void category_authorOnCreate(CategoryFW f) {
	}			
	
	
	public void category_creatorOnCreate(CategoryFW f) {
	}			
	
	
	public void category_templateOnCreate(CategoryFW f) {
	}			
	
	
	public void category_dateOnCreate(CategoryFW f) {
	}			
	
	
	public void category_expirationOnCreate(CategoryFW f) {
	}			
	
	
	public void category_noexpirationOnCreate(CategoryFW f) {
	}			
	
	
	public void category_titleOnCreate(CategoryFW f) {
	}			
	
	
	public void category_shortOnCreate(CategoryFW f) {
	}			
	
	
	public void category_bodyOnCreate(CategoryFW f) {
	}			
	
	
	public void category_stateOnCreate(CategoryFW f) {
	}			
	
	
	public void upload_imageOnCreate(CategoryFW f) {
	}			
	
	
	public void category_printOnCreate(CategoryFW f) {
	}			
	
	
	public void category_sendOnCreate(CategoryFW f) {
	}			
	
	
	public void categorysubmitOnCreate(CategoryFW f) {
	}			
	
	
	public void categorydeleteOnCreate(CategoryFW f) {
	}			
	
	
	public void categorynodeleteOnCreate(CategoryFW f) {
	}			
	
	
	public void sectionsOnCreate(CategoryFW f) {
	}			
	


//////###################################################################  ///////
//////###########       ON ACTION EVENT HANDLERS          ###############  ///////
//////###################################################################  ///////

	
	public void categorysubmitOnAction(CategoryFW f, Submit source) {
	}			
	
	public void categorydeleteOnAction(CategoryFW f, Submit source) {
	}			
	
	public void categorynodeleteOnAction(CategoryFW f, Submit source) {
	}			
			
	


    
    
////// ###################################################################  ///////
////// ###########       ON VALUE CHANGED EVENT HANDLERS   ###############  ///////
////// ###################################################################  ///////

    // qui ci devono finire tutti i field, booleanfield, 
    
	
	public void tab_stateOnValueChanged(CategoryFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void check_change_authorOnValueChanged(CategoryFW f, BooleanField source, Boolean newValue, Boolean oldValue) {
	}			
	
	public void category_authorOnValueChanged(CategoryFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void category_creatorOnValueChanged(CategoryFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void category_templateOnValueChanged(CategoryFW f, Field source, Integer newValue, Integer oldValue) {
	}			
	
	public void category_dateOnValueChanged(CategoryFW f, Field source, Date newValue, Date oldValue) {
	}			
	
	public void category_expirationOnValueChanged(CategoryFW f, Field source, Date newValue, Date oldValue) {
	}			
	
	public void category_noexpirationOnValueChanged(CategoryFW f, BooleanField source, Boolean newValue, Boolean oldValue) {
	}			
	
	public void category_titleOnValueChanged(CategoryFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void category_shortOnValueChanged(CategoryFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void category_bodyOnValueChanged(CategoryFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void category_stateOnValueChanged(CategoryFW f, Field source, Integer newValue, Integer oldValue) {
	}			
	
	public void upload_imageOnValueChanged(CategoryFW f, Upload source, Object newValue, Object oldValue) {
	}			
	
	public void category_printOnValueChanged(CategoryFW f, BooleanField source, Boolean newValue, Boolean oldValue) {
	}			
	
	public void category_sendOnValueChanged(CategoryFW f, BooleanField source, Boolean newValue, Boolean oldValue) {
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
