package org.deals.framework.core.util;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class DealsMailSender {
	private MailSender mailSender;
	private SimpleMailMessage templateMessage;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}

	public void send(MailMessage fm) throws MailException{
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
		msg.setSubject(fm.getTitle());
		//msg.setTo(toSend.getAddress());
		msg.setText(fm.getMessage());
		this.mailSender.send(msg);
	}
}
