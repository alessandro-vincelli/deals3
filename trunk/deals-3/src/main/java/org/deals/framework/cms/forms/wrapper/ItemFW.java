
package org.deals.framework.cms.forms.wrapper;
	
import java.util.Date;

import org.apache.cocoon.forms.binding.Binding;
import org.apache.cocoon.forms.binding.BindingException;
import org.apache.cocoon.forms.formmodel.Action;
import org.apache.cocoon.forms.formmodel.BooleanField;
import org.apache.cocoon.forms.formmodel.Field;
import org.apache.cocoon.forms.formmodel.Form;
import org.apache.cocoon.forms.formmodel.ImageMap;
import org.apache.cocoon.forms.formmodel.Output;
import org.apache.cocoon.forms.formmodel.Repeater;
import org.apache.cocoon.forms.formmodel.RepeaterAction;
import org.apache.cocoon.forms.formmodel.RowAction;
import org.apache.cocoon.forms.formmodel.Submit;
import org.apache.cocoon.forms.formmodel.Upload;
import org.apache.cocoon.forms.formmodel.Repeater.RepeaterRow;


 public class ItemFW  {			
    private Form form;
		
	private Field tabState;	
	private BooleanField checkChangeAuthor;	
	private Field itemId;	
	private Field itemAuthor;	
	private Field itemCreator;	
	private Field itemTemplate;	
	private Field itemDate;	
	private Field itemExpiration;	
	private BooleanField itemNoexpiration;	
	private Field itemTitle;	
	private Field itemShort;	
	private Field itemBody;	
	private Field itemState;	
	private Upload uploadImage;	
	private ImageMap itemimage;	
	private Field itemLabelimage;	
	private Upload uploadThumbnail;	
	private ImageMap itemthumbnail;	
	private Field itemLabelthumbnail;	
	private BooleanField itemPrint;	
	private BooleanField itemSend;	
	private BooleanField itemCommentsOn;	
	private Field itemMetaTitle;	
	private Field itemMetaDescription;	
	private Field itemMetakeywords;	
	private Submit itemsubmit;	
	private Submit itemdelete;	
	private Submit itemnodelete;	
	private SectionsRepeaterWrapper sections;	
	private CategoriesRepeaterWrapper categories;
	private PagesassociationsRepeaterWrapper pagesassociations;
	private Pagesassociations2RepeaterWrapper pagesassociations2;
	private Action additemfile;	
	private ItemfilesRepeaterWrapper itemfiles;	
	private Field itemlink;	
	private Field itemlinkdesc;	
	private Action additemlink;	
	private ItemlinksRepeaterWrapper itemlinks;	
	private RepeaterAction addlink;	
	private RepeaterAction removelink;	
	private Action addnewimages;	
	private RepeaterAction removeimages;	
	private ImagesRepeaterWrapper images;	
	private RepeaterAction imagessortbydate;	
	private RepeaterAction imagessortnatural;	

	private Action apcfilterbutton;	
	private ImageswebcomponentsRepeaterWrapper imageswebcomponents;	
	private RepeaterAction wcsortbydate;	
	private RepeaterAction wcsortnatural;	
	private Action wcfilterbutton;	
	private RepeaterAction pagefirst;	
	private RepeaterAction pageprev;	
	private RepeaterAction pagenext;	
	private RepeaterAction pagelast;	
	private Field topage;	
	private RepeaterAction pagecustom;	
	private ItemtagsRepeaterWrapper itemtags;	
	private PhotogallerywebcomponentsRepeaterWrapper photogallerywebcomponents;	
	private PhotogalleryRepeaterWrapper photogallery;	
		
	private Binding binding = null;		
		
	/**
	 * Effettua il wrapping del form passato
	 * @param f form da wrappare
	 */
	public ItemFW(Form form) {
		this.form = form;
		tabState = (Field)form.getChild("tab_state");	
		checkChangeAuthor = (BooleanField)form.getChild("check_change_author");	
		itemId = (Field)form.getChild("item_id");	
		itemAuthor = (Field)form.getChild("item_author");	
		itemCreator = (Field)form.getChild("item_creator");	
		itemTemplate = (Field)form.getChild("item_template");	
		itemDate = (Field)form.getChild("item_date");	
		itemExpiration = (Field)form.getChild("item_expiration");	
		itemNoexpiration = (BooleanField)form.getChild("item_noexpiration");	
		itemTitle = (Field)form.getChild("item_title");	
		itemShort = (Field)form.getChild("item_short");	
		itemBody = (Field)form.getChild("item_body");	
		itemState = (Field)form.getChild("item_state");	
		uploadImage = (Upload)form.getChild("upload_image");	
		itemimage = (ImageMap)form.getChild("itemimage");	
		itemLabelimage = (Field)form.getChild("item_labelimage");	
		uploadThumbnail = (Upload)form.getChild("upload_thumbnail");	
		itemthumbnail = (ImageMap)form.getChild("itemthumbnail");	
		itemLabelthumbnail = (Field)form.getChild("item_labelthumbnail");	
		itemPrint = (BooleanField)form.getChild("item_print");	
		itemSend = (BooleanField)form.getChild("item_send");	
		itemCommentsOn = (BooleanField)form.getChild("item_comments_on");	
		itemMetaTitle = (Field)form.getChild("item_meta_title");	
		itemMetaDescription = (Field)form.getChild("item_meta_description");	
		itemMetakeywords = (Field)form.getChild("item_meta_keywords");	
		itemsubmit = (Submit)form.getChild("itemsubmit");	
		itemdelete = (Submit)form.getChild("itemdelete");	
		itemnodelete = (Submit)form.getChild("itemnodelete");	
		additemfile = (Action)form.getChild("additemfile");	
		itemlink = (Field)form.getChild("itemlink");	
		itemlinkdesc = (Field)form.getChild("itemlinkdesc");	
		additemlink = (Action)form.getChild("additemlink");	
		addlink = (RepeaterAction)form.getChild("addlink");	
		removelink = (RepeaterAction)form.getChild("removelink");	
		addnewimages = (Action)form.getChild("addnewimages");	
		removeimages = (RepeaterAction)form.getChild("removeimages");	
		imagessortbydate = (RepeaterAction)form.getChild("imagessortbydate");	
		imagessortnatural = (RepeaterAction)form.getChild("imagessortnatural");	
		apcfilterbutton = (Action)form.getChild("apcfilterbutton");	
		wcsortbydate = (RepeaterAction)form.getChild("wcsortbydate");	
		wcsortnatural = (RepeaterAction)form.getChild("wcsortnatural");	
		wcfilterbutton = (Action)form.getChild("wcfilterbutton");	
		pagefirst = (RepeaterAction)form.getChild("pagefirst");	
		pageprev = (RepeaterAction)form.getChild("pageprev");	
		pagenext = (RepeaterAction)form.getChild("pagenext");	
		pagelast = (RepeaterAction)form.getChild("pagelast");	
		topage = (Field)form.getChild("topage");	
		pagecustom = (RepeaterAction)form.getChild("pagecustom");	
		sections = new SectionsRepeaterWrapper((Repeater)form.getChild("sections"));
		categories = new CategoriesRepeaterWrapper((Repeater)form.getChild("categories"));
		itemfiles = new ItemfilesRepeaterWrapper((Repeater)form.getChild("itemfiles"));
		itemlinks = new ItemlinksRepeaterWrapper((Repeater)form.getChild("itemlinks"));
		images = new ImagesRepeaterWrapper((Repeater)form.getChild("images"));
		imageswebcomponents = new ImageswebcomponentsRepeaterWrapper((Repeater)form.getChild("imageswebcomponents"));
		pagesassociations = new PagesassociationsRepeaterWrapper((Repeater)form.getChild("pagesassociations"));
		itemtags = new ItemtagsRepeaterWrapper((Repeater)form.getChild("itemtags"));
		photogallerywebcomponents = new PhotogallerywebcomponentsRepeaterWrapper((Repeater)form.getChild("photogallerywebcomponents"));
		pagesassociations2 = new Pagesassociations2RepeaterWrapper((Repeater)form.getChild("pagesassociations2"));
		photogallery = new PhotogalleryRepeaterWrapper((Repeater)form.getChild("photogallery"));
		
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
         * @return returns Conferma widget
         */
        public BooleanField getCheckChangeAuthor() {
            return checkChangeAuthor;
        }

       
        /**
         * @return returns Conferma value
         */
        public Boolean getCheckChangeAuthorValue() {
            return (Boolean)checkChangeAuthor.getValue();
        }		
        
        /**
         * Set Conferma value
         * @param value Conferma value
         */
        public void setCheckChangeAuthorValue(Boolean value) {
            checkChangeAuthor.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Chiave primaria widget
         */
        public Field getItemId() {
            return itemId;
        }

       
        /**
         * @return returns Chiave primaria value
         */
        public Integer getItemIdValue() {
            return (Integer)itemId.getValue();
        }		
        
        /**
         * Set Chiave primaria value
         * @param value Chiave primaria value
         */
        public void setItemIdValue(Integer value) {
            itemId.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Autore della scheda specificato manuale widget
         */
        public Field getItemAuthor() {
            return itemAuthor;
        }

       
        /**
         * @return returns Autore della scheda specificato manuale value
         */
        public String getItemAuthorValue() {
            return (String)itemAuthor.getValue();
        }		
        
        /**
         * Set Autore della scheda specificato manuale value
         * @param value Autore della scheda specificato manuale value
         */
        public void setItemAuthorValue(String value) {
            itemAuthor.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Autore widget
         */
        public Field getItemCreator() {
            return itemCreator;
        }

       
        /**
         * @return returns Autore value
         */
        public String getItemCreatorValue() {
            return (String)itemCreator.getValue();
        }		
        
        /**
         * Set Autore value
         * @param value Autore value
         */
        public void setItemCreatorValue(String value) {
            itemCreator.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Template applicato widget
         */
        public Field getItemTemplate() {
            return itemTemplate;
        }

       
        /**
         * @return returns Template applicato value
         */
        public Integer getItemTemplateValue() {
            return (Integer)itemTemplate.getValue();
        }		
        
        /**
         * Set Template applicato value
         * @param value Template applicato value
         */
        public void setItemTemplateValue(Integer value) {
            itemTemplate.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Data creazione widget
         */
        public Field getItemDate() {
            return itemDate;
        }

       
        /**
         * @return returns Data creazione value
         */
        public Date getItemDateValue() {
            return (Date)itemDate.getValue();
        }		
        
        /**
         * Set Data creazione value
         * @param value Data creazione value
         */
        public void setItemDateValue(Date value) {
            itemDate.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Data scadenza widget
         */
        public Field getItemExpiration() {
            return itemExpiration;
        }

       
        /**
         * @return returns Data scadenza value
         */
        public Date getItemExpirationValue() {
            return (Date)itemExpiration.getValue();
        }		
        
        /**
         * Set Data scadenza value
         * @param value Data scadenza value
         */
        public void setItemExpirationValue(Date value) {
            itemExpiration.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Mai widget
         */
        public BooleanField getItemNoexpiration() {
            return itemNoexpiration;
        }

       
        /**
         * @return returns Mai value
         */
        public Boolean getItemNoexpirationValue() {
            return (Boolean)itemNoexpiration.getValue();
        }		
        
        /**
         * Set Mai value
         * @param value Mai value
         */
        public void setItemNoexpirationValue(Boolean value) {
            itemNoexpiration.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Titolo della scheda widget
         */
        public Field getItemTitle() {
            return itemTitle;
        }

       
        /**
         * @return returns Titolo della scheda value
         */
        public String getItemTitleValue() {
            return (String)itemTitle.getValue();
        }		
        
        /**
         * Set Titolo della scheda value
         * @param value Titolo della scheda value
         */
        public void setItemTitleValue(String value) {
            itemTitle.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Occhiello della scheda widget
         */
        public Field getItemShort() {
            return itemShort;
        }

       
        /**
         * @return returns Occhiello della scheda value
         */
        public String getItemShortValue() {
            return (String)itemShort.getValue();
        }		
        
        /**
         * Set Occhiello della scheda value
         * @param value Occhiello della scheda value
         */
        public void setItemShortValue(String value) {
            itemShort.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Corpo della scheda widget
         */
        public Field getItemBody() {
            return itemBody;
        }

       
        /**
         * @return returns Corpo della scheda value
         */
        public String getItemBodyValue() {
            return (String)itemBody.getValue();
        }		
        
        /**
         * Set Corpo della scheda value
         * @param value Corpo della scheda value
         */
        public void setItemBodyValue(String value) {
            itemBody.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Status widget
         */
        public Field getItemState() {
            return itemState;
        }

       
        /**
         * @return returns Status value
         */
        public Integer getItemStateValue() {
            return (Integer)itemState.getValue();
        }		
        
        /**
         * Set Status value
         * @param value Status value
         */
        public void setItemStateValue(Integer value) {
            itemState.setValue(value);
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
         * @return returns  widget
         */
        public ImageMap getItemimage() {
            return itemimage;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Didascalia widget
         */
        public Field getItemLabelimage() {
            return itemLabelimage;
        }

       
        /**
         * @return returns Didascalia value
         */
        public String getItemLabelimageValue() {
            return (String)itemLabelimage.getValue();
        }		
        
        /**
         * Set Didascalia value
         * @param value Didascalia value
         */
        public void setItemLabelimageValue(String value) {
            itemLabelimage.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Carica widget
         */
        public Upload getUploadThumbnail() {
            return uploadThumbnail;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns  widget
         */
        public ImageMap getItemthumbnail() {
            return itemthumbnail;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Didascalia widget
         */
        public Field getItemLabelthumbnail() {
            return itemLabelthumbnail;
        }

       
        /**
         * @return returns Didascalia value
         */
        public String getItemLabelthumbnailValue() {
            return (String)itemLabelthumbnail.getValue();
        }		
        
        /**
         * Set Didascalia value
         * @param value Didascalia value
         */
        public void setItemLabelthumbnailValue(String value) {
            itemLabelthumbnail.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Stampa widget
         */
        public BooleanField getItemPrint() {
            return itemPrint;
        }

       
        /**
         * @return returns Stampa value
         */
        public Boolean getItemPrintValue() {
            return (Boolean)itemPrint.getValue();
        }		
        
        /**
         * Set Stampa value
         * @param value Stampa value
         */
        public void setItemPrintValue(Boolean value) {
            itemPrint.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Invio widget
         */
        public BooleanField getItemSend() {
            return itemSend;
        }

       
        /**
         * @return returns Invio value
         */
        public Boolean getItemSendValue() {
            return (Boolean)itemSend.getValue();
        }		
        
        /**
         * Set Invio value
         * @param value Invio value
         */
        public void setItemSendValue(Boolean value) {
            itemSend.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Commenti widget
         */
        public BooleanField getItemCommentsOn() {
            return itemCommentsOn;
        }

       
        /**
         * @return returns Commenti value
         */
        public Boolean getItemCommentsOnValue() {
            return (Boolean)itemCommentsOn.getValue();
        }		
        
        /**
         * Set Commenti value
         * @param value Commenti value
         */
        public void setItemCommentsOnValue(Boolean value) {
            itemCommentsOn.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Title widget
         */
        public Field getItemMetaTitle() {
            return itemMetaTitle;
        }

       
        /**
         * @return returns Title value
         */
        public String getItemMetaTitleValue() {
            return (String)itemMetaTitle.getValue();
        }		
        
        /**
         * Set Title value
         * @param value Title value
         */
        public void setItemMetaTitleValue(String value) {
            itemMetaTitle.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Description widget
         */
        public Field getItemMetaDescription() {
            return itemMetaDescription;
        }

       
        /**
         * @return returns Description value
         */
        public String getItemMetaDescriptionValue() {
            return (String)itemMetaDescription.getValue();
        }		
        
        /**
         * Set Description value
         * @param value Description value
         */
        public void setItemMetaDescriptionValue(String value) {
            itemMetaDescription.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Keywords widget
         */
        public Field getItemMetakeywords() {
            return itemMetakeywords;
        }

       
        /**
         * @return returns Keywords value
         */
        public String getItemMetakeywordsValue() {
            return (String)itemMetakeywords.getValue();
        }		
        
        /**
         * Set Keywords value
         * @param value Keywords value
         */
        public void setItemMetakeywordsValue(String value) {
            itemMetakeywords.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns SALVA widget
         */
        public Submit getItemsubmit() {
            return itemsubmit;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns CONFERMA CANCELLAZIONE widget
         */
        public Submit getItemdelete() {
            return itemdelete;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns ANNULLA CANCELLAZIONE widget
         */
        public Submit getItemnodelete() {
            return itemnodelete;
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
         * @return returns aggiungi widget
         */
        public Action getAdditemfile() {
            return additemfile;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns  widget
         */
        public ItemfilesRepeaterWrapper getItemfiles() {
            return itemfiles;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns  widget
         */
        public Field getItemlink() {
            return itemlink;
        }

       
        /**
         * @return returns  value
         */
        public String getItemlinkValue() {
            return (String)itemlink.getValue();
        }		
        
        /**
         * Set  value
         * @param value  value
         */
        public void setItemlinkValue(String value) {
            itemlink.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns  widget
         */
        public Field getItemlinkdesc() {
            return itemlinkdesc;
        }

       
        /**
         * @return returns  value
         */
        public String getItemlinkdescValue() {
            return (String)itemlinkdesc.getValue();
        }		
        
        /**
         * Set  value
         * @param value  value
         */
        public void setItemlinkdescValue(String value) {
            itemlinkdesc.setValue(value);
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns aggiungi widget
         */
        public Action getAdditemlink() {
            return additemlink;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns  widget
         */
        public ItemlinksRepeaterWrapper getItemlinks() {
            return itemlinks;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Aggiungi link widget
         */
        public RepeaterAction getAddlink() {
            return addlink;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Rimuovi il link selezionato widget
         */
        public RepeaterAction getRemovelink() {
            return removelink;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Inserisci la selezione widget
         */
        public Action getAddnewimages() {
            return addnewimages;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Rimuovi le risorse selezionate widget
         */
        public RepeaterAction getRemoveimages() {
            return removeimages;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Risorse widget
         */
        public ImagesRepeaterWrapper getImages() {
            return images;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Ordine risorse per data widget
         */
        public RepeaterAction getImagessortbydate() {
            return imagessortbydate;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Naturale widget
         */
        public RepeaterAction getImagessortnatural() {
            return imagessortnatural;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
       
       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Applica filtri widget
         */
        public Action getApcfilterbutton() {
            return apcfilterbutton;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Risorse widget
         */
        public ImageswebcomponentsRepeaterWrapper getImageswebcomponents() {
            return imageswebcomponents;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Ordine risorse per data widget
         */
        public RepeaterAction getwcsortbydate() {
            return wcsortbydate;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Naturale widget
         */
        public RepeaterAction getwcsortnatural() {
            return wcsortnatural;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Applica filtri widget
         */
        public Action getwcfilterbutton() {
            return wcfilterbutton;
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
         * @return returns  widget
         */
        public ItemtagsRepeaterWrapper getItemtags() {
            return itemtags;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Risorse widget
         */
        public PhotogallerywebcomponentsRepeaterWrapper getPhotogallerywebcomponents() {
            return photogallerywebcomponents;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
        
		
        /**
         * @return returns Risorse widget
         */
        public PhotogalleryRepeaterWrapper getPhotogallery() {
            return photogallery;
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
		private CategoriesRepeaterWrapper categories;	
		
				
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
		
        /**
         * @return returns  widget
         */
        public CategoriesRepeaterWrapper getCategories(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (CategoriesRepeaterWrapper)r.getChild("categories");
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
			
		private Field idcategory;	
		private BooleanField checkcategory;	
		private Field labelcategory;	
		
				
		public CategoriesRepeaterWrapper(Repeater base) {
		  // mette il repeater in locale
		  this.base = base;
		}
		
		
        /**
         * @return returns  widget
         */
        public Field getIdcategory(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("idcategory");
         } 
         else return null;
        }

       
        /**
         * @return returns  value
         */
        public Integer getIdcategoryValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("idcategory").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set  value
         * @param value  value
         */
        public void setIdcategoryValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("idcategory").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Nome categoria widget
         */
        public BooleanField getCheckcategory(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (BooleanField)r.getChild("checkcategory");
         } 
         else return null;
        }

       
        /**
         * @return returns Nome categoria value
         */
        public Boolean getCheckcategoryValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Boolean)r.getChild("checkcategory").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Nome categoria value
         * @param value Nome categoria value
         */
        public void setCheckcategoryValue(Boolean value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("checkcategory").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns  widget
         */
        public Field getLabelcategory(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("labelcategory");
         } 
         else return null;
        }

       
        /**
         * @return returns  value
         */
        public String getLabelcategoryValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("labelcategory").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set  value
         * @param value  value
         */
        public void setLabelcategoryValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("labelcategory").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
			
		
		
				
		
		public Repeater getRepeater() {
		   return base;
		}
	 }	
	
	 public class ItemfilesRepeaterWrapper {
		
		private Repeater base;
			
		private Field iditemfile;	
		private Field itemfilename;	
		private Field itemfiledesc;	
		private Field itemfilemimetype;	
		private Field itemfilehref;	
		private Action itemfiledelete;	
		
				
		public ItemfilesRepeaterWrapper(Repeater base) {
		  // mette il repeater in locale
		  this.base = base;
		}
		
		
        /**
         * @return returns  widget
         */
        public Field getIditemfile(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("iditemfile");
         } 
         else return null;
        }

       
        /**
         * @return returns  value
         */
        public Integer getIditemfileValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("iditemfile").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set  value
         * @param value  value
         */
        public void setIditemfileValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("iditemfile").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns  widget
         */
        public Field getItemfilename(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("itemfilename");
         } 
         else return null;
        }

       
        /**
         * @return returns  value
         */
        public String getItemfilenameValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("itemfilename").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set  value
         * @param value  value
         */
        public void setItemfilenameValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("itemfilename").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns  widget
         */
        public Field getItemfiledesc(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("itemfiledesc");
         } 
         else return null;
        }

       
        /**
         * @return returns  value
         */
        public String getItemfiledescValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("itemfiledesc").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set  value
         * @param value  value
         */
        public void setItemfiledescValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("itemfiledesc").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns  widget
         */
        public Field getItemfilemimetype(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("itemfilemimetype");
         } 
         else return null;
        }

       
        /**
         * @return returns  value
         */
        public String getItemfilemimetypeValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("itemfilemimetype").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set  value
         * @param value  value
         */
        public void setItemfilemimetypeValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("itemfilemimetype").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns  widget
         */
        public Field getItemfilehref(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("itemfilehref");
         } 
         else return null;
        }

       
        /**
         * @return returns  value
         */
        public String getItemfilehrefValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("itemfilehref").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set  value
         * @param value  value
         */
        public void setItemfilehrefValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("itemfilehref").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns cancella widget
         */
        public Action getItemfiledelete(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Action)r.getChild("itemfiledelete");
         } 
         else return null;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
			
		
		
				
		
		public Repeater getRepeater() {
		   return base;
		}
	 }	
	
	 public class ItemlinksRepeaterWrapper {
		
		private Repeater base;
			
		private Output rowid;	
		private Field itemidlink;	
		private Field iditemlink;	
		private Field itemlinkdesk;	
		private Field itemlink;	
		private Action itemlinkdelete;	
		private RowAction up;	
		private RowAction down;	
		private BooleanField select;	
		
				
		public ItemlinksRepeaterWrapper(Repeater base) {
		  // mette il repeater in locale
		  this.base = base;
		}
		
		
        /**
         * @return returns Id widget
         */
        public Output getRowid(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("rowid");
         } 
         else return null;
        }

       
        /**
         * @return returns Id value
         */
        public Integer getRowidValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("rowid").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Id value
         * @param value Id value
         */
        public void setRowidValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("rowid").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns  widget
         */
        public Field getItemidlink(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("itemidlink");
         } 
         else return null;
        }

       
        /**
         * @return returns  value
         */
        public Integer getItemidlinkValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("itemidlink").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set  value
         * @param value  value
         */
        public void setItemidlinkValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("itemidlink").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns  widget
         */
        public Field getIditemlink(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("iditemlink");
         } 
         else return null;
        }

       
        /**
         * @return returns  value
         */
        public Integer getIditemlinkValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("iditemlink").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set  value
         * @param value  value
         */
        public void setIditemlinkValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("iditemlink").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Didascalia widget
         */
        public Field getItemlinkdesk(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("itemlinkdesk");
         } 
         else return null;
        }

       
        /**
         * @return returns Didascalia value
         */
        public String getItemlinkdeskValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("itemlinkdesk").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Didascalia value
         * @param value Didascalia value
         */
        public void setItemlinkdeskValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("itemlinkdesk").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns URL link widget
         */
        public Field getItemlink(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("itemlink");
         } 
         else return null;
        }

       
        /**
         * @return returns URL link value
         */
        public String getItemlinkValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("itemlink").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set URL link value
         * @param value URL link value
         */
        public void setItemlinkValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("itemlink").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns CANCELLA widget
         */
        public Action getItemlinkdelete(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Action)r.getChild("itemlinkdelete");
         } 
         else return null;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns  widget
         */
        public RowAction getUp(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (RowAction)r.getChild("up");
         } 
         else return null;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns  widget
         */
        public RowAction getDown(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (RowAction)r.getChild("down");
         } 
         else return null;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
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
			
		
		
				
		
		public Repeater getRepeater() {
		   return base;
		}
	 }	
	
	 public class ImagesRepeaterWrapper {
		
		private Repeater base;
			
		private Output rowid;	
		private BooleanField select;	
		private Field apcId;	
		private Output apcPageId;	
		private Output apcComponentId;	
		private Output apcAssocDate;	
		private Field apcAlt;	
		private Output wrMimetype;	
		private Output uriResource;	
		private Field apcType;	
		private Field filterApcType;	
		private Field apcDescription;	
		private Output imageName;	
		private Action deleteitem;	
		
				
		public ImagesRepeaterWrapper(Repeater base) {
		  // mette il repeater in locale
		  this.base = base;
		}
		
		
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
         * @return returns apc_id widget
         */
        public Field getApcId(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("apcId");
         } 
         else return null;
        }

       
        /**
         * @return returns apc_id value
         */
        public Integer getApcIdValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("apcId").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set apc_id value
         * @param value apc_id value
         */
        public void setApcIdValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("apcId").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns apc_page_id widget
         */
        public Output getApcPageId(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("apcPageId");
         } 
         else return null;
        }

       
        /**
         * @return returns apc_page_id value
         */
        public Integer getApcPageIdValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("apcPageId").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set apc_page_id value
         * @param value apc_page_id value
         */
        public void setApcPageIdValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("apcPageId").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns apc_component_id widget
         */
        public Output getApcComponentId(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("apcComponentId");
         } 
         else return null;
        }

       
        /**
         * @return returns apc_component_id value
         */
        public Integer getApcComponentIdValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("apcComponentId").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set apc_component_id value
         * @param value apc_component_id value
         */
        public void setApcComponentIdValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("apcComponentId").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Data widget
         */
        public Output getApcAssocDate(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("apcAssocDate");
         } 
         else return null;
        }

       
        /**
         * @return returns Data value
         */
        public Date getApcAssocDateValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Date)r.getChild("apcAssocDate").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Data value
         * @param value Data value
         */
        public void setApcAssocDateValue(Date value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("apcAssocDate").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Titolo widget
         */
        public Field getApcAlt(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("apcAlt");
         } 
         else return null;
        }

       
        /**
         * @return returns Titolo value
         */
        public String getApcAltValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("apcAlt").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Titolo value
         * @param value Titolo value
         */
        public void setApcAltValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("apcAlt").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns wr_mimetype widget
         */
        public Output getwrMimetype(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("wrMimetype");
         } 
         else return null;
        }

       
        /**
         * @return returns wr_mimetype value
         */
        public String getwrMimetypeValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("wrMimetype").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set wr_mimetype value
         * @param value wr_mimetype value
         */
        public void setwrMimetypeValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wrMimetype").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Uri risorsa widget
         */
        public Output getUriResource(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("uriResource");
         } 
         else return null;
        }

       
        /**
         * @return returns Uri risorsa value
         */
        public String getUriResourceValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("uriResource").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Uri risorsa value
         * @param value Uri risorsa value
         */
        public void setUriResourceValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("uriResource").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Tipo widget
         */
        public Field getApcType(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("apcType");
         } 
         else return null;
        }

       
        /**
         * @return returns Tipo value
         */
        public Integer getApcTypeValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("apcType").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Tipo value
         * @param value Tipo value
         */
        public void setApcTypeValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("apcType").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Tipo widget
         */
        public Field getFilterApcType(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("filterApcType");
         } 
         else return null;
        }

       
        /**
         * @return returns Tipo value
         */
        public Integer getFilterApcTypeValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("filterApcType").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Tipo value
         * @param value Tipo value
         */
        public void setFilterApcTypeValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("filterApcType").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Descrizione widget
         */
        public Field getApcDescription(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("apcDescription");
         } 
         else return null;
        }

       
        /**
         * @return returns Descrizione value
         */
        public String getApcDescriptionValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("apcDescription").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Descrizione value
         * @param value Descrizione value
         */
        public void setApcDescriptionValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("apcDescription").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns File widget
         */
        public Output getImageName(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("imageName");
         } 
         else return null;
        }

       
        /**
         * @return returns File value
         */
        public String getImageNameValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("imageName").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set File value
         * @param value File value
         */
        public void setImageNameValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("imageName").setValue(value);
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
	
	 public class ImageswebcomponentsRepeaterWrapper {
		
		private Repeater base;
			
		private BooleanField select;	
		private Field wcId;	
		private Output rowid;	
		private Output wrName;	
		private Field wrDescription;	
		private Output wrMimetype;	
		private Field wrAlt;	
		private Field wcType;	
		private ImageMap previewImage;	
		private Field uriPreviewImage;	
		private Output wcInsertDate;	
		private PagesassociationsRepeaterWrapper pagesassociations;	
		
				
		public ImageswebcomponentsRepeaterWrapper(Repeater base) {
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
            return (Field)r.getChild("wcId");
         } 
         else return null;
        }

       
        /**
         * @return returns wc_id value
         */
        public Integer getwcIdValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("wcId").getValue();
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
            r.getChild("wcId").setValue(value);
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
         * @return returns File widget
         */
        public Output getwrName(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("wrName");
         } 
         else return null;
        }

       
        /**
         * @return returns File value
         */
        public String getwrNameValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("wrName").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set File value
         * @param value File value
         */
        public void setwrNameValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wrName").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Descrizione widget
         */
        public Field getwrDescription(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("wrDescription");
         } 
         else return null;
        }

       
        /**
         * @return returns Descrizione value
         */
        public String getwrDescriptionValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("wrDescription").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Descrizione value
         * @param value Descrizione value
         */
        public void setwrDescriptionValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wrDescription").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Tipo widget
         */
        public Output getwrMimetype(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("wrMimetype");
         } 
         else return null;
        }

       
        /**
         * @return returns Tipo value
         */
        public String getwrMimetypeValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("wrMimetype").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Tipo value
         * @param value Tipo value
         */
        public void setwrMimetypeValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wrMimetype").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Titolo widget
         */
        public Field getwrAlt(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("wrAlt");
         } 
         else return null;
        }

       
        /**
         * @return returns Titolo value
         */
        public String getwrAltValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("wrAlt").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Titolo value
         * @param value Titolo value
         */
        public void setwrAltValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wrAlt").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns tipo della risorsa widget
         */
        public Field getwcType(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("wcType");
         } 
         else return null;
        }

       
        /**
         * @return returns tipo della risorsa value
         */
        public Integer getwcTypeValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("wcType").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set tipo della risorsa value
         * @param value tipo della risorsa value
         */
        public void setwcTypeValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wcType").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Anteprima widget
         */
        public ImageMap getPreviewImage(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (ImageMap)r.getChild("previewImage");
         } 
         else return null;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Link URI widget
         */
        public Field getUriPreviewImage(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("uriPreviewImage");
         } 
         else return null;
        }

       
        /**
         * @return returns Link URI value
         */
        public String getUriPreviewImageValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("uriPreviewImage").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Link URI value
         * @param value Link URI value
         */
        public void setUriPreviewImageValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("uriPreviewImage").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Data widget
         */
        public Output getwcInsertDate(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("wcInsertDate");
         } 
         else return null;
        }

       
        /**
         * @return returns Data value
         */
        public Date getwcInsertDateValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Date)r.getChild("wcInsertDate").getValue();
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
            r.getChild("wcInsertDate").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns  widget
         */
        public PagesassociationsRepeaterWrapper getPagesassociations(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (PagesassociationsRepeaterWrapper)r.getChild("pagesassociations");
         } 
         else return null;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
			
		
		
				
		
		public Repeater getRepeater() {
		   return base;
		}
	 }	
	
	 public class PagesassociationsRepeaterWrapper {
		
		private Repeater base;
			
		private BooleanField select;	
		private Output apcId;	
		private Output rowid;	
		private Output wpName;	
		private Output wpId;	
		private Output wpPagetype;	
		
				
		public PagesassociationsRepeaterWrapper(Repeater base) {
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
         * @return returns apc_id widget
         */
        public Output getApcId(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("apcId");
         } 
         else return null;
        }

       
        /**
         * @return returns apc_id value
         */
        public Integer getApcIdValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("apcId").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set apc_id value
         * @param value apc_id value
         */
        public void setApcIdValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("apcId").setValue(value);
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
         * @return returns Nome widget
         */
        public Output getwpName(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("wpName");
         } 
         else return null;
        }

       
        /**
         * @return returns Nome value
         */
        public String getwpNameValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("wpName").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Nome value
         * @param value Nome value
         */
        public void setwpNameValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wpName").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns PageID widget
         */
        public Output getwpId(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("wpId");
         } 
         else return null;
        }

       
        /**
         * @return returns PageID value
         */
        public Integer getwpIdValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("wpId").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set PageID value
         * @param value PageID value
         */
        public void setwpIdValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wpId").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Tipo widget
         */
        public Output getwpPagetype(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("wpPagetype");
         } 
         else return null;
        }

       
        /**
         * @return returns Tipo value
         */
        public String getwpPagetypeValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("wpPagetype").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Tipo value
         * @param value Tipo value
         */
        public void setwpPagetypeValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wpPagetype").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
			
		
		
				
		
		public Repeater getRepeater() {
		   return base;
		}
	 }	
	
	 public class ItemtagsRepeaterWrapper {
		
		private Repeater base;
			
		private BooleanField select;	
		private BooleanField tagSelected;	
		private Output tagTitle;	
		
				
		public ItemtagsRepeaterWrapper(Repeater base) {
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
         * @return returns  widget
         */
        public BooleanField getTagSelected(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (BooleanField)r.getChild("tagSelected");
         } 
         else return null;
        }

       
        /**
         * @return returns  value
         */
        public Boolean getTagSelectedValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Boolean)r.getChild("tagSelected").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set  value
         * @param value  value
         */
        public void setTagSelectedValue(Boolean value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("tagSelected").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Nome del tag widget
         */
        public Output getTagTitle(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("tagTitle");
         } 
         else return null;
        }

       
        /**
         * @return returns Nome del tag value
         */
        public String getTagTitleValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("tagTitle").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Nome del tag value
         * @param value Nome del tag value
         */
        public void setTagTitleValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("tagTitle").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
			
		
		
				
		
		public Repeater getRepeater() {
		   return base;
		}
	 }	
	
	 public class PhotogallerywebcomponentsRepeaterWrapper {
		
		private Repeater base;
			
		private BooleanField select;	
		private Field wcId;	
		private Output rowid;	
		private Output wrName;	
		private Field wrDescription;	
		private Output wrMimetype;	
		private Field wrAlt;	
		private Field wcType;	
		private ImageMap previewImage;	
		private Field uriPreviewImage;	
		private Output wcInsertDate;	
		private Pagesassociations2RepeaterWrapper pagesassociations2;	
		
				
		public PhotogallerywebcomponentsRepeaterWrapper(Repeater base) {
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
            return (Field)r.getChild("wcId");
         } 
         else return null;
        }

       
        /**
         * @return returns wc_id value
         */
        public Integer getwcIdValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("wcId").getValue();
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
            r.getChild("wcId").setValue(value);
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
         * @return returns File widget
         */
        public Output getwrName(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("wrName");
         } 
         else return null;
        }

       
        /**
         * @return returns File value
         */
        public String getwrNameValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("wrName").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set File value
         * @param value File value
         */
        public void setwrNameValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wrName").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Descrizione widget
         */
        public Field getwrDescription(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("wrDescription");
         } 
         else return null;
        }

       
        /**
         * @return returns Descrizione value
         */
        public String getwrDescriptionValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("wrDescription").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Descrizione value
         * @param value Descrizione value
         */
        public void setwrDescriptionValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wrDescription").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Tipo widget
         */
        public Output getwrMimetype(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("wrMimetype");
         } 
         else return null;
        }

       
        /**
         * @return returns Tipo value
         */
        public String getwrMimetypeValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("wrMimetype").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Tipo value
         * @param value Tipo value
         */
        public void setwrMimetypeValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wrMimetype").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Titolo widget
         */
        public Field getwrAlt(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("wrAlt");
         } 
         else return null;
        }

       
        /**
         * @return returns Titolo value
         */
        public String getwrAltValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("wrAlt").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Titolo value
         * @param value Titolo value
         */
        public void setwrAltValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wrAlt").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns tipo della risorsa widget
         */
        public Field getwcType(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("wcType");
         } 
         else return null;
        }

       
        /**
         * @return returns tipo della risorsa value
         */
        public Integer getwcTypeValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("wcType").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set tipo della risorsa value
         * @param value tipo della risorsa value
         */
        public void setwcTypeValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wcType").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Anteprima widget
         */
        public ImageMap getPreviewImage(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (ImageMap)r.getChild("previewImage");
         } 
         else return null;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Link URI widget
         */
        public Field getUriPreviewImage(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("uriPreviewImage");
         } 
         else return null;
        }

       
        /**
         * @return returns Link URI value
         */
        public String getUriPreviewImageValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("uriPreviewImage").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Link URI value
         * @param value Link URI value
         */
        public void setUriPreviewImageValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("uriPreviewImage").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Data widget
         */
        public Output getwcInsertDate(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("wcInsertDate");
         } 
         else return null;
        }

       
        /**
         * @return returns Data value
         */
        public Date getwcInsertDateValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Date)r.getChild("wcInsertDate").getValue();
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
            r.getChild("wcInsertDate").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns  widget
         */
        public Pagesassociations2RepeaterWrapper getPagesassociations2(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Pagesassociations2RepeaterWrapper)r.getChild("pagesassociations2");
         } 
         else return null;
        }

       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
			
		
		
				
		
		public Repeater getRepeater() {
		   return base;
		}
	 }	
	
	 public class Pagesassociations2RepeaterWrapper {
		
		private Repeater base;
			
		private BooleanField select;	
		private Output apcId;	
		private Output rowid;	
		private Output wpName;	
		private Output wpId;	
		private Output wpPagetype;	
		
				
		public Pagesassociations2RepeaterWrapper(Repeater base) {
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
         * @return returns apc_id widget
         */
        public Output getApcId(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("apcId");
         } 
         else return null;
        }

       
        /**
         * @return returns apc_id value
         */
        public Integer getApcIdValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("apcId").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set apc_id value
         * @param value apc_id value
         */
        public void setApcIdValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("apcId").setValue(value);
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
         * @return returns Nome widget
         */
        public Output getwpName(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("wpName");
         } 
         else return null;
        }

       
        /**
         * @return returns Nome value
         */
        public String getwpNameValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("wpName").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Nome value
         * @param value Nome value
         */
        public void setwpNameValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wpName").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns PageID widget
         */
        public Output getwpId(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("wpId");
         } 
         else return null;
        }

       
        /**
         * @return returns PageID value
         */
        public Integer getwpIdValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("wpId").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set PageID value
         * @param value PageID value
         */
        public void setwpIdValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wpId").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Tipo widget
         */
        public Output getwpPagetype(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("wpPagetype");
         } 
         else return null;
        }

       
        /**
         * @return returns Tipo value
         */
        public String getwpPagetypeValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("wpPagetype").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Tipo value
         * @param value Tipo value
         */
        public void setwpPagetypeValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wpPagetype").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
			
		
		
				
		
		public Repeater getRepeater() {
		   return base;
		}
	 }	
	
	 public class PhotogalleryRepeaterWrapper {
		
		private Repeater base;
			
		private Output rowid;	
		private BooleanField select;	
		private Field apcId;	
		private Output apcPageId;	
		private Output apcComponentId;	
		private Output apcAssocDate;	
		private Field apcAlt;	
		private Output wrMimetype;	
		private Output uriResource;	
		private Field apcType;	
		private Field filterApcType;	
		private Field apcDescription;	
		private Output imageName;	
		private Action deleteitem;	
		
				
		public PhotogalleryRepeaterWrapper(Repeater base) {
		  // mette il repeater in locale
		  this.base = base;
		}
		
		
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
         * @return returns apc_id widget
         */
        public Field getApcId(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("apcId");
         } 
         else return null;
        }

       
        /**
         * @return returns apc_id value
         */
        public Integer getApcIdValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("apcId").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set apc_id value
         * @param value apc_id value
         */
        public void setApcIdValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("apcId").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns apc_page_id widget
         */
        public Output getApcPageId(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("apcPageId");
         } 
         else return null;
        }

       
        /**
         * @return returns apc_page_id value
         */
        public Integer getApcPageIdValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("apcPageId").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set apc_page_id value
         * @param value apc_page_id value
         */
        public void setApcPageIdValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("apcPageId").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns apc_component_id widget
         */
        public Output getApcComponentId(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("apcComponentId");
         } 
         else return null;
        }

       
        /**
         * @return returns apc_component_id value
         */
        public Integer getApcComponentIdValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("apcComponentId").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set apc_component_id value
         * @param value apc_component_id value
         */
        public void setApcComponentIdValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("apcComponentId").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Data widget
         */
        public Output getApcAssocDate(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("apcAssocDate");
         } 
         else return null;
        }

       
        /**
         * @return returns Data value
         */
        public Date getApcAssocDateValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Date)r.getChild("apcAssocDate").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Data value
         * @param value Data value
         */
        public void setApcAssocDateValue(Date value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("apcAssocDate").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Titolo widget
         */
        public Field getApcAlt(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("apcAlt");
         } 
         else return null;
        }

       
        /**
         * @return returns Titolo value
         */
        public String getApcAltValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("apcAlt").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Titolo value
         * @param value Titolo value
         */
        public void setApcAltValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("apcAlt").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns wr_mimetype widget
         */
        public Output getwrMimetype(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("wrMimetype");
         } 
         else return null;
        }

       
        /**
         * @return returns wr_mimetype value
         */
        public String getwrMimetypeValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("wrMimetype").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set wr_mimetype value
         * @param value wr_mimetype value
         */
        public void setwrMimetypeValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("wrMimetype").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Uri risorsa widget
         */
        public Output getUriResource(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("uriResource");
         } 
         else return null;
        }

       
        /**
         * @return returns Uri risorsa value
         */
        public String getUriResourceValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("uriResource").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Uri risorsa value
         * @param value Uri risorsa value
         */
        public void setUriResourceValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("uriResource").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Tipo widget
         */
        public Field getApcType(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("apcType");
         } 
         else return null;
        }

       
        /**
         * @return returns Tipo value
         */
        public Integer getApcTypeValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("apcType").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Tipo value
         * @param value Tipo value
         */
        public void setApcTypeValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("apcType").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Tipo widget
         */
        public Field getFilterApcType(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("filterApcType");
         } 
         else return null;
        }

       
        /**
         * @return returns Tipo value
         */
        public Integer getFilterApcTypeValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Integer)r.getChild("filterApcType").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Tipo value
         * @param value Tipo value
         */
        public void setFilterApcTypeValue(Integer value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("filterApcType").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns Descrizione widget
         */
        public Field getApcDescription(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Field)r.getChild("apcDescription");
         } 
         else return null;
        }

       
        /**
         * @return returns Descrizione value
         */
        public String getApcDescriptionValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("apcDescription").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set Descrizione value
         * @param value Descrizione value
         */
        public void setApcDescriptionValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("apcDescription").setValue(value);
         } 
        }	
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////         
		
        /**
         * @return returns File widget
         */
        public Output getImageName(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (Output)r.getChild("imageName");
         } 
         else return null;
        }

       
        /**
         * @return returns File value
         */
        public String getImageNameValue(int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            return (String)r.getChild("imageName").getValue();
         } 
         else return null;
        }		
        
        /**
         * Set File value
         * @param value File value
         */
        public void setImageNameValue(String value, int row) {
         if (row < base.getSize()) {
            RepeaterRow r = base.getRow(row);
            r.getChild("imageName").setValue(value);
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

	public CategoriesRepeaterWrapper getCategories() {
		return categories;
	}


	public void setCategories(CategoriesRepeaterWrapper categories) {
		this.categories = categories;
	}


	public PagesassociationsRepeaterWrapper getPagesassociations() {
		return pagesassociations;
	}


	public void setPagesassociations(PagesassociationsRepeaterWrapper pagesassociations) {
		this.pagesassociations = pagesassociations;
	}


	public Pagesassociations2RepeaterWrapper getPagesassociations2() {
		return pagesassociations2;
	}


	public void setPagesassociations2(Pagesassociations2RepeaterWrapper pagesassociations2) {
		this.pagesassociations2 = pagesassociations2;
	}	
	

   }

