
package org.deals.framework.cms.forms.wrapper;
	
import java.util.Date;

import org.apache.cocoon.forms.binding.Binding;
import org.apache.cocoon.forms.binding.BindingException;
import org.apache.cocoon.forms.formmodel.Action;
import org.apache.cocoon.forms.formmodel.BooleanField;
import org.apache.cocoon.forms.formmodel.Field;
import org.apache.cocoon.forms.formmodel.Form;
import org.apache.cocoon.forms.formmodel.Output;
import org.apache.cocoon.forms.formmodel.Repeater;
import org.apache.cocoon.forms.formmodel.RepeaterAction;
import org.apache.cocoon.forms.formmodel.Repeater.RepeaterRow;


 public class GestioneProfiliFW  {			
    private Form form;
		
	private ProfiliRepeaterWrapper profili;	
	private RepeaterAction pagefirst;	
	private RepeaterAction pageprev;	
	private RepeaterAction pagenext;	
	private RepeaterAction pagelast;	
	private Field topage;	
	private RepeaterAction pagecustom;	
	private RepeaterAction sortnatural;	
	private RepeaterAction removeprofilo;	
	private RepeaterAction addprofilo;	
	private Action profiliaction;
		
	private Binding binding = null;		
		
	/**
	 * Effettua il wrapping del form passato
	 * @param f form da wrappare
	 */
	public GestioneProfiliFW(Form form) {
		this.form = form;
		pagefirst = (RepeaterAction)form.getChild("pagefirst");	
		pageprev = (RepeaterAction)form.getChild("pageprev");	
		pagenext = (RepeaterAction)form.getChild("pagenext");	
		pagelast = (RepeaterAction)form.getChild("pagelast");	
		topage = (Field)form.getChild("topage");	
		pagecustom = (RepeaterAction)form.getChild("pagecustom");	
		sortnatural = (RepeaterAction)form.getChild("sortnatural");	
		removeprofilo= (RepeaterAction)form.getChild("removeprofilo");	
		addprofilo = (RepeaterAction)form.getChild("addprofilo");	
		profili = new ProfiliRepeaterWrapper((Repeater)form.getChild("profili"));
		profiliaction = (Action)form.getChild("profiliaction");
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
         * @return returns Profili widget
         */
        public ProfiliRepeaterWrapper getProfili() {
            return profili;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns << widget
         */
        public RepeaterAction getPagefirst() {
            return pagefirst;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns < widget
         */
        public RepeaterAction getPageprev() {
            return pageprev;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns > widget
         */
        public RepeaterAction getPagenext() {
            return pagenext;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns >> widget
         */
        public RepeaterAction getPagelast() {
            return pagelast;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns topage widget
         */
        public Field getTopage() {
            return topage;
        }

       
        /**
         * @return returns topage value
         */
        public Integer getTopageValue() {
            return (Integer)topage.getValue();
        }		
        
        /**
         * Set topage value
         * @param value topage value
         */
        public void setTopageValue(Integer value) {
            topage.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Vai alla pagina widget
         */
        public RepeaterAction getPagecustom() {
            return pagecustom;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Naturale widget
         */
        public RepeaterAction getSortnatural() {
            return sortnatural;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Rimuovi l'utente selezionato widget
         */
        public RepeaterAction getRemoveprofilo() {
            return removeprofilo;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Aggiungi nuovo utente widget
         */
        public RepeaterAction getAddprofilo() {
            return addprofilo;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
        
        public Action getProfiliaction() {
			return profiliaction;
		}


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
        
		
	
	 public class ProfiliRepeaterWrapper {
		
		private Repeater base;
				
		public ProfiliRepeaterWrapper(Repeater base) {
		  // mette il repeater in locale
		  this.base = base;
		}
		
		
        /**
         * @return returns  widget
         */
        public BooleanField getSelect(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (BooleanField)r.getChild("select");
         } 
         else return null;
        }

       
        /**
         * @return returns  value
         */
        public Boolean getSelectValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Boolean)r.getChild("select").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set  value
         * @param value  value
         */
        public void setSelectValue(Boolean value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("select").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns up_id widget
         */
        public Field getUpId(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("up_id");
         } 
         else return null;
        }

       
        /**
         * @return returns up_id value
         */
        public Integer getUpIdValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("up_id").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set up_id value
         * @param value up_id value
         */
        public void setUpIdValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("up_id").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns ID widget
         */
        public Output getRowid(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("rowid");
         } 
         else return null;
        }

       
        /**
         * @return returns ID value
         */
        public Integer getRowidValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("rowid").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set ID value
         * @param value ID value
         */
        public void setRowidValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("rowid").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns up_name widget
         */
        public Field getUpName(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("up_name");
         } 
         else return null;
        }

       
        /**
         * @return returns up_name value
         */
        public String getUpNameValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("up_name").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set up_name value
         * @param value up_name value
         */
        public void setUpNameValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("up_name").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns up_description widget
         */
        public Field getUpDescription(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("up_description");
         } 
         else return null;
        }

       
        /**
         * @return returns up_description value
         */
        public String getUpDescriptionValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("up_description").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set up_description value
         * @param value up_description value
         */
        public void setUpDescriptionValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("up_description").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns up_created_date widget
         */
        public Field getUpCreatedDate(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("up_created_date");
         } 
         else return null;
        }

       
        /**
         * @return returns up_created_date value
         */
        public Date getUpCreatedValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Date)r.getChild("up_created_date").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set up_created_date value
         * @param value up_created_date value
         */
        public void setUpCreatedValue(Date value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("up_created_date").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns up_expire_date widget
         */
        public Field getUpExpireDate(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("up_expire_date");
         } 
         else return null;
        }

       
        /**
         * @return returns up_expire_date value
         */
        public Date getUpExpireDateValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Date)r.getChild("up_expire_date").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set up_expire_date value
         * @param value up_expire_date value
         */
        public void setUpExpireDateValue(Date value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("up_expire_date").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         				
		
		public Repeater getRepeater() {
		   return base;
		}
	 }	
	

   }

