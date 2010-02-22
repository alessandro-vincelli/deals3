
package org.deals.framework.cms.forms.wrapper;
	
import org.apache.cocoon.forms.formmodel.Form;
import org.apache.cocoon.forms.binding.Binding;
import org.apache.cocoon.forms.binding.BindingException;

	
import org.apache.cocoon.forms.formmodel.Field;	
import org.apache.cocoon.forms.formmodel.Action;	
import org.apache.cocoon.forms.formmodel.BooleanField;
import org.apache.cocoon.forms.formmodel.Repeater;
import org.apache.cocoon.forms.formmodel.Repeater.RepeaterRow;
import java.util.Date;


 public class ManagementFW  {			

    private Form form;
		
	private ItemsRepeaterWrapper items;	
	private CategoriesRepeaterWrapper categories;	
	private SectionsRepeaterWrapper sections;	
		
	private Binding binding = null;		
		
	/**
	 * Effettua il wrapping del form passato
	 * @param f form da wrappare
	 */
	public ManagementFW(Form form) {
		this.form = form;
		items = new ItemsRepeaterWrapper((Repeater)form.getChild("items"));
		categories = new CategoriesRepeaterWrapper((Repeater)form.getChild("categories"));
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
         * @return returns Schede widget
         */
        public ItemsRepeaterWrapper getItems() {
            return items;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Categorie widget
         */
        public CategoriesRepeaterWrapper getCategories() {
            return categories;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Sezioni widget
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
        
		
	
	 public class ItemsRepeaterWrapper {
		
		private Repeater base;
			
				
		public ItemsRepeaterWrapper(Repeater base) {
		  // mette il repeater in locale
		  this.base = base;
		}
		
		
        /**
         * @return returns Titolo widget
         */
        public Field getLabelitem(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("labelitem");
         } 
         else return null;
        }

       
        /**
         * @return returns Titolo value
         */
        public String getLabelitemValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("labelitem").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Titolo value
         * @param value Titolo value
         */
        public void setLabelitemValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("labelitem").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Id logico widget
         */
        public Field getIditem(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("iditem");
         } 
         else return null;
        }

       
        /**
         * @return returns Id logico value
         */
        public Integer getIditemValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("iditem").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Id logico value
         * @param value Id logico value
         */
        public void setIditemValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("iditem").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Id widget
         */
        public Field getweightitem(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("weightitem");
         } 
         else return null;
        }

       
        /**
         * @return returns Id value
         */
        public Integer getweightitemValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("weightitem").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Id value
         * @param value Id value
         */
        public void setweightitemValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("weightitem").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Status widget
         */
        public Field getStatusitem(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("statusitem");
         } 
         else return null;
        }

       
        /**
         * @return returns Status value
         */
        public String getStatusitemValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("statusitem").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Status value
         * @param value Status value
         */
        public void setStatusitemValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("statusitem").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Scheda principale widget
         */
        public Field getTopitem(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("topitem");
         } 
         else return null;
        }

       
        /**
         * @return returns Scheda principale value
         */
        public String getTopitemValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("topitem").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Scheda principale value
         * @param value Scheda principale value
         */
        public void setTopitemValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("topitem").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Scadenza widget
         */
        public Field getExpirationitem(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("expirationitem");
         } 
         else return null;
        }

       
        /**
         * @return returns Scadenza value
         */
        public Date getExpirationitemValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Date)r.getChild("expirationitem").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Scadenza value
         * @param value Scadenza value
         */
        public void setExpirationitemValue(Date value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("expirationitem").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Data widget
         */
        public Field getDateitem(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("dateitem");
         } 
         else return null;
        }

       
        /**
         * @return returns Data value
         */
        public Date getDateitemValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Date)r.getChild("dateitem").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Data value
         * @param value Data value
         */
        public void setDateitemValue(Date value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("dateitem").setValue(value);
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
			
		
		
				
		
		public Repeater getRepeater() {
		   return base;
		}
	 }	
	
	 public class CategoriesRepeaterWrapper {
		
		private Repeater base;
			
				
		public CategoriesRepeaterWrapper(Repeater base) {
		  // mette il repeater in locale
		  this.base = base;
		}
		
		
        /**
         * @return returns Titolo widget
         */
        public Field getLabelcategory(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("labelcategory");
         } 
         else return null;
        }

       
        /**
         * @return returns Titolo value
         */
        public String getLabelcategoryValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("labelcategory").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Titolo value
         * @param value Titolo value
         */
        public void setLabelcategoryValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("labelcategory").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Status widget
         */
        public Field getStatuscategory(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("statuscategory");
         } 
         else return null;
        }

       
        /**
         * @return returns Status value
         */
        public String getStatuscategoryValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("statuscategory").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Status value
         * @param value Status value
         */
        public void setStatuscategoryValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("statuscategory").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Categoria principale widget
         */
        public Field getTopcategory(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("topcategory");
         } 
         else return null;
        }

       
        /**
         * @return returns Categoria principale value
         */
        public String getTopcategoryValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("topcategory").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Categoria principale value
         * @param value Categoria principale value
         */
        public void setTopcategoryValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("topcategory").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Id logico widget
         */
        public Field getIdcategory(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("idcategory");
         } 
         else return null;
        }

       
        /**
         * @return returns Id logico value
         */
        public Integer getIdcategoryValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("idcategory").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Id logico value
         * @param value Id logico value
         */
        public void setIdcategoryValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("idcategory").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Id widget
         */
        public Field getweightcategory(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("weightcategory");
         } 
         else return null;
        }

       
        /**
         * @return returns Id value
         */
        public Integer getweightcategoryValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("weightcategory").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Id value
         * @param value Id value
         */
        public void setweightcategoryValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("weightcategory").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
			
		
		
				
		
		public Repeater getRepeater() {
		   return base;
		}
	 }	
	
	 public class SectionsRepeaterWrapper {
		
		private Repeater base;
			
				
		public SectionsRepeaterWrapper(Repeater base) {
		  // mette il repeater in locale
		  this.base = base;
		}
		
		
        /**
         * @return returns Titolo widget
         */
        public Field getLabelsection(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("labelsection");
         } 
         else return null;
        }

       
        /**
         * @return returns Titolo value
         */
        public String getLabelsectionValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("labelsection").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Titolo value
         * @param value Titolo value
         */
        public void setLabelsectionValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("labelsection").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Id logico widget
         */
        public Field getIdsection(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("idsection");
         } 
         else return null;
        }

       
        /**
         * @return returns Id logico value
         */
        public Integer getIdsectionValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("idsection").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Id logico value
         * @param value Id logico value
         */
        public void setIdsectionValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("idsection").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Id widget
         */
        public Field getweightsection(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("weightsection");
         } 
         else return null;
        }

       
        /**
         * @return returns Id value
         */
        public Integer getweightsectionValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("weightsection").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Id value
         * @param value Id value
         */
        public void setweightsectionValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("weightsection").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Select widget
         */
        public BooleanField getSelect(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (BooleanField)r.getChild("select");
         } 
         else return null;
        }

       
        /**
         * @return returns Select value
         */
        public Boolean getSelectValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Boolean)r.getChild("select").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Select value
         * @param value Select value
         */
        public void setSelectValue(Boolean value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("select").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns SELEZIONA widget
         */
        public Action getBselection(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Action)r.getChild("bselection");
         } 
         else return null;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
			
		
		
				
		
		public Repeater getRepeater() {
		   return base;
		}
	 }	
	

   }

