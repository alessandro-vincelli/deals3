/*
 * Created on May 9, 2007
 *
 */
package org.deals.framework.cms.forms.eh;

import java.util.List;
import java.util.Vector;

import org.apache.cocoon.forms.binding.BindingException;
import org.apache.cocoon.forms.event.ActionEvent;
import org.apache.cocoon.forms.event.RepeaterEvent;
import org.apache.cocoon.forms.event.RepeaterEventAction;
import org.apache.cocoon.forms.event.RepeaterListener;
import org.apache.cocoon.forms.formmodel.Repeater;
import org.apache.cocoon.forms.formmodel.Submit;
import org.apache.cocoon.forms.formmodel.Upload;
import org.apache.cocoon.forms.formmodel.WidgetState;
import org.apache.cocoon.forms.formmodel.Repeater.RepeaterRow;
import org.apache.cocoon.servlet.multipart.Part;
import org.apache.log4j.Logger;
import org.deals.framework.bean.ComponentType;
import org.deals.framework.cms.forms.eh.abstracts.AbstractResourcesRepoEH;
import org.deals.framework.cms.forms.formbean.ResourcesRepoBinder;
import org.deals.framework.cms.forms.formbean.ResourcesRepoFB;
import org.deals.framework.cms.forms.wrapper.ResourcesRepoFW;
import org.deals.framework.core.MimeType;
import org.deals.framework.core.WebCMS;
import org.deals.framework.repo.RepoBinary;
import org.deals.framework.util.SafeUtil;

public class ResourcesRepoEH extends AbstractResourcesRepoEH {

	private Logger log = Logger.getLogger(getClass());

	private ResourcesRepoBinder bm;

	private ResourcesRepoFB fb;

	//private DealsSession cSession;
	
	private WebCMS cms;
	private RepoBinary rb;
	
	// FIXME upload
	//private FileUploadManager fileUploadManager;

	public void setCms(WebCMS cms) {
		this.cms = cms;
	}

	@Override
	public void formOnCreate(ResourcesRepoFW f) {
		super.formOnCreate(f);
		f.getwebcomponents().getRepeater().addRepeaterListener(new WebComponentsListener());
		//cSession = new DealsSession(session);
		bm = new ResourcesRepoBinder(session, cms, rb);
		//session.setAttribute("pippo", "pippo");
		
		// deve creare il form bean chiedendolo al binder,
		// quindi lo può caricare...
		fb = bm.getFormBean();
		try {
			f.load(fb);
		} catch (BindingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void componentssubmitOnAction(ResourcesRepoFW f, Submit source) {
		super.componentssubmitOnAction(f, source);

		f.getForm().validate();
		
		// salvo solo ese il form è valido
		if (f.getForm().isValid()) {

			List<Part> fileParts = new Vector<Part>();

			for (int i = 0; i < f.getNewwebcomponents().getRepeater().getSize(); i++) {
				Upload fileUp = f.getNewwebcomponents().getUpload(i);

				Part part = (Part) fileUp.getValue();
				String mimeType = part.getMimeType();
				if (SafeUtil.isNullOrEmpty(mimeType))
					mimeType = MimeType.byFileName(part.getFileName()).mimeType();

				f.getNewwebcomponents().setwrMimetypeValue(mimeType, i);
				f.getNewwebcomponents().setwrNameValue(part.getFileName(), i);
				f.getNewwebcomponents().setwcTypeValue(ComponentType.WEB_RESOURCE_ID, i);

				fileParts.add(part);
			}

			try {
				f.save(fb);
				fb.setFileParts(fileParts);
				//FIXME upload
				//bm.saveFormBean(fb, fileUploadManager);
				bm.saveFormBean(fb);
			} catch (BindingException e) {
				log.error("Errore nel save binding ResourcesRepoEH...", e);
			}
		}
		// SUBMIT ....................................................
		// deve estrarre dal repeater delle new components i widget di
		// upload per caricare le immagini.... solo dopo aver caricato
		// le immagini si possono salvare i record....................

		// sul repeater delle new resources chiaramente andranno
		// visualizzati soltanto i campi strettamenti editabili
		// (descrizione e alt)... gli altri li dovrò estrarre
		// dal Part e dovrò settare i dati sui bean...

		// se volessi eliminare le risorse da file system
		// dovrei individuare le risorse eliminate, prenderne il nome
		// e risalire al file, e in questo modo eliminarle...

		// controllare come posso creare una nuova risorsa senza
		// ancora averla salvata, quindi senza avere ancora l'id
		// 					

	}
	//FIXME upload
	/*
	public FileUploadManager getFileUploadManager() {
		return fileUploadManager;
	}

	public void setFileUploadManager(FileUploadManager fileUploadManager) {
		this.fileUploadManager = fileUploadManager;
	}
*/
	

	@Override
	public void handleActionEvent(ActionEvent arg0) {
		super.handleActionEvent(arg0);
		if(arg0.getSourceWidget().toString().indexOf("actionpreviewimage") > 0){
			RepeaterRow rr = (RepeaterRow)arg0.getSourceWidget().getParent();
			//String uri = (String)rr.lookupWidget("preview_image").getValue();
			
			if(rr.lookupWidget("previewgroup").getState().equals(WidgetState.ACTIVE)){
				rr.lookupWidget("previewgroup").setState(WidgetState.INVISIBLE);
			}
			else{
				rr.lookupWidget("previewgroup").setState(WidgetState.ACTIVE);	
			}
			
			//arg0.getSourceWidget().getForm().lookupWidget("previewgroup").setState(WidgetState.ACTIVE);
			//arg0.getSourceWidget().getForm().lookupWidget("gruppo_preview_immagine").lookupWidget("zpreview_image").setValue("pippo");
			//((ImageMap)arg0.getSourceWidget().getForm().lookupWidget("gruppo_preview_immagine").lookupWidget("zpreview_image")).setImageURI(uri);
		}
		//
	}

	class WebComponentsListener implements RepeaterListener {

		public void repeaterModified(RepeaterEvent event) {
			
			//Repeater rriga = (Repeater) event.getSourceWidget().getParent().lookupWidget("webcomponents");
			//RepeaterRow rr = rriga.getRow(idxRiga);
			// Gestione cancellazione righe webcomponent
			if (event.getAction().equals(RepeaterEventAction.ROW_ADDED)) {
				int idxRiga = event.getRow();
				Repeater rriga = (Repeater) event.getSourceWidget().getParent().lookupWidget("webcomponents");
				RepeaterRow rr = rriga.getRow(idxRiga);
				//rr.getChild("preview_image").setState(WidgetState.INVISIBLE);
			}
			/*
			if (event.getAction().getName().equals("Row deleting")) {
				int idxRiga = event.getRow();
				Repeater rriga = (Repeater) event.getSourceWidget().getParent().lookupWidget("webcomponents");
				RepeaterRow rr = rriga.getRow(idxRiga);
				

				if (rr.getChild("wc_id").getValue() != null) {
					Integer wcId = (Integer) rr.getChild("wc_id").getValue();
					
				}
			}*/
			if (event.getAction().equals(RepeaterEventAction.ROW_DELETING)) {
				RepeaterRow  rr = (RepeaterRow)((Repeater)event.getSourceWidget()).getRow(event.getRow());
				Integer id = (Integer)rr.lookupWidget("wc_id").getValue();
				cms.deleteWebComponent(cms.getWebComponent(id));
				return;	
			}

		}

	}

	
	
	public void setRb(RepoBinary rb) {
		this.rb = rb;
	}

}
