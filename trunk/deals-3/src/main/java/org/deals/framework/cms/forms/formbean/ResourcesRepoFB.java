package org.deals.framework.cms.forms.formbean;

import java.util.Calendar;
import java.util.List;

import org.apache.cocoon.servlet.multipart.Part;
import org.deals.framework.bean.ComponentType;
import org.deals.framework.bean.WebComponent;

public class ResourcesRepoFB {

	private List<WebComponent> resources;

	private List<WebComponent> newresources;

	private List<Part> fileParts;

	public List<WebComponent> getResources() {
		return resources;
	}

	public void setResources(List<WebComponent> resources) {
		this.resources = resources;
	}

	public List<WebComponent> getNewresources() {
		return newresources;
	}

	public void setNewresources(List<WebComponent> newresources) {
		this.newresources = newresources;
	}

	public List<Part> getFileParts() {
		return fileParts;
	}

	public void setFileParts(List<Part> fileParts) {
		this.fileParts = fileParts;
	}

	public void addComponent(WebComponent wc) {
		// setto la data di inserimento
		wc.setWcInsertDate(Calendar.getInstance().getTime());
		// setto di default il tipo generico
		wc.setComponentType(ComponentType.WEB_RESOURCE());
		// aggiungo la componente all'elenco delle componenti
		newresources.add(wc);
	}

}
