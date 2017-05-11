package service;

import java.io.Serializable;
import java.util.Properties;

import javax.mail.Message;
 
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void sendMail(String toMail ,String message)
	{
		String user="mondher25@gmail.com";
		String password="mondher250687";
		
		Properties props=System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com");	
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587");
		
	
	Session mailSession=Session.getDefaultInstance(props,new javax.mail.Authenticator(){
		protected PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(user,password);
		}
	});
	
	try {
		Message mailMessage=new MimeMessage(mailSession);
		mailMessage.setFrom(new InternetAddress(user));
		mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
		mailMessage.setSubject("Administration");
		mailMessage.setContent(message,"text/html");
		Transport.send(mailMessage);
		System.out.println("mail send ok !!!!!");
	
	} catch (Exception e) {
		System.out.println("erreur sending mail  !!!!!");
		e.printStackTrace();
	}
		
	
	}
	
	
	
	public static void main(String[] args){
		
		sendMail("mondher25@gmail.com", "test");		
	}
	

	
	
}
