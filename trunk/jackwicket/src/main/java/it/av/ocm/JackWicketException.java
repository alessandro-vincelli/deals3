package it.av.ocm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JackWicketException extends Exception {

    private static final long serialVersionUID = -2549208951901428566L;
    private static Log log = LogFactory.getLog(JackWicketException.class);

    public JackWicketException() {
    }

    public JackWicketException(String s) {
        super(s);
        log.error(s);
    }

    public JackWicketException(String s, Throwable throwable) {
        super(s, throwable);
        log.error(s, throwable);
    }

    public JackWicketException(Throwable throwable) {
        super(throwable);
        log.error("Error on JackWicket", throwable);
    }
}