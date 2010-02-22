package org.deals.framework.cms.forms.formbean;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.cocoon.servlet.multipart.Part;
import org.apache.log4j.Logger;
import org.deals.framework.bean.ComponentType;
import org.deals.framework.bean.WebComponent;
import org.deals.framework.cms.DealsSession;
import org.deals.framework.core.MimeType;
import org.deals.framework.core.UrlCreator;
import org.deals.framework.core.WebCMS;
import org.deals.framework.repo.RepoBinary;

public class ResourcesRepoBinder {
	private Logger log = Logger.getLogger(getClass());

	ResourcesRepoFB fb;
	private RepoBinary rb;
	// private Logger log = Logger.getLogger(getClass());
	private WebCMS cms;

	//private HibernateUtiliObjectInHttpSession huos;

	//private WebComponentDAO wcDao = DAOFactory.instance(DAOFactory.HIBERNATE).getWebComponentDAO();

	DealsSession ds;

	public ResourcesRepoBinder(HttpSession session, WebCMS cms, RepoBinary rb) {
		//huos = new HibernateUtiliObjectInHttpSession(session);
		ds = new DealsSession(session);
		this.cms = cms;
		this.rb = rb;
	}

	public ResourcesRepoFB getFormBean() {
		// chiede al CMS la lista di tutte le web components presenti
		// e le restituisce
		fb = new ResourcesRepoFB();
		fb.setResources(cms.getWebResources());
		fb.setNewresources(new Vector<WebComponent>());
		//huos.putObject(IKeys.CMS_RESOURCES_REPO_RESOURCES, fb.getResources());
		return fb;
	}

	/**
	 * Si occupa di salvare il form bean
	 * 
	 * @param formBean
	 * @param uploader
	 */
	public void saveFormBean(ResourcesRepoFB formBean) {

		// deve recuperare le new resources...
		// ... ed aggiungere le componenti alle resources
		// che poi verranno persistite
		// huos.putObject(IKeys.CMS_RESOURCES_REPO_RESOURCES, formBean.getResources());
		
		//FIXME test rewrite DELETE
		/*
		List<WebComponent> toRemove = cms.getWebResources();
		toRemove.removeAll(formBean.getResources());
		for (Iterator iter = toRemove.iterator(); iter.hasNext();) {
			WebComponent wc = (WebComponent) iter.next();
			if(wc.getAPageComponentses().isEmpty()){
				wcDao.makeTransient(wc); 	
			}
			else{
				log.error("Attenzione, tentati di cancellazione risorsa ancora associata a schede");
			}
		}
		*/
		//cms.saveAllWebComponent(formBean.getResources());
		List<WebComponent> newRes = formBean.getNewresources();
		for (int i = 0; i < newRes.size(); i++) {
			WebComponent comp = newRes.get(i);
			// imposto staticamente la componente a web_reources generica
			comp.setComponentType(ComponentType.WEB_RESOURCE());
			cms.saveWebComponent(comp);
			// quindi ora comp ha un id assegnato
			Part part = formBean.getFileParts().get(i);
			//String imageuri = UrlCreator.createFilePathOnDisk(comp.getWcId(), part.getFileName(), MimeType.getMimeType(comp.getWrMimetype()));
			String imageuri = UrlCreator.createUniqueFileName(comp.getWcId(),part.getFileName());
			// spostare il file nella posizione giusta...
			try {
				//FIXME upload
				//uploader.upload(part, imageuri);
				rb.saveBinary(imageuri, part.getInputStream(), MimeType.getMimeType(comp.getWrMimetype()).mimeType());	
			} catch (Exception e) {
				log.error("Error on saving binary on repo", e);
			}
		}
		// se non lo toglio dalla sessione, da' errore se riattacchio l'oggeto e gli hai cambiato il tipo, se fai
		// una modifica d un campo di testo invece no
		/*huos.removeObject(IKeys.CMS_RESOURCES_REPO_RESOURCES);
		huos.removeObject(IKeys.CMS_MANAGEMENT_ITEMS);
		huos.removeObject(IKeys.CMS_EDIT_ITEM_LINKS);
		huos.removeObject(IKeys.CMS_EDIT_ITEM_IMAGES);
		huos.removeObject(IKeys.CMS_EDIT_REPO_RESOURCES);*/
		/*
		 * 
		 */
	}

	/**
	 * Elimina la componente web
	 * 
	 * @param wcId
	 *            chiave primaria della componente
	 * 
	 */
	/*
	public void deleteWebComponent(Integer wcId) {
		WebComponent wc = wcDao.findById(wcId, false);
		if (wc != null) {
			wcDao.makeTransient(wc);
			//huos.removeObject(IKeys.CMS_RESOURCES_REPO_RESOURCES);
		}
	}
*/
}
