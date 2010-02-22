
package org.deals.framework.cms.forms.wrapper;
	
import org.apache.cocoon.forms.binding.Binding;
import org.apache.cocoon.forms.binding.BindingException;
import org.apache.cocoon.forms.formmodel.Field;
import org.apache.cocoon.forms.formmodel.Form;
import org.apache.cocoon.forms.formmodel.Submit;


 public class AuthenticationFW  {			

    private Form form;
    		
	private Field dealsUsername;	
	private Field dealsPassword;	
	private Submit dealsenter;	
		
	private Binding binding = null;		
		
	/**
	 * Effettua il wrapping del form passato
	 * @param f form da wrappare
	 */
	public AuthenticationFW(Form form) {
		this.form = form;
		dealsUsername = (Field)form.getChild("deals_username");	
		dealsPassword = (Field)form.getChild("deals_password");	
		dealsenter = (Submit)form.getChild("dealsenter");	
		
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
	    	
    		
    			//formInstance.load(o);
    		
	    }
		
		
	    /**
	     * Salva i dati del form sull'oggetto
	     * @param o oggetto su cui salvare
	     * @throws BindingException problemi in fase di binding
	     */
	    public void save(Object o) throws BindingException {
	    	//formInstance.save(o);
	    }		
		
				
		
		
        /**
         * @return returns Username widget
         */
        public Field getDealsUsername() {
            return dealsUsername;
        }

       
        /**
         * @return returns Username value
         */
        public String getDealsUsernameValue() {
            return (String)dealsUsername.getValue();
        }		
        
        /**
         * Set Username value
         * @param value Username value
         */
        public void setDealsUsernameValue(String value) {
            dealsUsername.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Password widget
         */
        public Field getDealsPassword() {
            return dealsPassword;
        }

       
        /**
         * @return returns Password value
         */
        public String getDealsPasswordValue() {
            return (String)dealsPassword.getValue();
        }		
        
        /**
         * Set Password value
         * @param value Password value
         */
        public void setDealsPasswordValue(String value) {
            dealsPassword.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns ENTRA widget
         */
        public Submit getDealsenter() {
            return dealsenter;
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

