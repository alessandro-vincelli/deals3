/*
 * Created on Nov 14, 2006
 *
 */
package org.deals.framework.cms.forms.eh;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.mail.internet.InternetAddress;

import org.apache.cocoon.forms.formmodel.Submit;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;
import org.deals.framework.cms.forms.eh.abstracts.AbstractContactsEH;
import org.deals.framework.cms.forms.wrapper.ContactsFW;

public class ContactsEH extends AbstractContactsEH {
	Logger log = Logger.getLogger(getClass());

	@Override
	public void contactsubmitOnAction(ContactsFW f, Submit source) {
		super.contactsubmitOnAction(f, source);
		SimpleEmail email = new SimpleEmail();
		email.setHostName("mail.myserver.com");

		try {
			log.debug("__________________  invio email ");
			email.setDebug(true);
			email.setHostName("localhost");
			ArrayList<InternetAddress> to = new ArrayList<InternetAddress>();
			to.add(new InternetAddress("av@alessandro.vincelli.name", "Alessandro Vincelli"));

			email.setTo(to);
			email.setFrom(f.getEmailValue(), f.getFirstNameValue() + " " + f.getLastNameValue());
			email.setSubject("[www.consiag.it] RICHIESTA INFOMAZIONI");
			String message = ("Mittente:       " + f.getFirstNameValue() + " " + f.getLastNameValue() + "\n");
			message += "Mittente:       " + f.getFirstNameValue() + " " + f.getLastNameValue() + "\n";
			message += "Codice cliente: " + f.getCodeValue() + "\n";
			message += "Email:          " + f.getEmailValue() + "\n";
			message += "Telefono:       " + f.getPhoneNumberValue() + "\n";
			message += "Richiesta:      " + f.getContentValue() + "\n";
			email.setMsg(message);
			email.send();
			log.debug("__________________  email inviata");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
