package com.example.mail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("mock")
public class MockMailSender implements MailSender {
	private static final Log log = LogFactory.getLog(SmtpMailSender.class);

	@Override
	public void send(String to, String subject, String body) {
		log.info("Sending MOCK mail to " + to);
		log.info("Subject: " + subject);
		log.info("Body: " + body);
	}

}
