package com.wangzhu.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class SendMailUtils {

	@Autowired
	private MailSender mailSender;

	@Autowired
	private SimpleMailMessage mailMessage;

	public void sendTxtMail() {
		mailMessage.setSubject("÷˜Ã‚");
		mailMessage.setFrom("xiaoxian1369@126.com");
		mailMessage.setTo("xiaoxian1369@126.com");
		mailMessage.setText("≤‚ ‘Spring Email");
		mailSender.send(mailMessage);
	}
}
