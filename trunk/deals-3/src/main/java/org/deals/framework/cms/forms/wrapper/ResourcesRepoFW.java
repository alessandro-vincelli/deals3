
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
import org.apache.cocoon.forms.formmodel.Submit;
import org.apache.cocoon.forms.formmodel.Upload;
import org.apache.cocoon.forms.formmodel.Repeater.RepeaterRow;


 public class ResourcesRepoFW  {			
    private Form form;
		
	private webcomponentsRepeaterWrapper webcomponents;	
	private RepeaterAction removecomponents;	
	private RepeaterAction pagefirst;	
	private RepeaterAction pageprev;	
	private RepeaterAction pagenext;	
	private RepeaterAction pagelast;	
	private Field topage;	
	private RepeaterAction pagecustom;	
	private RepeaterAction sortbydate;	
	private RepeaterAction sortnatural;	
	private NewwebcomponentsRepeaterWrapper newwebcomponents;	
	private RepeaterAction addnewcomponents;	
	private RepeaterAction removenewcomponents;	
	private Submit componentssubmit;	
		
	private Binding binding = null;		
		
	/**
	 * Effettua il wrapping del form passato
	 * @param f form da wrappare
	 */
	public ResourcesRepoFW(Form form) {
		this.form = form;
		removecomponents = (RepeaterAction)form.getChild("removecomponents");	
		pagefirst = (RepeaterAction)form.getChild("pagefirst");	
		pageprev = (RepeaterAction)form.getChild("pageprev");	
		pagenext = (RepeaterAction)form.getChild("pagenext");	
		pagelast = (RepeaterAction)form.getChild("pagelast");	
		topage = (Field)form.getChild("topage");	
		pagecustom = (RepeaterAction)form.getChild("pagecustom");	
		sortbydate = (RepeaterAction)form.getChild("sortbydate");	
		sortnatural = (RepeaterAction)form.getChild("sortnatural");	
		addnewcomponents = (RepeaterAction)form.getChild("addnewcomponents");	
		removenewcomponents = (RepeaterAction)form.getChild("removenewcomponents");	
		componentssubmit = (Submit)form.getChild("componentssubmit");	
		webcomponents = new webcomponentsRepeaterWrapper((Repeater)form.getChild("webcomponents"));
		newwebcomponents = new NewwebcomponentsRepeaterWrapper((Repeater)form.getChild("newwebcomponents"));
		
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
         * @return returns Risorse widget
         */
        public webcomponentsRepeaterWrapper getwebcomponents() {
            return webcomponents;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Rimuovi la componente selezionato widget
         */
        public RepeaterAction getRemovecomponents() {
            return removecomponents;
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
         * @return returns Ordine risorse per data widget
         */
        public RepeaterAction getSortbydate() {
            return sortbydate;
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
         * @return returns Risorse widget
         */
        public NewwebcomponentsRepeaterWrapper getNewwebcomponents() {
            return newwebcomponents;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Aggiungi nuova componente widget
         */
        public RepeaterAction getAddnewcomponents() {
            return addnewcomponents;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Rimuovi la nuova componente selezionato widget
         */
        public RepeaterAction getRemovenewcomponents() {
            return removenewcomponents;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns SALVA widget
         */
        public Submit getComponentssubmit() {
            return componentssubmit;
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
        
		
	
	 public class webcomponentsRepeaterWrapper {
		
		private Repeater base;
					
				
		public webcomponentsRepeaterWrapper(Repeater base) {
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
         * @return returns wc_id widget
         */
        public Field getwcId(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("wc_id");
         } 
         else return null;
        }

       
        /**
         * @return returns wc_id value
         */
        public Integer getwcIdValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("wc_id").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set wc_id value
         * @param value wc_id value
         */
        public void setwcIdValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wc_id").setValue(value);
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
         * @return returns Nome della risorsa widget
         */
        public Field getwrName(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("wr_name");
         } 
         else return null;
        }

       
        /**
         * @return returns Nome della risorsa value
         */
        public String getwrNameValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("wr_name").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Nome della risorsa value
         * @param value Nome della risorsa value
         */
        public void setwrNameValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wr_name").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Descrione della risorsa widget
         */
        public Field getwrDescription(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("wr_description");
         } 
         else return null;
        }

       
        /**
         * @return returns Descrione della risorsa value
         */
        public String getwrDescriptionValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("wr_description").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Descrione della risorsa value
         * @param value Descrione della risorsa value
         */
        public void setwrDescriptionValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wr_description").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Mime type widget
         */
        public Field getwrMimetype(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("wr_mimetype");
         } 
         else return null;
        }

       
        /**
         * @return returns Mime type value
         */
        public String getwrMimetypeValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("wr_mimetype").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Mime type value
         * @param value Mime type value
         */
        public void setwrMimetypeValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wr_mimetype").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Alt della risorsa widget
         */
        public Field getwrAlt(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("wr_alt");
         } 
         else return null;
        }

       
        /**
         * @return returns Alt della risorsa value
         */
        public String getwrAltValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("wr_alt").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Alt della risorsa value
         * @param value Alt della risorsa value
         */
        public void setwrAltValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wr_alt").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Tipo della risorsa widget
         */
        public Field getwcType(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("wc_type");
         } 
         else return null;
        }

       
        /**
         * @return returns Tipo della risorsa value
         */
        public Integer getwcTypeValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("wc_type").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Tipo della risorsa value
         * @param value Tipo della risorsa value
         */
        public void setwcTypeValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wc_type").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Data widget
         */
        public Field getwcInsertDate(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("wc_insert_date");
         } 
         else return null;
        }

       
        /**
         * @return returns Data value
         */
        public Date getwcInsertDateValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Date)r.getChild("wc_insert_date").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Data value
         * @param value Data value
         */
        public void setwcInsertDateValue(Date value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wc_insert_date").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns  widget
         */
        public Action getDeleteitem(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Action)r.getChild("deleteitem");
         } 
         else return null;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Upload some pdf files widget
         */
        public Upload getUpload(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Upload)r.getChild("upload");
         } 
         else return null;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
			
		
		
				
		
		public Repeater getRepeater() {
		   return base;
		}
	 }	
	
	 public class NewwebcomponentsRepeaterWrapper {
		
		private Repeater base;
					
				
		public NewwebcomponentsRepeaterWrapper(Repeater base) {
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
         * @return returns wc_id widget
         */
        public Field getwcId(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("wc_id");
         } 
         else return null;
        }

       
        /**
         * @return returns wc_id value
         */
        public Integer getwcIdValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("wc_id").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set wc_id value
         * @param value wc_id value
         */
        public void setwcIdValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wc_id").setValue(value);
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
         * @return returns Nome della risorsa widget
         */
        public Field getwrName(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("wr_name");
         } 
         else return null;
        }

       
        /**
         * @return returns Nome della risorsa value
         */
        public String getwrNameValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("wr_name").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Nome della risorsa value
         * @param value Nome della risorsa value
         */
        public void setwrNameValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wr_name").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Descrione della risorsa widget
         */
        public Field getwrDescription(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("wr_description");
         } 
         else return null;
        }

       
        /**
         * @return returns Descrione della risorsa value
         */
        public String getwrDescriptionValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("wr_description").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Descrione della risorsa value
         * @param value Descrione della risorsa value
         */
        public void setwrDescriptionValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wr_description").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Mime type widget
         */
        public Field getwrMimetype(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("wr_mimetype");
         } 
         else return null;
        }

       
        /**
         * @return returns Mime type value
         */
        public String getwrMimetypeValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("wr_mimetype").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Mime type value
         * @param value Mime type value
         */
        public void setwrMimetypeValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wr_mimetype").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Alt della risorsa widget
         */
        public Field getwrAlt(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("wr_alt");
         } 
         else return null;
        }

       
        /**
         * @return returns Alt della risorsa value
         */
        public String getwrAltValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("wr_alt").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Alt della risorsa value
         * @param value Alt della risorsa value
         */
        public void setwrAltValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wr_alt").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Tipo della risorsa widget
         */
        public Field getwcType(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("wc_type");
         } 
         else return null;
        }

       
        /**
         * @return returns Tipo della risorsa value
         */
        public Integer getwcTypeValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("wc_type").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Tipo della risorsa value
         * @param value Tipo della risorsa value
         */
        public void setwcTypeValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wc_type").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Upload some pdf files widget
         */
        public Upload getUpload(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Upload)r.getChild("upload");
         } 
         else return null;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
					
		public Repeater getRepeater() {
		   return base;
		}
	 }	
	 
	
	 
	 
	 

   }

