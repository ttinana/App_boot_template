/**
 * 
 */
package com.example.mail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;

import com.example.dbean.DemoBean;

/**
 * @author tijana.pavicic
 *
 */
@Configuration
public class MailConfig {
	private static final Log log = LogFactory.getLog(SmtpMailSender.class);
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Bean
	public DemoBean demoBean(){
		log.info(" |INFO| DemoBean created");
		return new DemoBean();
	}
	 
	@Bean
	//@Profile("dev")
	/*@ConditionalOnProperty(name="spring.mail.host",
							value="foo",
							matchIfMissing=true)*/
	public MailSender mockMailSender(){
		return new MockMailSender();
	}
	
	@Bean
	//@Profile("!dev")
	@ConditionalOnProperty(name="${spring.mail.host}")
	public MailSender smtpMailSender(JavaMailSender javaMailSender){
		demoBean().foo();
		SmtpMailSender smtpMailSender= new SmtpMailSender();
		smtpMailSender.setJavaMailSender(javaMailSender);
		return smtpMailSender;
	}
	
	
	public MailSender smtpMailSenderOLDway(JavaMailSender javaMailSender, DemoBean demoBean){
		demoBean.foo();
		SmtpMailSender smtpMailSender= new SmtpMailSender();
		smtpMailSender.setJavaMailSender(javaMailSender);
		return smtpMailSender;
	}

}
