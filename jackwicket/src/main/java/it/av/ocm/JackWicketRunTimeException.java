package it.av.ocm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JackWicketRunTimeException extends RuntimeException {

    private static final long serialVersionUID = 6702704981783539685L;
    private static Log log = LogFactory.getLog(JackWicketRunTimeException.class);

    public JackWicketRunTimeException() {
    }

    public JackWicketRunTimeException(String s) {
        super(s);
        log.error(s);
    }

    public JackWicketRunTimeException(String s, Throwable throwable) {
        super(s, throwable);
        log.error(s, throwable);
    }

    public JackWicketRunTimeException(Throwable throwable) {
        super(throwable);
        log.error("Error on Jack Wicket", throwable);
    }
}
