/*
 * Created on Nov 14, 2006
 *
 */
package org.deals.framework.cms.forms.eh;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.cocoon.forms.datatype.SelectionList;
import org.apache.cocoon.forms.datatype.StaticSelectionList;
import org.apache.cocoon.forms.event.ActionEvent;
import org.apache.cocoon.forms.formmodel.Action;
import org.apache.cocoon.forms.formmodel.Field;
import org.apache.cocoon.forms.formmodel.ImageMap;
import org.apache.cocoon.forms.formmodel.MultiValueField;
import org.apache.cocoon.forms.formmodel.RepeaterAction;
import org.apache.cocoon.forms.formmodel.Submit;
import org.apache.cocoon.forms.formmodel.Upload;
import org.apache.cocoon.forms.formmodel.Widget;
import org.apache.cocoon.forms.formmodel.WidgetState;
import org.apache.cocoon.forms.formmodel.Repeater.RepeaterRow;
import org.apache.cocoon.servlet.multipart.Part;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.deals.framework.cms.forms.eh.abstracts.AbstractItemEH;
import org.deals.framework.cms.forms.wrapper.ItemFW;
import org.deals.framework.core.MimeType;
import org.deals.framework.util.SafeUtil;

public class ItemEH extends AbstractItemEH {
	
	Logger log = Logger.getLogger(getClass());

	@Override
	public void formOnCreate(ItemFW f) {
		super.formOnCreate(f);
	}
	

	@Override
	public void itemsubmitOnAction(ItemFW f, Submit source) {
		super.itemsubmitOnAction(f, source);
	}

	@Override
	public void addlinkOnAction(ItemFW f, RepeaterAction source) {
		super.addlinkOnAction(f, source);
		int size = f.getItemlinks().getRepeater().getSize();
		f.getItemlinks().setRowidValue(size, size - 1);
	}

	@Override
	public void handleActionEvent(ActionEvent arg0) {
		super.handleActionEvent(arg0);

		if (arg0.getActionCommand() != null) {
			if (arg0.getActionCommand().equals("addtags")) {
				MultiValueField tags = (MultiValueField)arg0.getSourceWidget().getForm().lookupWidget("tags");
				
				Field wpTags = (Field)arg0.getSourceWidget().getForm().lookupWidget("item_tags");
				String wpt = (String)wpTags.getValue();
				HashSet<String> hs = new HashSet<String>();
				if(StringUtils.isNotEmpty(wpt)){
					if( wpt.split(";") != null){
						String[] wp1 = wpt.split(";");
						for (int i = 0; i < wp1.length; i++) {
							hs.add(wp1[i]);
						}
					}
				}
				if(!(ArrayUtils.isEmpty((Object[])tags.getValue()))){
					String[] wp2 = (String[])tags.getValue();
					for (int i = 0; i < wp2.length; i++) {
						if(StringUtils.isNotEmpty(wp2[i])){
							hs.add(wp2[i]);
						}
					}
				}
				String[] aaa = (String[]) hs.toArray(new String[hs.size()]);
				if (aaa.length > 0){
					wpTags.setValue(StringUtils.join(aaa, ";"));
				}
			}
		}
	}

	@Override
	public void upload_imageOnValueChanged(ItemFW f, Upload source, Object newValue, Object oldValue) {
		if (newValue != null) {
			super.upload_imageOnValueChanged(f, source, newValue, oldValue);
			caricaFoto(source);
			f.getUploadImage().setValue(null); // rimette a null il widget di upload
		}
	}

	@Override
	public void upload_thumbnailOnValueChanged(ItemFW f, Upload source, Object newValue, Object oldValue) {
		if (newValue != null) {
			super.upload_thumbnailOnValueChanged(f, source, newValue, oldValue);
			caricaThumbnail(source);
			f.getUploadThumbnail().setValue(null); // rimette a null il widget di upload
		}
	}

