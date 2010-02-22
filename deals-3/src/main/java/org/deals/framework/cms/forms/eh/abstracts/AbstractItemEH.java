
package org.deals.framework.cms.forms.eh.abstracts;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.avalon.framework.service.ServiceManager;
import org.apache.cocoon.components.flow.apples.AppleResponse;
import org.apache.cocoon.environment.Request;
import org.apache.cocoon.forms.binding.Binding;
import org.apache.cocoon.forms.event.AbstractFormHandler;
import org.apache.cocoon.forms.event.ActionEvent;
import org.apache.cocoon.forms.event.ValueChangedEvent;
import org.apache.cocoon.forms.formmodel.Action;
import org.apache.cocoon.forms.formmodel.BooleanField;
import org.apache.cocoon.forms.formmodel.Field;
import org.apache.cocoon.forms.formmodel.Form;
import org.apache.cocoon.forms.formmodel.RepeaterAction;
import org.apache.cocoon.forms.formmodel.Submit;
import org.apache.cocoon.forms.formmodel.Upload;
import org.apache.cocoon.forms.formmodel.Widget;
import org.apache.cocoon.forms.formmodel.WidgetState;
import org.apache.cocoon.forms.formmodel.Repeater.RepeaterRow;
import org.deals.framework.cms.forms.wrapper.ItemFW;



 public abstract class AbstractItemEH extends AbstractFormHandler { 

	protected ServiceManager service = null;
	protected HttpSession session = null;
    protected Request request = null;
    protected AppleResponse response = null;

	protected ItemFW fw = null; 
    
		
	/**
	 * InitForm con il solo form
	 * @param f form da gestire
	 */
	//public void initForm(Form f) {
	//	initForm(f, null, null, null, null);
	//}
	
	/**
	 * InitForm con il form, il serviceManager, la request e la response
	 * @param f form da gestire
	 * @param service serviceManager, fa le veci dell'oggetto Cocoon
	 * @param request oggetto request
	 * @param response oggetto response
	 */
	//public void initForm(Form f, ServiceManager service, Request request, AppleResponse response) {
	//	initForm(f, service, request, response, null);
	//}
		
		
    /**
     *  Chiama tutte le onCreate del form e inizializza gli oggetti cocoon
     */
	public ItemFW initForm(Form f, Request request, Binding binding) {

			this.request = request;
			session = request.getSession();
			fw = new ItemFW(f);
			
			if (binding != null) {
			   fw.setBinding(binding);
			}

			formOnCreate(fw);
			tab_stateOnCreate(fw);
			check_change_authorOnCreate(fw);
			item_idOnCreate(fw);
			item_authorOnCreate(fw);
			item_creatorOnCreate(fw);
			item_templateOnCreate(fw);
			item_dateOnCreate(fw);
			item_expirationOnCreate(fw);
			item_noexpirationOnCreate(fw);
			item_titleOnCreate(fw);
			item_shortOnCreate(fw);
			item_bodyOnCreate(fw);
			item_stateOnCreate(fw);
			upload_imageOnCreate(fw);
			itemimageOnCreate(fw);
			item_labelimageOnCreate(fw);
			upload_thumbnailOnCreate(fw);
			itemthumbnailOnCreate(fw);
			item_labelthumbnailOnCreate(fw);
			item_printOnCreate(fw);
			item_sendOnCreate(fw);
			item_comments_onOnCreate(fw);
			item_meta_titleOnCreate(fw);
			item_meta_descriptionOnCreate(fw);
			item_meta_keywordsOnCreate(fw);
			itemsubmitOnCreate(fw);
			itemdeleteOnCreate(fw);
			itemnodeleteOnCreate(fw);
			sectionsOnCreate(fw);
			additemfileOnCreate(fw);
			itemfilesOnCreate(fw);
			itemlinkOnCreate(fw);
			itemlinkdescOnCreate(fw);
			additemlinkOnCreate(fw);
			itemlinksOnCreate(fw);
			addlinkOnCreate(fw);
			removelinkOnCreate(fw);
			addnewimagesOnCreate(fw);
			removeimagesOnCreate(fw);
			imagesOnCreate(fw);
			imagessortbydateOnCreate(fw);
			imagessortnaturalOnCreate(fw);
			filter_by_apc_typeOnCreate(fw);
			filter_by_mime_typeOnCreate(fw);
			apcfilterbuttonOnCreate(fw);
			imageswebcomponentsOnCreate(fw);
			wcsortbydateOnCreate(fw);
			wcsortnaturalOnCreate(fw);
			wcfilterbuttonOnCreate(fw);
			pagefirstOnCreate(fw);
			pageprevOnCreate(fw);
			pagenextOnCreate(fw);
			pagelastOnCreate(fw);
			topageOnCreate(fw);
			pagecustomOnCreate(fw);
			itemtagsOnCreate(fw);
			photogallerywebcomponentsOnCreate(fw);
			photogalleryOnCreate(fw);
			return fw;
	}
       
	public void handleActionEvent(ActionEvent arg0) {
            if (arg0.getSourceWidget().getParent().equals(fw.getForm())) {
               // tutte le chiamate pertinenti a widget che stanno nel Form
			   
				  if (arg0.getSourceWidget().equals(fw.getItemsubmit())) {
					   itemsubmitOnAction(fw, (Submit)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemdelete())) {
					   itemdeleteOnAction(fw, (Submit)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemnodelete())) {
					   itemnodeleteOnAction(fw, (Submit)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getAdditemfile())) {
					   additemfileOnAction(fw, (Action)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getAdditemlink())) {
					   additemlinkOnAction(fw, (Action)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getAddlink())) {
					   addlinkOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getRemovelink())) {
					   removelinkOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getAddnewimages())) {
					   addnewimagesOnAction(fw, (Action)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getRemoveimages())) {
					   removeimagesOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getImagessortbydate())) {
					   imagessortbydateOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getImagessortnatural())) {
					   imagessortnaturalOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getApcfilterbutton())) {
					   apcfilterbuttonOnAction(fw, (Action)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getwcsortbydate())) {
					   wcsortbydateOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getwcsortnatural())) {
					   wcsortnaturalOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getwcfilterbutton())) {
					   wcfilterbuttonOnAction(fw, (Action)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getPagefirst())) {
					   pagefirstOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getPageprev())) {
					   pageprevOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getPagenext())) {
					   pagenextOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getPagelast())) {
					   pagelastOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getPagecustom())) {
					   pagecustomOnAction(fw, (RepeaterAction)arg0.getSourceWidget());          
					   return;
				 	}
				      
               System.err.println("!!! Attenzione - ItemEH non ho intercettato il widget che ha generato l'evento Action " + arg0.toString());
               return; // non ho intercettato il widget che ha generato l'evento
            }	
            
            // lista di if per controllare se il parent ?? magari un repeater, o meglio una repeater row
            //if (arg0.getSourceWidget().getParent().getParent().equals(fw.getRepeater()) {
               // vecchia intercettazione tramite ID
            //} 

		
            if (arg0.getSourceWidget().getFullName().indexOf("sections")>-1) {
			   
			   
            } 
        
            if (arg0.getSourceWidget().getFullName().indexOf("categories")>-1) {
			   
			   
            } 
        
            if (arg0.getSourceWidget().getFullName().indexOf("itemfiles")>-1) {
			   
	            if (arg0.getSourceWidget().getDefinition().equals(fw.getItemfiles().getItemfiledelete(0).getDefinition())) {            	
					// chiamata dell'evento action
                    itemfiles_itemfiledeleteOnAction(fw, (Action)arg0.getSourceWidget(), Integer.parseInt(((RepeaterRow)arg0.getSourceWidget().getParent()).getId()));					
	            }
			   
			   
            } 
        
            if (arg0.getSourceWidget().getFullName().indexOf("itemlinks")>-1) {
			   
	            if (arg0.getSourceWidget().getDefinition().equals(fw.getItemlinks().getItemlinkdelete(0).getDefinition())) {            	
					// chiamata dell'evento action
                    itemlinks_itemlinkdeleteOnAction(fw, (Action)arg0.getSourceWidget(), Integer.parseInt(((RepeaterRow)arg0.getSourceWidget().getParent()).getId()));					
	            }
			   
			   
            } 
        
            if (arg0.getSourceWidget().getFullName().indexOf("images")>-1) {
			   
	            if (arg0.getSourceWidget().getDefinition().equals(fw.getImages().getDeleteitem(0).getDefinition())) {            	
					// chiamata dell'evento action
                    images_deleteitemOnAction(fw, (Action)arg0.getSourceWidget(), Integer.parseInt(((RepeaterRow)arg0.getSourceWidget().getParent()).getId()));					
	            }
			   
			   
            } 
        
            if (arg0.getSourceWidget().getFullName().indexOf("imageswebcomponents")>-1) {
			   
			   
            } 
        
            if (arg0.getSourceWidget().getFullName().indexOf("pagesassociations")>-1) {
			   
			   
            } 
        
            if (arg0.getSourceWidget().getFullName().indexOf("itemtags")>-1) {
			   
			   
            } 
        
            if (arg0.getSourceWidget().getFullName().indexOf("photogallerywebcomponents")>-1) {
			   
			   
            } 
        
            if (arg0.getSourceWidget().getFullName().indexOf("pagesassociations2")>-1) {
			   
			   
            } 
        
            if (arg0.getSourceWidget().getFullName().indexOf("photogallery")>-1) {
			   
	            if (arg0.getSourceWidget().getDefinition().equals(fw.getPhotogallery().getDeleteitem(0).getDefinition())) {            	
					// chiamata dell'evento action
                    photogallery_deleteitemOnAction(fw, (Action)arg0.getSourceWidget(), Integer.parseInt(((RepeaterRow)arg0.getSourceWidget().getParent()).getId()));					
	            }
			   
			   
            } 
        		
        
	        
	        
	}

	public void handleValueChangedEvent(ValueChangedEvent arg0) {
	        
            if (arg0.getSourceWidget().getParent().equals(fw.getForm())) {
               // tutte le chiamate pertinenti a widget che stanno nel Form
				 
				  if (arg0.getSourceWidget().equals(fw.getTabState())) {
					   tab_stateOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getCheckChangeAuthor())) {
					   check_change_authorOnValueChanged(fw, (BooleanField)arg0.getSourceWidget(), (Boolean)arg0.getNewValue(), (Boolean)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemId())) {
					   item_idOnValueChanged(fw, (Field)arg0.getSourceWidget(), (Integer)arg0.getNewValue(), (Integer)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemAuthor())) {
					   item_authorOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemCreator())) {
					   item_creatorOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemTemplate())) {
					   item_templateOnValueChanged(fw, (Field)arg0.getSourceWidget(), (Integer)arg0.getNewValue(), (Integer)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemDate())) {
					   item_dateOnValueChanged(fw, (Field)arg0.getSourceWidget(), (Date)arg0.getNewValue(), (Date)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemExpiration())) {
					   item_expirationOnValueChanged(fw, (Field)arg0.getSourceWidget(), (Date)arg0.getNewValue(), (Date)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemNoexpiration())) {
					   item_noexpirationOnValueChanged(fw, (BooleanField)arg0.getSourceWidget(), (Boolean)arg0.getNewValue(), (Boolean)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemTitle())) {
					   item_titleOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemShort())) {
					   item_shortOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemBody())) {
					   item_bodyOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemState())) {
					   item_stateOnValueChanged(fw, (Field)arg0.getSourceWidget(), (Integer)arg0.getNewValue(), (Integer)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getUploadImage())) {
					   upload_imageOnValueChanged(fw, (Upload)arg0.getSourceWidget(), (Object)arg0.getNewValue(), (Object)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemLabelimage())) {
					   item_labelimageOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getUploadThumbnail())) {
					   upload_thumbnailOnValueChanged(fw, (Upload)arg0.getSourceWidget(), (Object)arg0.getNewValue(), (Object)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemLabelthumbnail())) {
					   item_labelthumbnailOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemPrint())) {
					   item_printOnValueChanged(fw, (BooleanField)arg0.getSourceWidget(), (Boolean)arg0.getNewValue(), (Boolean)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemSend())) {
					   item_sendOnValueChanged(fw, (BooleanField)arg0.getSourceWidget(), (Boolean)arg0.getNewValue(), (Boolean)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemCommentsOn())) {
					   item_comments_onOnValueChanged(fw, (BooleanField)arg0.getSourceWidget(), (Boolean)arg0.getNewValue(), (Boolean)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemMetaTitle())) {
					   item_meta_titleOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemMetaDescription())) {
					   item_meta_descriptionOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemMetakeywords())) {
					   item_meta_keywordsOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemlink())) {
					   itemlinkOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getItemlinkdesc())) {
					   itemlinkdescOnValueChanged(fw, (Field)arg0.getSourceWidget(), (String)arg0.getNewValue(), (String)arg0.getOldValue());          
					   return;
				 	}
				 
				  if (arg0.getSourceWidget().equals(fw.getTopage())) {
					   topageOnValueChanged(fw, (Field)arg0.getSourceWidget(), (Integer)arg0.getNewValue(), (Integer)arg0.getOldValue());          
					   return;
				 	}
				      
               System.err.println("!!! Attenzione - ItemEH non ho intercettato il widget che ha generato l'evento ValueChanged " + arg0.toString());
               return; // non ho intercettato il widget che ha generato l'evento
            }	
		
					
            
            // lista di if per controllare se il parent ?? magari un repeater, o meglio una repeater row
            //if (arg0.getSourceWidget().getParent().getParent().equals(fw.getRepeater()) {
               // vecchia intercettazione tramite ID
            //} 
	}



