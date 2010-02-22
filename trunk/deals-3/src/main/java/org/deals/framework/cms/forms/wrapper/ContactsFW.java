
package org.deals.framework.cms.forms.wrapper;
        
import org.apache.cocoon.forms.formmodel.Form;
import org.apache.cocoon.forms.binding.Binding;
import org.apache.cocoon.forms.binding.BindingException;

	
import org.apache.cocoon.forms.formmodel.Field;
import org.apache.cocoon.forms.formmodel.Submit;


 public class ContactsFW  {			

    private Form form;
        	
        private Field firstName;	
        private Field lastName;	
        private Field code;	
        private Field email;	
        private Field phoneNumber;	
        private Field content;	
        private Submit contactsubmit;	
                
        private Binding binding = null;		
                
        /**
         * Effettua il wrapping del form passato
         * @param f form da wrappare
         */
        public ContactsFW(Form form) {
                this.form = form;
                firstName = (Field)form.getChild("first_name");	
                lastName = (Field)form.getChild("last_name");	
                code = (Field)form.getChild("code");	
                email = (Field)form.getChild("email");	
                phoneNumber = (Field)form.getChild("phone_number");	
                content = (Field)form.getChild("content");	
                contactsubmit = (Submit)form.getChild("contactsubmit");	
                
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
         * @return returns Nome widget
         */
        public Field getFirstName() {
            return firstName;
        }

       
        /**
         * @return returns Nome value
         */
        public String getFirstNameValue() {
            return (String)firstName.getValue();
        }		
        
        /**
         * Set Nome value
         * @param value Nome value
         */
        public void setFirstNameValue(String value) {
            firstName.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
                
        /**
         * @return returns Cognome widget
         */
        public Field getLastName() {
            return lastName;
        }

       
        /**
         * @return returns Cognome value
         */
        public String getLastNameValue() {
            return (String)lastName.getValue();
        }		
        
        /**
         * Set Cognome value
         * @param value Cognome value
         */
        public void setLastNameValue(String value) {
            lastName.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
                
        /**
         * @return returns Codice widget
         */
        public Field getCode() {
            return code;
        }

       
        /**
         * @return returns Codice value
         */
        public String getCodeValue() {
            return (String)code.getValue();
        }		
        
        /**
         * Set Codice value
         * @param value Codice value
         */
        public void setCodeValue(String value) {
            code.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
                
        /**
         * @return returns Email widget
         */
        public Field getEmail() {
            return email;
        }

       
        /**
         * @return returns Email value
         */
        public String getEmailValue() {
            return (String)email.getValue();
        }		
        
        /**
         * Set Email value
         * @param value Email value
         */
        public void setEmailValue(String value) {
            email.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
                
        /**
         * @return returns Telefono widget
         */
        public Field getPhoneNumber() {
            return phoneNumber;
        }

       
        /**
         * @return returns Telefono value
         */
        public String getPhoneNumberValue() {
            return (String)phoneNumber.getValue();
        }		
        
        /**
         * Set Telefono value
         * @param value Telefono value
         */
        public void setPhoneNumberValue(String value) {
            phoneNumber.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
                
        /**
         * @return returns Richiesta widget
         */
        public Field getContent() {
            return content;
        }

       
        /**
         * @return returns Richiesta value
         */
        public String getContentValue() {
            return (String)content.getValue();
        }		
        
        /**
         * Set Richiesta value
         * @param value Richiesta value
         */
        public void setContentValue(String value) {
            content.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
                
        /**
         * @return returns INVIA widget
         */
        public Submit getContactsubmit() {
            return contactsubmit;
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

