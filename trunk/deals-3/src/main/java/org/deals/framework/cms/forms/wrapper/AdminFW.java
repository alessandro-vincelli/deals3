
package org.deals.framework.cms.forms.wrapper;
	
import org.apache.cocoon.forms.formmodel.Form;
import org.apache.cocoon.forms.binding.Binding;
import org.apache.cocoon.forms.binding.BindingException;

	
import org.apache.cocoon.forms.formmodel.Field;	
import org.apache.cocoon.forms.formmodel.Action;	
import org.apache.cocoon.forms.formmodel.Output;


 public class AdminFW  {			
    private Form form;
		
	private Field tabState;	
	private Output logMessagges;	
	private Action searchreindex;	
		
	private Binding binding = null;		
		
	/**
	 * Effettua il wrapping del form passato
	 * @param f form da wrappare
	 */
	public AdminFW(Form form) {
		this.form = form;
		tabState = (Field)form.getChild("tab_state");	
		logMessagges = (Output)form.getChild("log_messagges");	
		searchreindex = (Action)form.getChild("searchreindex");	
		
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
         * @return returns Messaggi widget
         */
        public Output getLogMessagges() {
            return logMessagges;
        }

       
        /**
         * @return returns Messaggi value
         */
        public String getLogMessaggesValue() {
            return (String)logMessagges.getValue();
        }		
        
        /**
         * Set Messaggi value
         * @param value Messaggi value
         */
        public void setLogMessaggesValue(String value) {
            logMessagges.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Indicizza widget
         */
        public Action getSearchreindex() {
            return searchreindex;
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