	/**
	 * esegue l'upload delle foto nella directory "_upload"
	 * 
	 * @param foto
	 * @param idRisto
	 * @return
	 */
	private boolean caricaFoto(Upload foto) {
		
		try {
		} catch (Exception x) {
			x.printStackTrace();
		}

		Part part = (Part) foto.getValue();
		try {
			String mimeType = part.getMimeType();
			if (SafeUtil.isNullOrEmpty(mimeType))
				mimeType = MimeType.byFileName(part.getFileName()).mimeType();
			ImageMap image = (ImageMap) fw.getForm().getChild("itemimage"); 
			return true;
		} catch (Exception x) {
			log.error("Exception xyz", x);
			log.error(x);
			log.error(x.getStackTrace()[0]);
			x.printStackTrace();
			return false;
		}
	}

	@Override
	public void addnewimagesOnAction(ItemFW f, Action source) {
		super.addnewimagesOnAction(f, source);
		int size = f.getImageswebcomponents().getRepeater().getSize();

		for (int i = 0; i < size; i++) {
			RepeaterRow rr = f.getImageswebcomponents().getRepeater().getRow(i);

			if (rr.lookupWidget("select").getValue().toString() == "true") {

				// prendo l'ultima riga appena inserita
				RepeaterRow rrImage = f.getImages().getRepeater().addRow();

				int sizeRImages = f.getImages().getRepeater().getSize();
				rrImage.lookupWidget("rowid").setValue(sizeRImages);
				rrImage.lookupWidget("apc_page_id").setValue(rr.getForm().lookupWidget("item_id").getValue());
				rrImage.lookupWidget("apc_component_id").setValue(rr.lookupWidget("wc_id").getValue());
				rrImage.lookupWidget("apc_alt").setValue(rr.lookupWidget("wr_alt").getValue());
				rrImage.lookupWidget("apc_description").setValue(rr.lookupWidget("wr_description").getValue());
				rrImage.lookupWidget("image_name").setValue(rr.lookupWidget("wr_name").getValue());
			}
		}
	}

	private boolean caricaThumbnail(Upload foto) {

		Part part = (Part) foto.getValue();
		try {
			String mimeType = part.getMimeType();
			if (SafeUtil.isNullOrEmpty(mimeType))
				mimeType = MimeType.byFileName(part.getFileName()).mimeType();
			return true;
		} catch (Exception x) {
			log.error("Exception xyz", x);
			log.error(x);
			log.error(x.getStackTrace()[0]);
			x.printStackTrace();
			return false;
		}
	}

	private boolean caricaFile(Upload file) {
		Part part = (Part) file.getValue();
		try {
			String mimeType = part.getMimeType();
			if (SafeUtil.isNullOrEmpty(mimeType))
				mimeType = MimeType.byFileName(part.getFileName()).mimeType();
			return true;
		} catch (Exception x) {
			// TODO usare questo per loggare le eccezioni
			log.error("Exception xyz", x);
			log.error(x);
			log.error(x.getStackTrace()[0]);
			x.printStackTrace();
			return false;
		}
	}

	/**
	 * cancella la scheda
	 * 
	 */
	@Override
	public void itemdeleteOnAction(ItemFW f, Submit source) {
		super.itemdeleteOnAction(f, source);
		f.setAttribute("deleteMe", true);
	}

	/**
	 * restituisce il controllo al flow senza fare nulla
	 */
	@Override
	public void itemnodeleteOnAction(ItemFW f, Submit source) {
		super.itemnodeleteOnAction(f, source);
	}

	private SelectionList hashMapToSelectionList(HashMap hashMap, Field owner) {
		StaticSelectionList ssl = new StaticSelectionList(owner.getDatatype());
		Iterator iter = hashMap.keySet().iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			ssl.addItem(key, hashMap.get(key).toString());
		}
		return ssl;
	}

	/**
	 * Disabilito tutti i campi, Nota: per il momento non usata perchè resa grafica pessima
	 * 
	 * @param f
	 */
	private void disabilitaTuttiCampi(ItemFW f) {
		for (Iterator iter = f.getForm().getChildren(); iter.hasNext();) {
			Widget campo = (Widget) iter.next();
			campo.setState(WidgetState.DISABLED);
		}
	}

}
