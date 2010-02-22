package org.deals.framework.search;

import java.util.HashMap;

public class SearchResult  {

	private String title;

	private String href;

	private String result;

	private double score;

	private String[] sections;

	private String[] categories;

	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	public String[] getSections() {
		return sections;
	}

	public void setSections(String[] sections) {
		this.sections = sections;
	}

	public String toXML() {

		HashMap<String, Integer> secs = new HashMap<String, Integer>();
		HashMap<String, Integer> cats = new HashMap<String, Integer>();

		StringBuffer xml = new StringBuffer();
		xml.append("<search-result>");
		xml.append("<title>" + title + "</title>");
		xml.append("<href>" + href + "</href>");
		xml.append("<result>" + result + "</result>");
		xml.append("<published>" + date + "</published>");
		xml.append("<score>" + Math.round(score) + "</score>");
		xml.append("<sections>");
		for (int i = 0; i < sections.length; i++) {
			if (secs.get(sections[i]) == null) {
				xml.append("<section>" + sections[i] + "</section>");
				secs.put(sections[i], 1);
			}
		}
		xml.append("</sections>");
		xml.append("<categories>");
		for (int i = 0; i < categories.length; i++) {
			if (cats.get(categories[i]) == null) {
				xml.append("<category>" + categories[i] + "</category>");
				cats.put(categories[i], 1);
			}
		}
		xml.append("</categories>");
		xml.append("</search-result>");
		return xml.toString();
	}

}
