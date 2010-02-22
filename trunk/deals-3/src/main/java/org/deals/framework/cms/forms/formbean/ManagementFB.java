package org.deals.framework.cms.forms.formbean;

import java.util.List;

import org.deals.framework.bean.APagePage;
import org.deals.framework.bean.WebPage;

public class ManagementFB {

	private List<APagePage> items;

	private List<APagePage> categories;

	private List<WebPage> sections;

	private Integer sectionSelected;

	private Integer categorySelected;

	private Integer itemSelected;

	public List<APagePage> getCategories() {
		return categories;
	}

	public List<APagePage> getItems() {
		return items;
	}

	public List<WebPage> getSections() {
		return sections;
	}

	public void setCategories(List<APagePage> categories) {
		this.categories = categories;
	}

	public void setItems(List<APagePage> items) {
		this.items = items;
	}

	public void setSections(List<WebPage> sections) {
		this.sections = sections;
	}

	public Integer getCategorySelected() {
		return categorySelected;
	}

	public void setCategorySelected(Integer categorySelected) {
		this.categorySelected = categorySelected;
	}

	public Integer getItemSelected() {
		return itemSelected;
	}

	public void setItemSelected(Integer itemSelected) {
		this.itemSelected = itemSelected;
	}

	public Integer getSectionSelected() {
		return sectionSelected;
	}

	public void setSectionSelected(Integer sectionSelected) {
		this.sectionSelected = sectionSelected;
	}
	
	

	/*
	 * private Vector<Items> items = new Vector<Items>(); private Vector<Categories> categories =
	 * new Vector<Categories>(); private Vector<Sections> sections = new Vector<Sections>();
	 * 
	 * 
	 * public boolean addCategory(Categories arg0) { return categories.add(arg0); }
	 * 
	 * public boolean addItem(Items arg0) { return items.add(arg0); }
	 * 
	 * public boolean addItem(Item page, WebPage parentId) { return items.add(new Items(page,
	 * parentId)); }
	 * 
	 * public boolean addSection(Sections arg0) { return sections.add(arg0); }
	 * 
	 * public Vector<Categories> getCategories() { return categories; }
	 * 
	 * public Vector<Items> getItems() { return items; }
	 * 
	 * public Vector<Sections> getSections() { return sections; }
	 * 
	 * public void setCategories(Vector<Categories> categories) { this.categories = categories; }
	 * 
	 * public void setItems(Vector<Items> items) { this.items = items; }
	 * 
	 * public void setSections(Vector<Sections> sections) { this.sections = sections; }
	 * 
	 * public interface ManagementInternalBean { public int getId(); }
	 */

