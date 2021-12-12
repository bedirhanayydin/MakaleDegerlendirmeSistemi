package Model;


import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class Mail {
	
	private static  String MailBaslik;
	private static String MailIcerik;
	
	public Mail(String MailBaslik,String MailIcerik)
	{
		Mail.MailBaslik=MailBaslik;
		Mail.MailIcerik=MailIcerik;
	}
	
	public static void sendMail(String recepient ) 	throws Exception
	{
		Properties properties=new Properties();
		 	
		properties.put("mail.smtp.host","smtp.gmail.com");//host atama
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "587");//port tanýmlama
		
		
		String myAccountEmail="example@gmail.com";
		String password="password";
		
		Session session= Session.getInstance(properties, new javax.mail.Authenticator() {
			
			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {

				return new javax.mail.PasswordAuthentication(myAccountEmail, password);
				
			}
			
		});
		Message message =prepareMessage(session,myAccountEmail,recepient);
		Transport.send(message);
		
	}

	private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
		try {
			
			Message message=new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject(MailBaslik);
			message.setText(MailIcerik);
			return message;
		}
		catch(Exception e)
		{
			Logger.getLogger(Mail.class.getName()).log(Level.SEVERE,null,e);
		}
		return null;
	}

}
