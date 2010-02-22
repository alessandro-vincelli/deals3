package org.deals.framework.cms.forms.manager;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.forms.binding.Binding;
import org.apache.cocoon.forms.binding.BindingException;
import org.apache.cocoon.forms.formmodel.ImageMap;
import org.apache.cocoon.forms.formmodel.MultiValueField;
import org.apache.cocoon.forms.formmodel.WidgetState;
import org.apache.log4j.Logger;
import org.deals.framework.cms.forms.formbean.ItemBinder;
import org.deals.framework.cms.forms.formbean.ItemFB;
import org.deals.framework.cms.forms.wrapper.ItemFW;
import org.deals.framework.core.WebCMS;

public class ItemManager {
	private ItemBinder ib;
	private WebCMS cms;
	private Logger log = Logger.getLogger(getClass());
	private ItemFB fb;

	public ItemFW load(ItemFW f, Request request, Binding binding){
		ib = new ItemBinder(request.getSession(), cms);

		try {
			int pageid = -1;

			if (request.getParameter("pageid") != null) {
				if (!request.getParameter("pageid").equals("")) {
					pageid = Integer.parseInt(request.getParameter("pageid"));
				}
			}
 
			if (pageid != -1) {
				fb = ib.getFormBean(pageid);
			} else {
				try {
					fb = ib.newFormBean();
				} catch (Exception e) {
					log.error("_____Errore nella creazione del nuovo formBean della scheda!" + e);
				}
			}
			f.load(fb);

			f.getItemTemplate().setSelectionList(fb.getTemplateSL(), "teId", "teName");
			f.getItemState().setSelectionList(fb.getPageStateSL(), "psId", "psName");
			
			((MultiValueField)f.getForm().lookupWidget("tags")).setSelectionList(toCollection(fb.getTags()), "value", "value");

			// effettua l'aggancio dell'eventuale immagine principale della pagina
			if (fb.getBean().getPageImage() != null) {
				ImageMap image = (ImageMap) f.getForm().getChild("itemimage");
				// FIXME
				// image.setImageURI(fb.getBean().getPageImage().getWebResourcePathOnSite());
			}
			if (fb.getBean().getPageThumbnail() != null) {
				ImageMap image = (ImageMap) f.getForm().getChild("itemthumbnail");
				// FIXME
				// image.setImageURI(fb.getBean().getPageThumbnail().getWebResourcePathOnSite());
			}

			f.getItemnodelete().setState(WidgetState.INVISIBLE);
			f.getItemdelete().setState(WidgetState.INVISIBLE);
			if (request.getParameter("delete") != null) {
				if (!request.getParameter("delete").equals("")) {
					f.getItemsubmit().setState(WidgetState.INVISIBLE);
					f.getItemnodelete().setState(WidgetState.ACTIVE);
					f.getItemdelete().setState(WidgetState.ACTIVE);
				}
			}

		} catch (BindingException e) {
			log.error("ERROR on binding " + e.getMessage());
			e.printStackTrace();
		}
		return f;
	}

	public ItemFW persist(ItemFW f, Request request, Binding binding){
		log.debug("_________________________________Peristing Item ");
		try {
			if(f.getAttribute("deleteMe")!= null && f.getAttribute("deleteMe").equals(true)){
				ib.deleteFormBean(fb);
			}
			else{
				f.save(fb);
				ib.saveFormBean(fb, f);	
			}
		} catch (BindingException e) {
			log.error("ERROR ON Persisting modification (Save or Delete) " + e.toString() + e.getStackTrace()[0]);
			e.printStackTrace();
		}
		return f;
	}

	public void setCms(WebCMS cms) {
		this.cms = cms;
	}
	
	/**
	 * must be public in order to work with JxPath Form binding/creating
	 * @author alessandro
	 *
	 */
	public class KeyValue{
		String key;
		String value;
		
		public KeyValue() {
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
				
	}
	private Collection toCollection(String[] tags){
		ArrayList<KeyValue> aa = new ArrayList<KeyValue>(); 
		for (int i = 0; i < tags.length; i++) {
			KeyValue kv = new KeyValue();
			kv.setKey(tags[i]);
			kv.setValue(tags[i]);
			aa.add(kv);
		}
		return aa;
	}
}