//////###################################################################  ///////
//////###########       ON CREATE HANDLERS          ###############  ///////
//////###################################################################  ///////

	public void formOnCreate(ItemFW f) {
	}

	
	
	public void tab_stateOnCreate(ItemFW f) {
	}			
	
	
	public void check_change_authorOnCreate(ItemFW f) {
	}			
	
	
	public void item_idOnCreate(ItemFW f) {
	}			
	
	
	public void item_authorOnCreate(ItemFW f) {
	}			
	
	
	public void item_creatorOnCreate(ItemFW f) {
	}			
	
	
	public void item_templateOnCreate(ItemFW f) {
	}			
	
	
	public void item_dateOnCreate(ItemFW f) {
	}			
	
	
	public void item_expirationOnCreate(ItemFW f) {
	}			
	
	
	public void item_noexpirationOnCreate(ItemFW f) {
	}			
	
	
	public void item_titleOnCreate(ItemFW f) {
	}			
	
	
	public void item_shortOnCreate(ItemFW f) {
	}			
	
	
	public void item_bodyOnCreate(ItemFW f) {
	}			
	
	
	public void item_stateOnCreate(ItemFW f) {
	}			
	
	
	public void upload_imageOnCreate(ItemFW f) {
	}			
	
	
	public void itemimageOnCreate(ItemFW f) {
	}			
	
	
	public void item_labelimageOnCreate(ItemFW f) {
	}			
	
	
	public void upload_thumbnailOnCreate(ItemFW f) {
	}			
	
	
	public void itemthumbnailOnCreate(ItemFW f) {
	}			
	
	
	public void item_labelthumbnailOnCreate(ItemFW f) {
	}			
	
	
	public void item_printOnCreate(ItemFW f) {
	}			
	
	
	public void item_sendOnCreate(ItemFW f) {
	}			
	
	
	public void item_comments_onOnCreate(ItemFW f) {
	}			
	
	
	public void item_meta_titleOnCreate(ItemFW f) {
	}			
	
	
	public void item_meta_descriptionOnCreate(ItemFW f) {
	}			
	
	
	public void item_meta_keywordsOnCreate(ItemFW f) {
	}			
	
	
	public void itemsubmitOnCreate(ItemFW f) {
	}			
	
	
	public void itemdeleteOnCreate(ItemFW f) {
	}			
	
	
	public void itemnodeleteOnCreate(ItemFW f) {
	}			
	
	
	public void sectionsOnCreate(ItemFW f) {
	}			
	
	
	public void additemfileOnCreate(ItemFW f) {
	}			
	
	
	public void itemfilesOnCreate(ItemFW f) {
	}			
	
	
	public void itemlinkOnCreate(ItemFW f) {
	}			
	
	
	public void itemlinkdescOnCreate(ItemFW f) {
	}			
	
	
	public void additemlinkOnCreate(ItemFW f) {
	}			
	
	
	public void itemlinksOnCreate(ItemFW f) {
	}			
	
	
	public void addlinkOnCreate(ItemFW f) {
	}			
	
	
	public void removelinkOnCreate(ItemFW f) {
	}			
	
	
	public void addnewimagesOnCreate(ItemFW f) {
	}			
	
	
	public void removeimagesOnCreate(ItemFW f) {
	}			
	
	
	public void imagesOnCreate(ItemFW f) {
	}			
	
	
	public void imagessortbydateOnCreate(ItemFW f) {
	}			
	
	
	public void imagessortnaturalOnCreate(ItemFW f) {
	}			
	
	
	public void filter_by_apc_typeOnCreate(ItemFW f) {
	}			
	
	
	public void filter_by_mime_typeOnCreate(ItemFW f) {
	}			
	
	
	public void apcfilterbuttonOnCreate(ItemFW f) {
	}			
	
	
	public void imageswebcomponentsOnCreate(ItemFW f) {
	}			
	
	
	public void wcsortbydateOnCreate(ItemFW f) {
	}			
	
	
	public void wcsortnaturalOnCreate(ItemFW f) {
	}			
	
	
	public void wcfilterbuttonOnCreate(ItemFW f) {
	}			
	
	
	public void pagefirstOnCreate(ItemFW f) {
	}			
	
	
	public void pageprevOnCreate(ItemFW f) {
	}			
	
	
	public void pagenextOnCreate(ItemFW f) {
	}			
	
	
	public void pagelastOnCreate(ItemFW f) {
	}			
	
	
	public void topageOnCreate(ItemFW f) {
	}			
	
	
	public void pagecustomOnCreate(ItemFW f) {
	}			
	
	
	public void itemtagsOnCreate(ItemFW f) {
	}			
	
	
	public void photogallerywebcomponentsOnCreate(ItemFW f) {
	}			
	
	
	public void photogalleryOnCreate(ItemFW f) {
	}			
	


