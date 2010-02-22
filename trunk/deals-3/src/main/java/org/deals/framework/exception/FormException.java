package org.deals.framework.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FormException extends RuntimeException {
	/**
	 * @author Alessandro Vincelli
	 * @email a.vincelli@gmail.com
	 */
	private static final long serialVersionUID = -7354223946460313258L;
	private static Log log = LogFactory.getLog(FormException.class);

	public FormException() {
	}

	public FormException(String s) {
		super(s);
		log.error(s);
	}

	public FormException(String s, Throwable throwable) {
		super(s, throwable);
		log.error(s, throwable);
	}

	public FormException(Throwable throwable) {
		super(throwable);
		log.error("Error on Form", throwable);
	}
}
