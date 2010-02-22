package org.deals.framework.cms.forms.formbean;

import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import org.deals.framework.bean.APageComponents;
import org.deals.framework.bean.APageComponentsType;
import org.deals.framework.bean.ComponentType;
import org.deals.framework.bean.PageState;
import org.deals.framework.bean.Template;
import org.deals.framework.bean.WebComponent;
import org.deals.framework.bean.WebPage;

public class ItemFB {

	private WebPage bean;

	private boolean checkChangeAuthor = false; // 

	private boolean itemNoexpiration = false; // se data scadenza == null true

	private List<WebComponent> imagesresources;

	private List<APageComponents> files;
	
	private List<APageComponents> images;

	private List<APageComponents> links;
	
	private List<APageComponentsType> aPageComponentsTypeSL;
	
	private String[] tags;

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public ItemFB(WebPage bean) {
		this.bean = bean;
		if (bean.getWpAuthor() != null) {
			if (!bean.getWpAuthor().equals("")) {
				checkChangeAuthor = true;
			}
		}
		if (bean.getWpExpire() == null)
			itemNoexpiration = true;
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

	public boolean isItemNoexpiration() {
		return itemNoexpiration;
	}

	public void setItemNoexpiration(boolean itemNoexpiration) {
		this.itemNoexpiration = itemNoexpiration;
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

	private Vector<SectionStructure> sectionStructure = new Vector<SectionStructure>();

	public void addSectionStructure(SectionStructure sec) {
		sectionStructure.addElement(sec);
	}

	public Vector<SectionStructure> getSectionStructure() {
		return sectionStructure;
	}

	// ////////////////////////////////////////////////////////////////

	public void setSectionStructure(Vector<SectionStructure> sectionStructure) {
		this.sectionStructure = sectionStructure;
	}

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

		private Vector<CategoryStructure> categoryStructure = new Vector<CategoryStructure>();

		public void addCategory(CategoryStructure cat) {
			categoryStructure.addElement(cat);
		}

		public Vector<CategoryStructure> getCategoryStructure() {
			return categoryStructure;
		}

		public void setCategoryStructure(Vector<CategoryStructure> categoryStructure) {
			this.categoryStructure = categoryStructure;
		}

	}

	/**
	 * Classe interna che rappresenta la struttura dei check-box di associazione per categorie Ogni
	 * categoria ha un checkbox per la selezione e una label di descrizione
	 */
	public class CategoryStructure {

		private boolean checked;

		private WebPage page;

		public CategoryStructure(WebPage category) {
			page = category;
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

	/*
	 * 
	 * private WebPage bean;
	 * 
	 * private String itemTitle; // titolo private String itemAuthor; // autore private String
	 * itemCreator; // creatore private String itemLastModifier; // ultima modifica private Date
	 * itemDate; // data creazione private String itemShort; // header private String itemBody; //
	 * content private boolean checkChangeAuthor; // private int itemState; // stato pagina private
	 * int itemTemplate; // template pagina private Date itemExpiration; // data scadenza private
	 * boolean itemNoexpiration; // se data scadenza == null true private boolean itemPrint; //
	 * private boolean itemSend; //
	 * 
	 * private Vector files = new Vector(); private Vector links = new Vector();
	 * 
	 * public Vector getFiles() { return files; }
	 * 
	 * public void setFiles(Vector files) { this.files = files; }
	 * 
	 * public void addFile(int progre, String description) { FileDescriptor fd = new
	 * FileDescriptor(progre, description); files.add(fd); }
	 * 
	 * public void addFile(int progre, String description, String href) { FileDescriptor fd = new
	 * FileDescriptor(progre, description,href); files.add(fd); }
	 * 
	 * public Vector getLinks() { return links; }
	 * 
	 * public void setLinks(Vector links) { this.links = links; }
	 * 
	 * public void addLink(int progre, String href, String description) { LinkDescriptor ld = new
	 * LinkDescriptor(progre, href, description); links.add(ld); }
	 * 
	 * 
	 * private HashMap templateSL; private HashMap pageStateSL;
	 * 
	 * public HashMap getPageStateSL() { return pageStateSL; } public void setPageStateSL(HashMap
	 * pageStateSL) { this.pageStateSL = pageStateSL; } public HashMap getTemplateSL() { return
	 * templateSL; } public void setTemplateSL(HashMap templateSL) { this.templateSL = templateSL; }
	 * public boolean isCheckChangeAuthor() { return checkChangeAuthor; } public String
	 * getItemAuthor() { return itemAuthor; } public String getItemBody() { return itemBody; }
	 * public String getItemCreator() { return itemCreator; } public Date getItemDate() { return
	 * itemDate; } public Date getItemExpiration() { return itemExpiration; } public String
	 * getItemLastModifier() { return itemLastModifier; } public boolean isItemNoexpiration() {
	 * return itemNoexpiration; } public boolean isItemPrint() { return itemPrint; } public boolean
	 * isItemSend() { return itemSend; } public String getItemShort() { return itemShort; } public
	 * int getItemState() { return itemState; } public int getItemTemplate() { return itemTemplate; }
	 * public String getItemTitle() { return itemTitle; } public void setCheckChangeAuthor(boolean
	 * checkChangeAuthor) { this.checkChangeAuthor = checkChangeAuthor; } public void
	 * setItemAuthor(String itemAuthor) { this.itemAuthor = itemAuthor; } public void
	 * setItemBody(String itemBody) { this.itemBody = itemBody; } public void setItemCreator(String
	 * itemCreator) { this.itemCreator = itemCreator; } public void setItemDate(Date itemDate) {
	 * this.itemDate = itemDate; } public void setItemExpiration(Date itemExpiration) {
	 * this.itemExpiration = itemExpiration; } public void setItemLastModifier(String
	 * itemLastModifier) { this.itemLastModifier = itemLastModifier; } public void
	 * setItemNoexpiration(boolean itemNoexpiration) { this.itemNoexpiration = itemNoexpiration; }
	 * public void setItemPrint(boolean itemPrint) { this.itemPrint = itemPrint; } public void
	 * setItemSend(boolean itemSend) { this.itemSend = itemSend; } public void setItemShort(String
	 * itemShort) { this.itemShort = itemShort; } public void setItemState(int itemState) {
	 * this.itemState = itemState; } public void setItemTemplate(int itemTemplate) {
	 * this.itemTemplate = itemTemplate; } public void setItemTitle(String itemTitle) {
	 * this.itemTitle = itemTitle; } public WebPage getBean() { return bean; } public void
	 * setBean(WebPage bean) { this.bean = bean; }
	 * 
	 * 
	 * 
	 * public void createSectionAndCategoriesBackup() { Iterator<SectionStructure> iter =
	 * sectionStructure.iterator(); while (iter.hasNext()) { SectionStructure ss = iter.next();
	 * SectionStructure bSs = new SectionStructure(); bSs.id = ss.id; bSs.checked = ss.checked;
	 * bSs.label = ss.label;
	 * 
	 * Iterator<CategoryStructure> iter2 = ss.getCategoryStructure().iterator(); while
	 * (iter2.hasNext()) { CategoryStructure cs = iter2.next(); CategoryStructure bCs = new
	 * CategoryStructure(); bCs.id = cs.id; bCs.checked = cs.checked; bCs.label = cs.label;
	 * bSs.addCategory(bCs); }
	 * 
	 * sectionStructureBackup.add(bSs); } }
	 * 
	 * 
	 * public Vector<SectionStructure> getSectionAndCategoriesBackup() { return
	 * sectionStructureBackup; }
	 * 
	 * //////////////////////////////////////////////////////////////////
	 * 
	 * private Vector<SectionStructure> sectionStructure = new Vector<SectionStructure>(); private
	 * Vector<SectionStructure> sectionStructureBackup = new Vector<SectionStructure>();
	 * 
	 * public void addSectionStructure(SectionStructure sec) { sectionStructure.addElement(sec); }
	 * 
	 * public Vector<SectionStructure> getSectionStructure() { return sectionStructure; } public
	 * void setSectionStructure(Vector<SectionStructure> sectionStructure) { this.sectionStructure =
	 * sectionStructure; }
	 * 
	 */

	
	public List<APageComponents> getLinks() {
		return links;
	}

	public void addLink(APageComponents apc) {
		// setto sull'associazione del nuovo link la relazione con la pagina che vuole il nuovo link
		apc.setWebPage(this.getBean());
		// creo un un nuovo componente che sarà il linka cui sarà associata la pagina
		WebComponent wc = new WebComponent();
		// setto il tipo RESOURCE LINK sulla nuova pagina
		wc.setComponentType(ComponentType.RESOURCE_LINK());
		// setto sull'associazione del nuovo link la relazione con la componente link appena creata
		apc.setWebComponent(wc);
		// setto la data di inserimento
		Calendar cal = Calendar.getInstance();
		apc.setApcAssocDate(cal.getTime());
		// setto il tipo dell'associazione a RESOURCE_LINK
		apc.setAPageComponentsType(APageComponentsType.RESOURCE_LINK());
		// aggiungo l'associazione all'elenco dei link
		links.add(apc);
	}
	
	public void addImages(APageComponents apc) {
		// setto sull'associazione del nuovo link la relazione con la pagina che vuole il nuovo link
		apc.setWebPage(this.getBean());
		Calendar cal = Calendar.getInstance();
		// setto la data di inserimento
		apc.setApcAssocDate(cal.getTime());
		// setto sull'associazione del nuovo link la relazione con la componente link appena creata
		//apc.setWebComponent(new WebComponent());
		// aggiungo l'associazione all'elenco dei link
		images.add(apc);
	}
	

	public void setLinks(List<APageComponents> links) {
		this.links = links;
	}

	/**
	 * Classe interna che rappresenta la struttura dei check-box di associazione per sezioni e
	 * categorie Ogni sezione ha un checkbox per la selezione, una label di descrizione e una
	 * struttura interna di categorie
	 */
	/*
	 * public class SectionStructure {
	 * 
	 * private int id;
	 * 
	 * private boolean checked;
	 * 
	 * private String label;
	 * 
	 * private Vector<CategoryStructure> categoryStructure = new Vector<CategoryStructure>();
	 * 
	 * public void addCategory(CategoryStructure cat) { categoryStructure.addElement(cat); }
	 * 
	 * public Vector<CategoryStructure> getCategoryStructure() { return categoryStructure; }
	 * 
	 * public void setCategoryStructure(Vector<CategoryStructure> categoryStructure) {
	 * this.categoryStructure = categoryStructure; }
	 * 
	 * public boolean isChecked() { return checked; }
	 * 
	 * public void setChecked(boolean checked) { this.checked = checked; }
	 * 
	 * public String getLabel() { return label; }
	 * 
	 * public void setLabel(String label) { this.label = label; }
	 * 
	 * public int getId() { return id; }
	 * 
	 * public void setId(int id) { this.id = id; } }
	 */
	/**
	 * Classe interna che rappresenta la struttura dei check-box di associazione per categorie Ogni
	 * categoria ha un checkbox per la selezione e una label di descrizione
	 */
	/*
	 * public class CategoryStructure {
	 * 
	 * private int id;
	 * 
	 * private boolean checked;
	 * 
	 * private String label;
	 * 
	 * public boolean isChecked() { return checked; }
	 * 
	 * public void setChecked(boolean checked) { this.checked = checked; }
	 * 
	 * public String getLabel() { return label; }
	 * 
	 * public void setLabel(String label) { this.label = label; }
	 * 
	 * public int getId() { return id; }
	 * 
	 * public void setId(int id) { this.id = id; } }
	 * 
	 * public class FileDescriptor { private int id;
	 * 
	 * private String description;
	 * 
	 * private String href;
	 * 
	 * public FileDescriptor() { this(-1, "", ""); }
	 * 
	 * public FileDescriptor(int id, String description) { this(id, description, ""); }
	 * 
	 * public FileDescriptor(int id, String description, String href) { this.id = id;
	 * this.description = description; this.href = href; }
	 * 
	 * public String getDescription() { return description; }
	 * 
	 * public void setDescription(String description) { this.description = description; }
	 * 
	 * public int getId() { return id; }
	 * 
	 * public void setId(int id) { this.id = id; }
	 * 
	 * public String getHref() { return href; }
	 * 
	 * public void setHref(String href) { this.href = href; }
	 *  }
	 * 
	 * public class LinkDescriptor {
	 * 
	 * private int id;
	 * 
	 * private String description;
	 * 
	 * private String href;
	 * 
	 * public LinkDescriptor(int id, String href, String description) { this.id = id;
	 * this.description = description; this.href = href; }
	 * 
	 * public String getDescription() { return description; }
	 * 
	 * public void setDescription(String description) { this.description = description; }
	 * 
	 * public int getId() { return id; }
	 * 
	 * public void setId(int id) { this.id = id; }
	 * 
	 * public String getHref() { return href; }
	 * 
	 * public void setHref(String href) { this.href = href; }
	 *  }
	 */
	public void removeWebComponent(int componentId) {
		// deve rimuovere la componente con l'id passato dalla pagina corrente

		/*
		 * FIXME DISATTIVATO Set cps = getBean().getEntity().getAPageComponentses(); for (Iterator
		 * iter = cps.iterator(); iter.hasNext();) { HAPageComponents element = (HAPageComponents)
		 * iter.next(); HWebComponent comp = element.getWebComponent(); if
		 * (comp.getWcId().equals(componentId)) { cps.remove(element); return; } }
		 */
	}

	
	public List<WebComponent> getImagesresources() {
		return imagesresources;
	}

	public void setImagesresources(List<WebComponent> imagesresources) {
		this.imagesresources = imagesresources;
	}

	public List<APageComponents> getFiles() {
		return files;
	}

	public void setFiles(List<APageComponents> files) {
		this.files = files;
	}

	public List<APageComponents> getImages() {
		return images;
	}

	public void setImages(List<APageComponents> images) {
		this.images = images;
	}

	public List<APageComponentsType> getAPageComponentsTypeSL() {
		return aPageComponentsTypeSL;
	}

	public void setAPageComponentsTypeSL(List<APageComponentsType> pageComponentsTypeSL) {
		aPageComponentsTypeSL = pageComponentsTypeSL;
	}

	
}
