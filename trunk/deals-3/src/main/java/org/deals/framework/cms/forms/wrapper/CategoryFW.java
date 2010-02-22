
package org.deals.framework.cms.forms.wrapper;
	
import org.apache.cocoon.forms.formmodel.Form;
import org.apache.cocoon.forms.binding.Binding;
import org.apache.cocoon.forms.binding.BindingException;

	
import org.apache.cocoon.forms.formmodel.Field;	
import org.apache.cocoon.forms.formmodel.BooleanField;
import org.apache.cocoon.forms.formmodel.Submit;	
import org.apache.cocoon.forms.formmodel.Upload;
import org.apache.cocoon.forms.formmodel.Repeater;
import org.apache.cocoon.forms.formmodel.Repeater.RepeaterRow;
import java.util.Date;


 public class CategoryFW  {			
    private Form form;
		
	private Field tabState;	
	private BooleanField checkChangeAuthor;	
	private Field categoryAuthor;	
	private Field categoryCreator;	
	private Field categoryTemplate;	
	private Field categoryDate;	
	private Field categoryExpiration;	
	private BooleanField categoryNoexpiration;	
	private Field categoryTitle;	
	private Field categoryShort;	
	private Field categoryBody;	
	private Field categoryState;	
	private Upload uploadImage;	
	private BooleanField categoryPrint;	
	private BooleanField categorySend;	
	private Submit categorysubmit;	
	private Submit categorydelete;	
	private Submit categorynodelete;	
	private SectionsRepeaterWrapper sections;	
		
	private Binding binding = null;		
		
	/**
	 * Effettua il wrapping del form passato
	 * @param f form da wrappare
	 */
	public CategoryFW(Form form) {
		this.form = form;
		tabState = (Field)form.getChild("tab_state");	
		checkChangeAuthor = (BooleanField)form.getChild("check_change_author");	
		categoryAuthor = (Field)form.getChild("category_author");	
		categoryCreator = (Field)form.getChild("category_creator");	
		categoryTemplate = (Field)form.getChild("category_template");	
		categoryDate = (Field)form.getChild("category_date");	
		categoryExpiration = (Field)form.getChild("category_expiration");	
		categoryNoexpiration = (BooleanField)form.getChild("category_noexpiration");	
		categoryTitle = (Field)form.getChild("category_title");	
		categoryShort = (Field)form.getChild("category_short");	
		categoryBody = (Field)form.getChild("category_body");	
		categoryState = (Field)form.getChild("category_state");	
		uploadImage = (Upload)form.getChild("upload_image");	
		categoryPrint = (BooleanField)form.getChild("category_print");	
		categorySend = (BooleanField)form.getChild("category_send");	
		categorysubmit = (Submit)form.getChild("categorysubmit");	
		categorydelete = (Submit)form.getChild("categorydelete");	
		categorynodelete = (Submit)form.getChild("categorynodelete");	
		sections = new SectionsRepeaterWrapper((Repeater)form.getChild("sections"));
		
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
         * @return returns Autore della categoria specificato manuale widget
         */
        public Field getCategoryAuthor() {
            return categoryAuthor;
        }

       
        /**
         * @return returns Autore della categoria specificato manuale value
         */
        public String getCategoryAuthorValue() {
            return (String)categoryAuthor.getValue();
        }		
        
        /**
         * Set Autore della categoria specificato manuale value
         * @param value Autore della categoria specificato manuale value
         */
        public void setCategoryAuthorValue(String value) {
            categoryAuthor.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Autore della categoria widget
         */
        public Field getCategoryCreator() {
            return categoryCreator;
        }

       
        /**
         * @return returns Autore della categoria value
         */
        public String getCategoryCreatorValue() {
            return (String)categoryCreator.getValue();
        }		
        
        /**
         * Set Autore della categoria value
         * @param value Autore della categoria value
         */
        public void setCategoryCreatorValue(String value) {
            categoryCreator.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Template della categoria widget
         */
        public Field getCategoryTemplate() {
            return categoryTemplate;
        }

       
        /**
         * @return returns Template della categoria value
         */
        public Integer getCategoryTemplateValue() {
            return (Integer)categoryTemplate.getValue();
        }		
        
        /**
         * Set Template della categoria value
         * @param value Template della categoria value
         */
        public void setCategoryTemplateValue(Integer value) {
            categoryTemplate.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Data della categoria widget
         */
        public Field getCategoryDate() {
            return categoryDate;
        }

       
        /**
         * @return returns Data della categoria value
         */
        public Date getCategoryDateValue() {
            return (Date)categoryDate.getValue();
        }		
        
        /**
         * Set Data della categoria value
         * @param value Data della categoria value
         */
        public void setCategoryDateValue(Date value) {
            categoryDate.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Scadenza della categoria widget
         */
        public Field getCategoryExpiration() {
            return categoryExpiration;
        }

       
        /**
         * @return returns Scadenza della categoria value
         */
        public Date getCategoryExpirationValue() {
            return (Date)categoryExpiration.getValue();
        }		
        
        /**
         * Set Scadenza della categoria value
         * @param value Scadenza della categoria value
         */
        public void setCategoryExpirationValue(Date value) {
            categoryExpiration.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns SENZA SCADENZA widget
         */
        public BooleanField getCategoryNoexpiration() {
            return categoryNoexpiration;
        }

       
        /**
         * @return returns SENZA SCADENZA value
         */
        public Boolean getCategoryNoexpirationValue() {
            return (Boolean)categoryNoexpiration.getValue();
        }		
        
        /**
         * Set SENZA SCADENZA value
         * @param value SENZA SCADENZA value
         */
        public void setCategoryNoexpirationValue(Boolean value) {
            categoryNoexpiration.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Titolo della categoria widget
         */
        public Field getCategoryTitle() {
            return categoryTitle;
        }

       
        /**
         * @return returns Titolo della categoria value
         */
        public String getCategoryTitleValue() {
            return (String)categoryTitle.getValue();
        }		
        
        /**
         * Set Titolo della categoria value
         * @param value Titolo della categoria value
         */
        public void setCategoryTitleValue(String value) {
            categoryTitle.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Occhiello della categoria widget
         */
        public Field getCategoryShort() {
            return categoryShort;
        }

       
        /**
         * @return returns Occhiello della categoria value
         */
        public String getCategoryShortValue() {
            return (String)categoryShort.getValue();
        }		
        
        /**
         * Set Occhiello della categoria value
         * @param value Occhiello della categoria value
         */
        public void setCategoryShortValue(String value) {
            categoryShort.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Corpo della categoria widget
         */
        public Field getCategoryBody() {
            return categoryBody;
        }

       
        /**
         * @return returns Corpo della categoria value
         */
        public String getCategoryBodyValue() {
            return (String)categoryBody.getValue();
        }		
        
        /**
         * Set Corpo della categoria value
         * @param value Corpo della categoria value
         */
        public void setCategoryBodyValue(String value) {
            categoryBody.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Stato della categoria widget
         */
        public Field getCategoryState() {
            return categoryState;
        }

       
        /**
         * @return returns Stato della categoria value
         */
        public Integer getCategoryStateValue() {
            return (Integer)categoryState.getValue();
        }		
        
        /**
         * Set Stato della categoria value
         * @param value Stato della categoria value
         */
        public void setCategoryStateValue(Integer value) {
            categoryState.setValue(value);
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
        public BooleanField getCategoryPrint() {
            return categoryPrint;
        }

       
        /**
         * @return returns Consenti stampa value
         */
        public Boolean getCategoryPrintValue() {
            return (Boolean)categoryPrint.getValue();
        }		
        
        /**
         * Set Consenti stampa value
         * @param value Consenti stampa value
         */
        public void setCategoryPrintValue(Boolean value) {
            categoryPrint.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Consenti invia widget
         */
        public BooleanField getCategorySend() {
            return categorySend;
        }

       
        /**
         * @return returns Consenti invia value
         */
        public Boolean getCategorySendValue() {
            return (Boolean)categorySend.getValue();
        }		
        
        /**
         * Set Consenti invia value
         * @param value Consenti invia value
         */
        public void setCategorySendValue(Boolean value) {
            categorySend.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns SALVA widget
         */
        public Submit getCategorysubmit() {
            return categorysubmit;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns CONFERMA CANCELLAZIONE widget
         */
        public Submit getCategorydelete() {
            return categorydelete;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns ANNULLA CANCELLAZIONE widget
         */
        public Submit getCategorynodelete() {
            return categorynodelete;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns  widget
         */
        public SectionsRepeaterWrapper getSections() {
            return sections;
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
        
		
	
	 public class SectionsRepeaterWrapper {
		
		private Repeater base;
			
		private Field idsection;	
		private BooleanField checksection;	
		private Field labelsection;	
		
				
		public SectionsRepeaterWrapper(Repeater base) {
		  // mette il repeater in locale
		  this.base = base;
		}
		
		
        /**
         * @return returns  widget
         */
        public Field getIdsection(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("idsection");
         } 
         else return null;
        }

       
        /**
         * @return returns  value
         */
        public Integer getIdsectionValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("idsection").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set  value
         * @param value  value
         */
        public void setIdsectionValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("idsection").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Nome sezione widget
         */
        public BooleanField getChecksection(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (BooleanField)r.getChild("checksection");
         } 
         else return null;
        }

       
        /**
         * @return returns Nome sezione value
         */
        public Boolean getChecksectionValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Boolean)r.getChild("checksection").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Nome sezione value
         * @param value Nome sezione value
         */
        public void setChecksectionValue(Boolean value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("checksection").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns  widget
         */
        public Field getLabelsection(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("labelsection");
         } 
         else return null;
        }

       
        /**
         * @return returns  value
         */
        public String getLabelsectionValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("labelsection").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set  value
         * @param value  value
         */
        public void setLabelsectionValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("labelsection").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
			
		
		
				
		
		public Repeater getRepeater() {
		   return base;
		}
	 }	
	

   }

