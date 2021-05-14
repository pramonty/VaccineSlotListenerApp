package com.monty.services.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monty.app.Application;
import com.monty.services.MailSender;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Component
public class MailSenderImpl implements MailSender {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(MailSenderImpl.class);
	
	@Autowired
	SendGrid sg;

	@Override
	public void sendMail(String subject,String body) {
		// TODO Auto-generated method stub
		Email from = new Email("pramonty@gmail.com");
		 
		 Email to = new Email("pramonty@gmail.com");
		 Content content = new Content("text/plain", body);
		 Mail mail = new Mail(from, subject, to, content);
		 
		 Request request = new Request();
		    try {
		      request.setMethod(Method.POST);
		      request.setEndpoint("mail/send");
		      request.setBody(mail.build());
		      Response response = sg.api(request);
		      LOGGER.info("Status code: "+response.getStatusCode());
		      LOGGER.info("Response body: "+response.getBody());
		      LOGGER.info("Response header: "+response.getHeaders());
		    } catch (IOException ex) {
		      LOGGER.error("Error while sending email",ex);
		    }
		
	}
	
	

}
