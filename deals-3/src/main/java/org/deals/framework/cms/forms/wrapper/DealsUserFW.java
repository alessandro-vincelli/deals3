
package org.deals.framework.cms.forms.wrapper;
        
import org.apache.cocoon.forms.formmodel.Form;
import org.apache.cocoon.forms.binding.Binding;
import org.apache.cocoon.forms.binding.BindingException;

	
import org.apache.cocoon.forms.formmodel.Field;
import org.apache.cocoon.forms.formmodel.Submit;


 public class DealsUserFW  {			

    private Form form;
        	
        private Field usId;	
        private Field usRole;	
        private Field usUsername;	
        private Field usPassword;	
        private Submit usersubmit;	
                
        private Binding binding = null;		
                
        /**
         * Effettua il wrapping del form passato
         * @param f form da wrappare
         */
        public DealsUserFW(Form form) {
                this.form = form;
                usId = (Field)form.getChild("us_id");	
                usRole = (Field)form.getChild("us_role");	
                usUsername = (Field)form.getChild("us_username");	
                usPassword = (Field)form.getChild("us_password");	
                usersubmit = (Submit)form.getChild("usersubmit");	
                
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
         * @return returns Id utente widget
         */
        public Field getUsId() {
            return usId;
        }

       
        /**
         * @return returns Id utente value
         */
        public Integer getUsIdValue() {
            return (Integer)usId.getValue();
        }		
        
        /**
         * Set Id utente value
         * @param value Id utente value
         */
        public void setUsIdValue(Integer value) {
            usId.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
                
        /**
         * @return returns Ruolo widget
         */
        public Field getUsRole() {
            return usRole;
        }

       
        /**
         * @return returns Ruolo value
         */
        public Integer getUsRoleValue() {
            return (Integer)usRole.getValue();
        }		
        
        /**
         * Set Ruolo value
         * @param value Ruolo value
         */
        public void setUsRoleValue(Integer value) {
            usRole.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
                
        /**
         * @return returns Nome utente widget
         */
        public Field getUsUsername() {
            return usUsername;
        }

       
        /**
         * @return returns Nome utente value
         */
        public String getUsUsernameValue() {
            return (String)usUsername.getValue();
        }		
        
        /**
         * Set Nome utente value
         * @param value Nome utente value
         */
        public void setUsUsernameValue(String value) {
            usUsername.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
                
        /**
         * @return returns Password widget
         */
        public Field getUsPassword() {
            return usPassword;
        }

       
        /**
         * @return returns Password value
         */
        public String getUsPasswordValue() {
            return (String)usPassword.getValue();
        }		
        
        /**
         * Set Password value
         * @param value Password value
         */
        public void setUsPasswordValue(String value) {
            usPassword.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
                
        /**
         * @return returns SALVA widget
         */
        public Submit getUsersubmit() {
            return usersubmit;
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

