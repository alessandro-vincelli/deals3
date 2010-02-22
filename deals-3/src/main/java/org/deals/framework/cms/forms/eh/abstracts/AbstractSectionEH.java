
package org.deals.framework.cms.forms.eh.abstracts;
import java.util.Date;

import javax.servlet.http.HttpSession;

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
import org.deals.framework.cms.forms.wrapper.SectionFW;

	


 public abstract class AbstractSectionEH extends AbstractFormHandler { 

	protected Logger log = Logger.getLogger(getClass());	 
	protected ServiceManager service = null;
	protected HttpSession session = null;
    protected Request request = null;
    
    protected AppleResponse response = null;

	protected SectionFW fw = null; 
    
		
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
	public SectionFW initForm(Form f, Request request, Binding binding) {

			
			this.request = request;
			session = request.getSession();
			fw = new SectionFW(f);
			
			if (binding != null) {
			   fw.setBinding(binding);
			}

			formOnCreate(fw);
			tab_stateOnCreate(fw);
			check_change_authorOnCreate(fw);
			section_authorOnCreate(fw);
			section_creatorOnCreate(fw);
			section_templateOnCreate(fw);
			section_dateOnCreate(fw);
			section_expirationOnCreate(fw);
			section_noexpirationOnCreate(fw);
			section_titleOnCreate(fw);
			section_shortOnCreate(fw);
			section_bodyOnCreate(fw);
			section_stateOnCreate(fw);
			upload_imageOnCreate(fw);
			section_printOnCreate(fw);
			section_sendOnCreate(fw);
			sectionsubmitOnCreate(fw);
			sectiondeleteOnCreate(fw);
			sectionnodeleteOnCreate(fw);
			return fw;
	}
       
	public void handleActionEvent(ActionEvent arg0) {
            if (arg0.getSourceWidget().getParent().equals(fw.getForm())) {
               // tutte le chiamate pertinenti a widget che stanno nel Form
			   
				  if (arg0.getSourceWidget().equals(fw.getSectionsubmit())) {
					   sectionsubmitOnAction(fw, (Submit)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getSectiondelete())) {
					   sectiondeleteOnAction(fw, (Submit)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getSectionnodelete())) {
					   sectionnodeleteOnAction(fw, (Submit)arg0.getSourceWidget());          
					   return;
				 	}
				      
               log.error("!!! Attenzione - SectionEH non ho intercettato il widget che ha generato l'evento Action " + arg0.toString());
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
				 
				  if (arg0.getSourceWidget().equals(fw.getCheckChangeAuthor())) {
					   check_change_authorOnValueChanged(fw, (BooleanField)arg0.getSourceWidget(), (Boolean)arg0.getNewValue(), (Boolean)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getSectionAuthor())) {
					   section_authorOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getSectionCreator())) {
					   section_creatorOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getSectionTemplate())) {
					   section_templateOnValueChanged(fw, (Field)arg0.getSourceWidget(), (Integer)arg0.getNewValue(), (Integer)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getSectionDate())) {
					   section_dateOnValueChanged(fw, (Field)arg0.getSourceWidget(), (Date)arg0.getNewValue(), (Date)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getSectionExpiration())) {
					   section_expirationOnValueChanged(fw, (Field)arg0.getSourceWidget(), (Date)arg0.getNewValue(), (Date)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getSectionNoexpiration())) {
					   section_noexpirationOnValueChanged(fw, (BooleanField)arg0.getSourceWidget(), (Boolean)arg0.getNewValue(), (Boolean)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getSectionTitle())) {
					   section_titleOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getSectionShort())) {
					   section_shortOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getSectionBody())) {
					   section_bodyOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getSectionState())) {
					   section_stateOnValueChanged(fw, (Field)arg0.getSourceWidget(), (Integer)arg0.getNewValue(), (Integer)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getUploadImage())) {
					   upload_imageOnValueChanged(fw, (Upload)arg0.getSourceWidget(), (Object)arg0.getNewValue(), (Object)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getSectionPrint())) {
					   section_printOnValueChanged(fw, (BooleanField)arg0.getSourceWidget(), (Boolean)arg0.getNewValue(), (Boolean)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getSectionSend())) {
					   section_sendOnValueChanged(fw, (BooleanField)arg0.getSourceWidget(), (Boolean)arg0.getNewValue(), (Boolean)arg0.getOldValue());          
					   return;
				 	}
				      
               log.error("!!! Attenzione - SectionEH non ho intercettato il widget che ha generato l'evento ValueChanged " + arg0.toString());
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

	public void formOnCreate(SectionFW f) {
	}

	
	
	public void tab_stateOnCreate(SectionFW f) {
	}			
	
	
	public void check_change_authorOnCreate(SectionFW f) {
	}			
	
	
	public void section_authorOnCreate(SectionFW f) {
	}			
	
	
	public void section_creatorOnCreate(SectionFW f) {
	}			
	
	
	public void section_templateOnCreate(SectionFW f) {
	}			
	
	
	public void section_dateOnCreate(SectionFW f) {
	}			
	
	
	public void section_expirationOnCreate(SectionFW f) {
	}			
	
	
	public void section_noexpirationOnCreate(SectionFW f) {
	}			
	
	
	public void section_titleOnCreate(SectionFW f) {
	}			
	
	
	public void section_shortOnCreate(SectionFW f) {
	}			
	
	
	public void section_bodyOnCreate(SectionFW f) {
	}			
	
	
	public void section_stateOnCreate(SectionFW f) {
	}			
	
	
	public void upload_imageOnCreate(SectionFW f) {
	}			
	
	
	public void section_printOnCreate(SectionFW f) {
	}			
	
	
	public void section_sendOnCreate(SectionFW f) {
	}			
	
	
	public void sectionsubmitOnCreate(SectionFW f) {
	}			
	
	
	public void sectiondeleteOnCreate(SectionFW f) {
	}			
	
	
	public void sectionnodeleteOnCreate(SectionFW f) {
	}			
	


