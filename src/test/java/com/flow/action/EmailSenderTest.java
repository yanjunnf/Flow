package com.flow.action;

import static org.junit.Assert.assertEquals;

import javax.mail.internet.MimeMessage;

import org.junit.Test;

import com.flow.action.network.EmailSenderAction;

public class EmailSenderTest {
	@Test
	public void testEmialSender() {
		try {
			EmailSenderAction sender = new EmailSenderAction("Email Sender Action", "smtp.126.com", "yanjunnf@126.com", "password_need_change");
			sender.setSubject("Test email");
			sender.setFrom("yanjunnf@126.com");
			sender.addRecipient("yanjunnf@hotmail.com", MimeMessage.RecipientType.TO);
			sender.addContent("Test content");
			sender.setDebug(true);
			sender.execute();
		} catch (Exception e) {
			assertEquals(true, false);
		}
	}
}