//////###################################################################  ///////
//////###########       ON ACTION EVENT HANDLERS          ###############  ///////
//////###################################################################  ///////

	
	public void itemsubmitOnAction(ItemFW f, Submit source) {
	}			
	
	public void itemdeleteOnAction(ItemFW f, Submit source) {
	}			
	
	public void itemnodeleteOnAction(ItemFW f, Submit source) {
	}			
	
	public void additemfileOnAction(ItemFW f, Action source) {
	}			
	
	public void additemlinkOnAction(ItemFW f, Action source) {
	}			
	
	public void addlinkOnAction(ItemFW f, RepeaterAction source) {
	}			
	
	public void removelinkOnAction(ItemFW f, RepeaterAction source) {
	}			
	
	public void addnewimagesOnAction(ItemFW f, Action source) {
	}			
	
	public void removeimagesOnAction(ItemFW f, RepeaterAction source) {
	}			
	
	public void imagessortbydateOnAction(ItemFW f, RepeaterAction source) {
	}			
	
	public void imagessortnaturalOnAction(ItemFW f, RepeaterAction source) {
	}			
	
	public void apcfilterbuttonOnAction(ItemFW f, Action source) {
	}			
	
	public void wcsortbydateOnAction(ItemFW f, RepeaterAction source) {
	}			
	
	public void wcsortnaturalOnAction(ItemFW f, RepeaterAction source) {
	}			
	
	public void wcfilterbuttonOnAction(ItemFW f, Action source) {
	}			
	
	public void pagefirstOnAction(ItemFW f, RepeaterAction source) {
	}			
	
	public void pageprevOnAction(ItemFW f, RepeaterAction source) {
	}			
	
	public void pagenextOnAction(ItemFW f, RepeaterAction source) {
	}			
	
	public void pagelastOnAction(ItemFW f, RepeaterAction source) {
	}			
	
	public void pagecustomOnAction(ItemFW f, RepeaterAction source) {
	}			
	
     public void itemfiles_itemfiledeleteOnAction(ItemFW f, Action source, int rowIndex) {
	}			 			   
			   
     public void itemlinks_itemlinkdeleteOnAction(ItemFW f, Action source, int rowIndex) {
	}			 			   
			   
     public void images_deleteitemOnAction(ItemFW f, Action source, int rowIndex) {
	}			 			   
			   
     public void photogallery_deleteitemOnAction(ItemFW f, Action source, int rowIndex) {
	}			 			   
			   		
	


    
    
