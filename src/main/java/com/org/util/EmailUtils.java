package com.org.util;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	@Autowired
	private JavaMailSender mailSender;
	public boolean sendEmail(String subject, String body, String to, File f) {

		try {
			MimeMessage minMsg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(minMsg, true);

			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setTo("xyz@gmail.com");
			// File f= new File();

			helper.addAttachment("Plans-Info", f);

			mailSender.send(minMsg);
		} catch (Exception e) {

		}

		return true;
	}

}
