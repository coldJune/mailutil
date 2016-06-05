package com.jun.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class Authenticat extends Authenticator{
	String user;
	String pass;
	public Authenticat(String user,String pass) {
		// TODO Auto-generated constructor stub
		this. user=user;
		this.pass = pass;
		
	}
	
	
	protected PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(user, pass);
	}
}

public class SendMail {
	private static final String HOST="smtp.163.com";
	private static final String PROTOCOL="smtp";
	private static final int PORT=25;
	static final String FROM="dbcompetition@163.com";
 static final String  PSW="123456a";
	


	private static Session getSession(){
		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);
		//props.put("mail.store.protocol", PROTOCOL);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", PORT);
	//	props.put("mail.smtp.starttls.enable",true); 
	//	props.put("mail.smtp.EnableSSL.enable",true);
		props.setProperty("mail.transport.protocol", PROTOCOL);

	//	props.setProperty("mail.smtp.socketFactory.fallback", "false");   
	
			

		Authenticat authenticator = new Authenticat(FROM,PSW) ;
		Session session = Session.getDefaultInstance(props,authenticator);
		return session;
	}
	public static void send(String toMail,String content){
		Session session = getSession();
		Message msg =null;
		try {
			 msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(FROM));
			Address[] address ={ new InternetAddress(toMail)};
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject("账号激活邮件");
			msg.setSentDate(new Date());
			msg.setContent(content, "text/html;charset=utf-8");
			Transport.send(msg);
		} catch (MessagingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