////// ###################################################################  ///////
////// ###########       ON VALUE CHANGED EVENT HANDLERS   ###############  ///////
////// ###################################################################  ///////

    // qui ci devono finire tutti i field, booleanfield, 
    
	
	public void tab_stateOnValueChanged(ItemFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void check_change_authorOnValueChanged(ItemFW f, BooleanField source, Boolean newValue, Boolean oldValue) {
	}			
	
	public void item_idOnValueChanged(ItemFW f, Field source, Integer newValue, Integer oldValue) {
	}			
	
	public void item_authorOnValueChanged(ItemFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void item_creatorOnValueChanged(ItemFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void item_templateOnValueChanged(ItemFW f, Field source, Integer newValue, Integer oldValue) {
	}			
	
	public void item_dateOnValueChanged(ItemFW f, Field source, Date newValue, Date oldValue) {
	}			
	
	public void item_expirationOnValueChanged(ItemFW f, Field source, Date newValue, Date oldValue) {
	}			
	
	public void item_noexpirationOnValueChanged(ItemFW f, BooleanField source, Boolean newValue, Boolean oldValue) {
	}			
	
	public void item_titleOnValueChanged(ItemFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void item_shortOnValueChanged(ItemFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void item_bodyOnValueChanged(ItemFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void item_stateOnValueChanged(ItemFW f, Field source, Integer newValue, Integer oldValue) {
	}			
	
	public void upload_imageOnValueChanged(ItemFW f, Upload source, Object newValue, Object oldValue) {
	}			
	
	public void item_labelimageOnValueChanged(ItemFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void upload_thumbnailOnValueChanged(ItemFW f, Upload source, Object newValue, Object oldValue) {
	}			
	
	public void item_labelthumbnailOnValueChanged(ItemFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void item_printOnValueChanged(ItemFW f, BooleanField source, Boolean newValue, Boolean oldValue) {
	}			
	
	public void item_sendOnValueChanged(ItemFW f, BooleanField source, Boolean newValue, Boolean oldValue) {
	}			
	
	public void item_comments_onOnValueChanged(ItemFW f, BooleanField source, Boolean newValue, Boolean oldValue) {
	}			
	
	public void item_meta_titleOnValueChanged(ItemFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void item_meta_descriptionOnValueChanged(ItemFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void item_meta_keywordsOnValueChanged(ItemFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void itemlinkOnValueChanged(ItemFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void itemlinkdescOnValueChanged(ItemFW f, Field source, String newValue, String oldValue) {
	}			
	
	public void topageOnValueChanged(ItemFW f, Field source, Integer newValue, Integer oldValue) {
	}			
	    
    
    
//////////////////////////////////////////////////////////////////////////////////////    
//////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////    

    
    protected void enableWidget(Widget w) {
        w.setState(WidgetState.ACTIVE);
    }
    
    protected void disableWidget(Widget w) {
        w.setState(WidgetState.DISABLED);
    }   
    
    protected void hideWidget(Widget w) {
        w.setState(WidgetState.INVISIBLE);
    }   
    
    protected void readonlyWidget(Widget w) {
        w.setState(WidgetState.OUTPUT);
    }   
    
		
 		
 } 		
