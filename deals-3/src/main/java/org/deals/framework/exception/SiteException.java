package org.deals.framework.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SiteException extends Exception{
	/**
	 * @author Alessandro Vincelli
	 * @email a.vincelli@gmail.com
	 * Created on 10 11, 2008
	 */
	private static final long serialVersionUID = 8848899817086827190L;
	private static Log log = LogFactory.getLog(SiteException.class);

	public SiteException() {
	}

	public SiteException(String s) {
		super(s);
		log.error(s);
	}

	public SiteException(String s, Throwable throwable) {
		super(s, throwable);
		log.error(s, throwable);
	}

	public SiteException(Throwable throwable) {
		super(throwable);
		log.error("Error on Site", throwable);
	}
}
