package com.flow.action.network;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.flow.action.AbstractAction;

public class EmailSenderAction extends AbstractAction{
	private Properties props;
	private MimeMessage mimeMessage;
	private Session session;
	private String host;
	private String user;
	private String pwd;
	private String charset = "UTF-8";
	private String contentType = "text/html;charset=UTF-8";
	private Logger logger = Logger.getLogger(EmailSenderAction.class.toString());
	
	public EmailSenderAction(String name, String host, String user, String pwd) {
		super(name, false);
		this.host = host;
		this.user = user;
		this.pwd = pwd;
		initialize();
	}
	
	private void initialize() {
		props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		
		session = Session.getInstance(props);
		
		mimeMessage = new MimeMessage(session);
	}

	@Override
	public Object execute() throws Exception {
		try {
			mimeMessage.setSentDate(new Date());
			mimeMessage.saveChanges();
			
			Transport transport = session.getTransport("smtp");
			transport.connect(user, pwd);
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();
			logger.info("Send mail successfully.");
		} catch (Exception e) {
			logger.severe("Failed to send email. Message=" + e.getMessage());
			throw e;
		}
		return null;
	}
	
	public void setSubject(String subject) throws MessagingException {
		mimeMessage.setSubject(subject);
	}
	
	public void setFrom(String mailAdd, String alias) throws Exception {
		mimeMessage.setFrom(new InternetAddress(mailAdd, alias, charset));
	}
	
	public void addRecipient(String mailAddr, RecipientType type) throws UnsupportedEncodingException, MessagingException {
		mimeMessage.addRecipient(type, new InternetAddress(mailAddr));
	}
	
	public void addContent(String content) throws MessagingException {
		mimeMessage.setContent(content, contentType);
	}
	
	public void setFrom(String mailAddr) throws MessagingException {
		mimeMessage.setFrom(mailAddr);
	}
	
	public void setDebug(boolean debug) {
		session.setDebug(debug);
	}
}