	// TODO sti tre affari sotto sono proprio identici, allora si può
	// spostare la logica nel ManagementInternalBean e farli solo ereditare, al
	// massimo definire
	// l'offset per il taglio del titolo
	/**
	 * Classe interna che rappresenta la struttura delle item visualizzate ad elenco nel Managemente
	 */
	// public class Items implements ManagementInternalBean {
	//		
	// private Item item;
	// private WebPage parent;
	//		
	// public Items(Item item, WebPage parent) {
	// this.item = item;
	// this.parent = parent;
	// }
	//		
	// public int getParentId() {
	// return parent.getWpId();
	// }
	//		
	// public Item getItem() {
	// return item;
	// }
	//				
	// public Date getDate() {
	// return item.getWpLastModified();
	// }
	//		
	// public Date getExpiration() {
	// return item.getWpExpire();
	// }
	//		
	// public int getId() {
	// return item.getWpId();
	// }
	//		
	// public String getStatus() {
	// if (item.getPageState()!=null)
	// return item.getPageState().getPsName();
	// else
	// return "";
	// }
	//		
	// public String getTitle() {
	// return TextUtils.intelligentSubstring(item.getWpContentTitle(),0,80);
	// }
	//		
	// public boolean isTop() {
	// return (getWeight()==1);
	// }
	//		
	// public int getWeight() {
	// return item.getWeight(parent);
	// }
	//		
	// public void setDate(Date date) {
	// item.setWpLastModified(date);
	// }
	//		
	// public void setExpiration(Date expiration) {
	// item.setWpExpire(expiration);
	// }
	//		
	// /**
	// * @deprecated
	// * @param id
	// */
	// public void setId(int id) {
	// //item.setId(id);
	// }
	//		
	// public void setStatus(String status) {
	// item.setPageState(PageState.getStateByName(status));
	// }
	//		
	// public void setTitle(String title) {
	// item.setWpContentTitle(title);
	// }
	//		
	// public void setWeight(int weight) {
	// item.setWpWeight(weight);
	// }
	//
	//
	// }
	//
	//
	// /**
	// * Classe interna che rappresenta la struttura delle categories
	// visualizzate ad elenco nel Managemente
	// **/
	//
	// public class Categories implements ManagementInternalBean {
	//		
	// private Category category;
	// private WebPage parent;
	//		
	// public Categories(Category cat, WebPage parent) {
	// this.category = cat;
	// this.parent = parent;
	// }
	//		
	//		
	// public int getParentId() {
	// return parent.getWpId();
	// }
	//		
	//		
	// public Category getCategory() {
	// return category;
	// }
	//		
	// public Date getDate() {
	// return category.getWpCreated();
	// }
	//		
	// public Date getExpiration() {
	// return category.getWpExpire();
	// }
	//		
	// public int getId() {
	// return category.getWpId();
	// }
	//		
	// public String getStatus() {
	// return category.getPageState().getPsName();
	// }
	//		
	// public String getTitle() {
	// return TextUtils.intelligentSubstring(category.getWpContentTitle(),0,50);
	// }
	//		
	// public boolean isTop() {
	// return (getWeight()==1);
	// }
	//		
	// public int getWeight() {
	// return category.getWeight(parent);
	// }
	//		
	// public void setDate(Date date) {
	// category.setWpCreated(date);
	// }
	//		
	// public void setExpiration(Date expiration) {
	// category.setWpExpire(expiration);
	// }
	//		
	// /**
	// * @deprecated
	// * @param id
	// */
	// public void setId(int id) {
	// //category.setId(id);
	// }
	//		
	// public void setStatus(String status) {
	// category.setPageState(PageState.getStateByName(status));
	// }
	//		
	// public void setTitle(String title) {
	// category.setWpContentTitle(title);
	// }
	//		
	// public void setWeight(int weight) {
	// category.setWpWeight(weight);
	// }
	//
	// }
	//
	// /**
	// *
	// * @author Alessandro Vincelli
	// * Classe interna che rappresenta la struttura delle item visualizzate ad
	// elenco nel Managemente
	// *
	// */
	// public class Sections implements ManagementInternalBean {
	//		
	// private Section section;
	//		
	// public Sections(Section sec) {
	// this.section = sec;
	// }
	//		
	// public Section getSection() {
	// return section;
	// }
	//		
	// public Date getDate() {
	// return section.getWpCreated();
	// }
	// public Date getExpiration() {
	// return section.getWpExpire();
	// }
	// public int getId() {
	// return section.getWpId();
	// }
	// public String getStatus() {
	// return section.getPageState().getPsName();
	// }
	// public String getTitle() {
	// return TextUtils.intelligentSubstring(section.getWpContentTitle(),0,50);
	// }
	//		
	// public boolean isTop() {
	// return (getWeight()==1);
	// }
	//		
	// public int getWeight() {
	// return section.getWpWeight();
	// }
	//		
	// public void setDate(Date date) {
	// section.setWpCreated(date);
	// }
	//		
	// public void setExpiration(Date expiration) {
	// section.setWpExpire(expiration);
	// }
	//		
	// /**
	// * @deprecated
	// * @param id
	// */
	// public void setId(int id) {
	// //section.setId(id);
	// }
	//		
	// public void setStatus(String status) {
	// section.setPageState(PageState.getStateByName(status));
	// }
	//		
	// public void setTitle(String title) {
	// section.setWpContentTitle(title);
	// }
	//		
	// public void setWeight(int weight) {
	// section.setWpWeight(weight);
	// }
	// }
}
