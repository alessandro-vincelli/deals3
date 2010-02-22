package org.deals.framework.core.util;

public class MailMessage {

	private String title;
	private String code;
	private String message;
	
	public MailMessage(String title, String code, String message) {
		super();
		this.title = title;
		this.code = code;
		this.message = message;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
