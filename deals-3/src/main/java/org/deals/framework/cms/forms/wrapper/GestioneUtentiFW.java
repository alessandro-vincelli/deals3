
package org.deals.framework.cms.forms.wrapper;
	
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


 public class GestioneUtentiFW  {			
    private Form form;
		
	private UtentiRepeaterWrapper utenti;	
	private RepeaterAction pagefirst;	
	private RepeaterAction pageprev;	
	private RepeaterAction pagenext;	
	private RepeaterAction pagelast;	
	private Field topage;	
	private RepeaterAction pagecustom;	
	private RepeaterAction sortnatural;	
	private RepeaterAction removeutente;	
	private RepeaterAction addutente;	
	private Action utentiaction;
		
	private Binding binding = null;		
		
	/**
	 * Effettua il wrapping del form passato
	 * @param f form da wrappare
	 */
	public GestioneUtentiFW(Form form) {
		this.form = form;
		pagefirst = (RepeaterAction)form.getChild("pagefirst");	
		pageprev = (RepeaterAction)form.getChild("pageprev");	
		pagenext = (RepeaterAction)form.getChild("pagenext");	
		pagelast = (RepeaterAction)form.getChild("pagelast");	
		topage = (Field)form.getChild("topage");	
		pagecustom = (RepeaterAction)form.getChild("pagecustom");	
		sortnatural = (RepeaterAction)form.getChild("sortnatural");	
		removeutente = (RepeaterAction)form.getChild("removeutente");	
		addutente = (RepeaterAction)form.getChild("addutente");	
		utenti = new UtentiRepeaterWrapper((Repeater)form.getChild("utenti"));
		utentiaction = (Action)form.getChild("utentiaction");
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
         * @return returns Utenti widget
         */
        public UtentiRepeaterWrapper getUtenti() {
            return utenti;
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
        public RepeaterAction getRemoveutente() {
            return removeutente;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Aggiungi nuovo utente widget
         */
        public RepeaterAction getAddutente() {
            return addutente;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
        
        public Action getUtentiaction() {
			return utentiaction;
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
        
		
	
	 public class UtentiRepeaterWrapper {
		
		private Repeater base;
				
		public UtentiRepeaterWrapper(Repeater base) {
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
         * @return returns us_id widget
         */
        public Field getUsId(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("usId");
         } 
         else return null;
        }

       
        /**
         * @return returns us_id value
         */
        public Integer getUsIdValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("usId").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set us_id value
         * @param value us_id value
         */
        public void setUsIdValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("usId").setValue(value);
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
         * @return returns Username widget
         */
        public Field getUsername(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("username");
         } 
         else return null;
        }

       
        /**
         * @return returns Username value
         */
        public String getUsernameValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("username").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Username value
         * @param value Username value
         */
        public void setUsernameValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("username").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Password widget
         */
        public Field getPassword(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("password");
         } 
         else return null;
        }

       
        /**
         * @return returns Password value
         */
        public String getPasswordValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("password").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Password value
         * @param value Password value
         */
        public void setPasswordValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("password").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Nome widget
         */
        public Field getNome(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("nome");
         } 
         else return null;
        }

       
        /**
         * @return returns Nome value
         */
        public String getNomeValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("nome").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Nome value
         * @param value Nome value
         */
        public void setNomeValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("nome").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Cognome widget
         */
        public Field getCognome(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("cognome");
         } 
         else return null;
        }

       
        /**
         * @return returns Cognome value
         */
        public String getCognomeValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("cognome").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Cognome value
         * @param value Cognome value
         */
        public void setCognomeValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("cognome").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Email widget
         */
        public Field getEmail(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("email");
         } 
         else return null;
        }

       
        /**
         * @return returns Email value
         */
        public String getEmailValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("email").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Email value
         * @param value Email value
         */
        public void setEmailValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("email").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Profilo widget
         */
        public Field getProfile(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("profile");
         } 
         else return null;
        }

       
        /**
         * @return returns Profilo value
         */
        public String getProfileValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("profile").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Profilo value
         * @param value Profilo value
         */
        public void setProfileValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("profile").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
			
		
		
				
		
		public Repeater getRepeater() {
		   return base;
		}
	 }	
	

   }

