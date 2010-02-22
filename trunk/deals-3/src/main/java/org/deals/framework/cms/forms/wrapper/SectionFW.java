
package org.deals.framework.cms.forms.wrapper;
	
import org.apache.cocoon.forms.formmodel.Form;
import org.apache.cocoon.forms.binding.Binding;
import org.apache.cocoon.forms.binding.BindingException;

	
import org.apache.cocoon.forms.formmodel.Field;	
import org.apache.cocoon.forms.formmodel.BooleanField;
import org.apache.cocoon.forms.formmodel.Submit;	
import org.apache.cocoon.forms.formmodel.Upload;
import java.util.Date;


 public class SectionFW  {			
    private Form form;
		
	private Field tabState;	
	private BooleanField checkChangeAuthor;	
	private Field sectionAuthor;	
	private Field sectionCreator;	
	private Field sectionTemplate;	
	private Field sectionDate;	
	private Field sectionExpiration;	
	private BooleanField sectionNoexpiration;	
	private Field sectionTitle;	
	private Field sectionShort;	
	private Field sectionBody;	
	private Field sectionState;	
	private Upload uploadImage;	
	private BooleanField sectionPrint;	
	private BooleanField sectionSend;	
	private Submit sectionsubmit;	
	private Submit sectiondelete;	
	private Submit sectionnodelete;	
		
	private Binding binding = null;		
		
	/**
	 * Effettua il wrapping del form passato
	 * @param f form da wrappare
	 */
	public SectionFW(Form form) {
		this.form = form;
		tabState = (Field)form.getChild("tab_state");	
		checkChangeAuthor = (BooleanField)form.getChild("check_change_author");	
		sectionAuthor = (Field)form.getChild("section_author");	
		sectionCreator = (Field)form.getChild("section_creator");	
		sectionTemplate = (Field)form.getChild("section_template");	
		sectionDate = (Field)form.getChild("section_date");	
		sectionExpiration = (Field)form.getChild("section_expiration");	
		sectionNoexpiration = (BooleanField)form.getChild("section_noexpiration");	
		sectionTitle = (Field)form.getChild("section_title");	
		sectionShort = (Field)form.getChild("section_short");	
		sectionBody = (Field)form.getChild("section_body");	
		sectionState = (Field)form.getChild("section_state");	
		uploadImage = (Upload)form.getChild("upload_image");	
		sectionPrint = (BooleanField)form.getChild("section_print");	
		sectionSend = (BooleanField)form.getChild("section_send");	
		sectionsubmit = (Submit)form.getChild("sectionsubmit");	
		sectiondelete = (Submit)form.getChild("sectiondelete");	
		sectionnodelete = (Submit)form.getChild("sectionnodelete");	
		
     }

		
		/**
		 * Setta il binding utilizzato per fare load e save del form
		 * @param binding binding del form
		 */
	    public void setBinding(Binding binding) {
	    	this.binding = binding;
	    }
		

	    /**
	     * Carica l'oggetto sul form
	     * @param o oggetto da caricare
	     * @throws BindingException problemi in fase di binding
	     */
	    public void load(Object o) throws BindingException {
    		try {
	    	if (binding != null) {
		    	binding.loadFormFromModel(form, o);
		    	
	    	} else {
	    		throw new BindingException("Error! - No binding setted");
	    	}
    		} catch (BindingException bex) {
    			throw bex;
    		}
	    }
		
		
	    /**
	     * Salva i dati del form sull'oggetto
	     * @param o oggetto su cui salvare
	     * @throws BindingException problemi in fase di binding
	     */
	    public void save(Object o) throws BindingException {
    		try {
	    	if (binding != null) {
		    	binding.saveFormToModel(form, o);
		    	
	    	} else {
	    		throw new BindingException("Error! - No binding setted");
	    	}
    		} catch (BindingException bex) {
    			throw bex;
    		}
	    }		
		
				
		
		
        /**
         * @return returns  widget
         */
        public Field getTabState() {
            return tabState;
        }

       
        /**
         * @return returns  value
         */
        public String getTabStateValue() {
            return (String)tabState.getValue();
        }		
        
        /**
         * Set  value
         * @param value  value
         */
        public void setTabStateValue(String value) {
            tabState.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Cambia in widget
         */
        public BooleanField getCheckChangeAuthor() {
            return checkChangeAuthor;
        }

       
        /**
         * @return returns Cambia in value
         */
        public Boolean getCheckChangeAuthorValue() {
            return (Boolean)checkChangeAuthor.getValue();
        }		
        
        /**
         * Set Cambia in value
         * @param value Cambia in value
         */
        public void setCheckChangeAuthorValue(Boolean value) {
            checkChangeAuthor.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Autore della sezione specificato manuale widget
         */
        public Field getSectionAuthor() {
            return sectionAuthor;
        }

       
        /**
         * @return returns Autore della sezione specificato manuale value
         */
        public String getSectionAuthorValue() {
            return (String)sectionAuthor.getValue();
        }		
        
        /**
         * Set Autore della sezione specificato manuale value
         * @param value Autore della sezione specificato manuale value
         */
        public void setSectionAuthorValue(String value) {
            sectionAuthor.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Autore della sezione widget
         */
        public Field getSectionCreator() {
            return sectionCreator;
        }

       
        /**
         * @return returns Autore della sezione value
         */
        public String getSectionCreatorValue() {
            return (String)sectionCreator.getValue();
        }		
        
        /**
         * Set Autore della sezione value
         * @param value Autore della sezione value
         */
        public void setSectionCreatorValue(String value) {
            sectionCreator.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Template della sezione widget
         */
        public Field getSectionTemplate() {
            return sectionTemplate;
        }

       
        /**
         * @return returns Template della sezione value
         */
        public Integer getSectionTemplateValue() {
            return (Integer)sectionTemplate.getValue();
        }		
        
        /**
         * Set Template della sezione value
         * @param value Template della sezione value
         */
        public void setSectionTemplateValue(Integer value) {
            sectionTemplate.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Data della sezione widget
         */
        public Field getSectionDate() {
            return sectionDate;
        }

       
        /**
         * @return returns Data della sezione value
         */
        public Date getSectionDateValue() {
            return (Date)sectionDate.getValue();
        }		
        
        /**
         * Set Data della sezione value
         * @param value Data della sezione value
         */
        public void setSectionDateValue(Date value) {
            sectionDate.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Scadenza della sezione widget
         */
        public Field getSectionExpiration() {
            return sectionExpiration;
        }

       
        /**
         * @return returns Scadenza della sezione value
         */
        public Date getSectionExpirationValue() {
            return (Date)sectionExpiration.getValue();
        }		
        
        /**
         * Set Scadenza della sezione value
         * @param value Scadenza della sezione value
         */
        public void setSectionExpirationValue(Date value) {
            sectionExpiration.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns SENZA SCADENZA widget
         */
        public BooleanField getSectionNoexpiration() {
            return sectionNoexpiration;
        }

       
        /**
         * @return returns SENZA SCADENZA value
         */
        public Boolean getSectionNoexpirationValue() {
            return (Boolean)sectionNoexpiration.getValue();
        }		
        
        /**
         * Set SENZA SCADENZA value
         * @param value SENZA SCADENZA value
         */
        public void setSectionNoexpirationValue(Boolean value) {
            sectionNoexpiration.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Titolo della sezione widget
         */
        public Field getSectionTitle() {
            return sectionTitle;
        }

       
        /**
         * @return returns Titolo della sezione value
         */
        public String getSectionTitleValue() {
            return (String)sectionTitle.getValue();
        }		
        
        /**
         * Set Titolo della sezione value
         * @param value Titolo della sezione value
         */
        public void setSectionTitleValue(String value) {
            sectionTitle.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Occhiello della sezione widget
         */
        public Field getSectionShort() {
            return sectionShort;
        }

       
        /**
         * @return returns Occhiello della sezione value
         */
        public String getSectionShortValue() {
            return (String)sectionShort.getValue();
        }		
        
        /**
         * Set Occhiello della sezione value
         * @param value Occhiello della sezione value
         */
        public void setSectionShortValue(String value) {
            sectionShort.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Corpo della sezione widget
         */
        public Field getSectionBody() {
            return sectionBody;
        }

       
        /**
         * @return returns Corpo della sezione value
         */
        public String getSectionBodyValue() {
            return (String)sectionBody.getValue();
        }		
        
        /**
         * Set Corpo della sezione value
         * @param value Corpo della sezione value
         */
        public void setSectionBodyValue(String value) {
            sectionBody.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Stato della sezione widget
         */
        public Field getSectionState() {
            return sectionState;
        }

       
        /**
         * @return returns Stato della sezione value
         */
        public Integer getSectionStateValue() {
            return (Integer)sectionState.getValue();
        }		
        
        /**
         * Set Stato della sezione value
         * @param value Stato della sezione value
         */
        public void setSectionStateValue(Integer value) {
            sectionState.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Carica widget
         */
        public Upload getUploadImage() {
            return uploadImage;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Consenti stampa widget
         */
        public BooleanField getSectionPrint() {
            return sectionPrint;
        }

       
        /**
         * @return returns Consenti stampa value
         */
        public Boolean getSectionPrintValue() {
            return (Boolean)sectionPrint.getValue();
        }		
        
        /**
         * Set Consenti stampa value
         * @param value Consenti stampa value
         */
        public void setSectionPrintValue(Boolean value) {
            sectionPrint.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Consenti invia widget
         */
        public BooleanField getSectionSend() {
            return sectionSend;
        }

       
        /**
         * @return returns Consenti invia value
         */
        public Boolean getSectionSendValue() {
            return (Boolean)sectionSend.getValue();
        }		
        
        /**
         * Set Consenti invia value
         * @param value Consenti invia value
         */
        public void setSectionSendValue(Boolean value) {
            sectionSend.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns SALVA widget
         */
        public Submit getSectionsubmit() {
            return sectionsubmit;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns CONFERMA CANCELLAZIONE widget
         */
        public Submit getSectiondelete() {
            return sectiondelete;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns ANNULLA CANCELLAZIONE widget
         */
        public Submit getSectionnodelete() {
            return sectionnodelete;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
			
		
		
        /**
         * @return Returns the form.
         */
        public Form getForm() {
            return form;
        }

        /**
         * @param form The form to set.
         */
        public void setForm(Form form) {
            this.form = form;
        }			
        
				
		public void setAttribute(String name, Object value) {
            form.setAttribute(name, value);		
		}		
		
		public Object getAttribute(String name) {
		    return form.getAttribute(name);
		}
        
		
	

   }

