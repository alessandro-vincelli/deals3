package org.deals.framework.cms.forms.formbean;

import java.util.List;
import java.util.Vector;

import org.deals.framework.bean.PageState;
import org.deals.framework.bean.Template;
import org.deals.framework.bean.UserProfile;
import org.deals.framework.bean.WebPage;

public class CategoryFB {

	private WebPage bean;		
	
	private boolean checkChangeAuthor = false; // 
	private boolean categoryNoexpiration = false; // se data scadenza == null true
	private List<UserProfile> profiles;	

	public List<UserProfile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<UserProfile> profiles) {
		this.profiles = profiles;
	}

	public CategoryFB(WebPage bean) {
		this.bean = bean;
		if (bean.getWpAuthor() != null) {
			if (!bean.getWpAuthor().equals("")) {
				checkChangeAuthor = true;
			}
		}
		if (bean.getWpExpire() == null)
			categoryNoexpiration = true;
	}
	
	public WebPage getBean() {
		return bean;
	}
	
	public void setBean(WebPage bean) {
		this.bean = bean;
	}	
	

	public boolean isCheckChangeAuthor() {
		return checkChangeAuthor;
	}

	public void setCheckChangeAuthor(boolean checkChangeAuthor) {
		this.checkChangeAuthor = checkChangeAuthor;
	}

	
	public boolean isCategoryNoexpiration() {
		return categoryNoexpiration;
	}
	
	public void setCategoryNoexpiration(boolean categoryNoexpiration) {
		this.categoryNoexpiration = categoryNoexpiration;
	}
	
	
	// //////////////////////////////////////////////////////////////////////////////////////////////////
	
		
	private List<Template> templateSL;

	private List<PageState> pageStateSL;
	
	public List<PageState> getPageStateSL() {
		return pageStateSL;
	}

	public void setPageStateSL(List<PageState> pageStateSL) {
		this.pageStateSL = pageStateSL;
	}

	public List<Template> getTemplateSL() {
		return templateSL;
	}

	public void setTemplateSL(List<Template> templateSL) {
		this.templateSL = templateSL;
	}

		
	// //////////////////////////////////////////////////////////////////////////////////////////////////

	
	
	
	
	
//	private boolean categorySend; // ??????????????? 

	private Vector<SectionStructure> sectionStructure = new Vector<SectionStructure>();

//	private Vector<SectionStructure> sectionStructureBackup = new Vector<SectionStructure>();


	public void addSectionStructure(SectionStructure sec) {
		sectionStructure.addElement(sec);
	}

//	public void createSectionsBackup() {
//		Iterator<SectionStructure> iter = sectionStructure.iterator();
//		while (iter.hasNext()) {
//			SectionStructure ss = iter.next();
//			SectionStructure bSs = new SectionStructure();
//			bSs.id = ss.id;
//			bSs.checked = ss.checked;
//			bSs.label = ss.label;
//			sectionStructureBackup.add(bSs);
//		}
//	}


//	public Vector<SectionStructure> getSectionsBackup() {
//		return sectionStructureBackup;
//	}
	
	public Vector<SectionStructure> getSectionStructure() {
		return sectionStructure;
	}
	
//	public Vector<SectionStructure> getSectionStructureBackup() {
//		return sectionStructureBackup;
//	}


	// ////////////////////////////////////////////////////////////////
	
	public void setSectionStructure(Vector<SectionStructure> sectionStructure) {
		this.sectionStructure = sectionStructure;
	}

//	public void setSectionStructureBackup(Vector<SectionStructure> sectionStructureBackup) {
//		this.sectionStructureBackup = sectionStructureBackup;
//	}
//
		
	/**
	 * Classe interna che rappresenta la struttura dei check-box di associazione per sezioni e
	 * categorie Ogni sezione ha un checkbox per la selezione, una label di descrizione e una
	 * struttura interna di categorie
	 */
	public class SectionStructure {

		private boolean checked;
		private WebPage page;

		public SectionStructure(WebPage section) {
			page = section;
			checked = false;
		}		
		
		public boolean isChecked() {
			return checked;
		}

		public void setChecked(boolean checked) {
			this.checked = checked;
		}

		public WebPage getPage() {
			return page;
		}

		public void setPage(WebPage page) {
			this.page = page;
		}		
	}
	
	
	
}