//////###################################################################  ///////
//////###########       ON ACTION EVENT HANDLERS          ###############  ///////
//////###################################################################  ///////

	
	public void sectionsubmitOnAction(SectionFW f, Submit source) {
	}			
	
	public void sectiondeleteOnAction(SectionFW f, Submit source) {
	}			
	
	public void sectionnodeleteOnAction(SectionFW f, Submit source) {
	}			
			
	


    
    
////// ###################################################################  ///////
////// ###########       ON VALUE CHANGED EVENT HANDLERS   ###############  ///////
////// ###################################################################  ///////

    // qui ci devono finire tutti i field, booleanfield, 
    
	
	public void tab_stateOnValueChanged(SectionFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void check_change_authorOnValueChanged(SectionFW f, BooleanField source, Boolean newValue, Boolean oldValue) {
	}			
	
	public void section_authorOnValueChanged(SectionFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void section_creatorOnValueChanged(SectionFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void section_templateOnValueChanged(SectionFW f, Field source, Integer newValue, Integer oldValue) {
	}			
	
	public void section_dateOnValueChanged(SectionFW f, Field source, Date newValue, Date oldValue) {
	}			
	
	public void section_expirationOnValueChanged(SectionFW f, Field source, Date newValue, Date oldValue) {
	}			
	
	public void section_noexpirationOnValueChanged(SectionFW f, BooleanField source, Boolean newValue, Boolean oldValue) {
	}			
	
	public void section_titleOnValueChanged(SectionFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void section_shortOnValueChanged(SectionFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void section_bodyOnValueChanged(SectionFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void section_stateOnValueChanged(SectionFW f, Field source, Integer newValue, Integer oldValue) {
	}			
	
	public void upload_imageOnValueChanged(SectionFW f, Upload source, Object newValue, Object oldValue) {
	}			
	
	public void section_printOnValueChanged(SectionFW f, BooleanField source, Boolean newValue, Boolean oldValue) {
	}			
	
	public void section_sendOnValueChanged(SectionFW f, BooleanField source, Boolean newValue, Boolean oldValue) {
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
