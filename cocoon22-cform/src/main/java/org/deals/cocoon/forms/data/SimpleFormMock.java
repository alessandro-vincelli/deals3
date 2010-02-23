package org.deals.cocoon.forms.data;

import java.util.Date;

/**
 * @author Alessandro Vincelli
 * @email a.vincelli@gmail.com Created on Aug 24, 2008
 */
public class SimpleFormMock {

	private String title = "Simple title from mock bean";
	private String header = "Header text from mock bean";
	private Date today = new Date();

	/**
	 * @return the today
	 */
	public Date getToday() {
		return today;
	}

	/**
	 * @param today
	 *            the today to set
	 */
	public void setToday(Date today) {
		this.today = today;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @param header
	 *            the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}
}
