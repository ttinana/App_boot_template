/**
 * 
 */
package com.example.controllers;

import java.io.File;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mail.MailSender;
import com.example.mail.SmtpMailSender;

/**
 * @author tijana.pavicic
 *
 */
@RestController
public class MailController {
	
	private static final Log log = LogFactory.getLog(SmtpMailSender.class);
	
	@Value("${spring.application.name}")
	private String appName;

	private MailSender mailSender;

	@Autowired
	public MailController(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	@RequestMapping("/mail")
	public String sendMail() {
		try {
			mailSender.send("ttinana@gmail.com", "Some subject", "the content");
		} catch (MessagingException e) {			
			log.error(" |ERROR| Mail not sent");
		}
		// final File f = new
		// File(MailController.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		// String classpath = System.getProperty("java.class.path");
		// String[] classpathEntries = classpath.split(File.pathSeparator);

		String path = new File(".").getAbsolutePath();
		return "Mail sent to Goran.  " + path +" from " + appName;
	}
}
