package com.rightprice.auth.util;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.rightprice.auth.model.EmailBeanForRightprice;




public class MailTrigger {

	
	public static void sendMail(EmailBeanForRightprice temp) throws IOException {
		/*
		 * DEVELOPMENT CONFIGURATION 
		 * host=10.119.132.34 
		 * userName=syndev
		 * password=Dell123$
		 * 
		 * PRODUCTION CONFIGURATION 
		 * host=10.128.32.187
		 * � userName=adminportal
		 * password=Dec_0207
		 */
		
		/*PropertyReader prop = new PropertyReader();
		ArrayList<String> key = new ArrayList<>();
		key.add("host");
		key.add("userName");
		key.add("password");
		HashMap<String, String> result = prop.getPropValues(
				"emailConfig.properties", key);*/
		AppLoger.APPLOGGER.info("inside mailtrigger class start --------------------");
		String[] toArray = temp.getTo();
		String[] ccArray = temp.getCc();
		//String from = temp.getFrom();
	//String from = "Rightprice@eviden.com";
	//	String from = "Rightprice@syntelinc.com";
		//String from = "Syndev@TATOSMAIL.COM";
		String from = "rightprice@eviden.com";
		
		Properties properties = System.getProperties();
		/*properties.setProperty("mail.smtp.host", "10.128.10.110");*/
		//properties.setProperty("mail.smtp.host", "smtp.git.myatos.net");
		properties.setProperty("mail.smtp.host", "smtp.corp.my-it-infra.net");
//		properties.put("mail.smtp.auth", "true");

		// Get the default Session object.
/*		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("adminportal","Dec_0207");
					}
				});*/
		Session session = Session.getInstance(properties,null);

		try {
            MimeMessage message = new MimeMessage(session);

		       message.setFrom(new InternetAddress(from));
		       AppLoger.APPLOGGER.info("from :"+ from);
		       
		       InternetAddress[] addressTo = new InternetAddress[toArray.length];
		       for (int i = 0; i < toArray.length; i++)
		       {
		             AppLoger.APPLOGGER.info("to["+ i + "] : "+ toArray[i]);
		             addressTo[i] = new InternetAddress(toArray[i]);
		       }
		       AppLoger.APPLOGGER.info("addressTo :" + addressTo);
		       
		       InternetAddress[] addressCc = new InternetAddress[ccArray.length];
		       for (int i = 0; i < ccArray.length; i++)
		       {
		             AppLoger.APPLOGGER.info("cc["+ i + "] : "+ ccArray[i]);
		             addressCc[i] = new InternetAddress(ccArray[i]);
		       }
		       AppLoger.APPLOGGER.info("addressCc :" + addressCc);
		       
		       message.addRecipients(Message.RecipientType.TO, addressTo);
		       message.addRecipients(Message.RecipientType.CC, addressCc);
		       
		       String subject=temp.getSubject();
		       message.setSubject(subject);
		       AppLoger.APPLOGGER.info("subject :"+subject);
		
		       String body=temp.getMailContent();
		       AppLoger.APPLOGGER.info("Mail Content:"+body);
		       message.setContent(body,"text/html");
		
		       // Send message
		       Transport.send(message);
		       AppLoger.APPLOGGER.info("Sent message successfully...");
		}
		catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	   }
}
