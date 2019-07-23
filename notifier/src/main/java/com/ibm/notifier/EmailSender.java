package com.ibm.notifier;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Hello world!
 *
 */
public class EmailSender {

	public static void main(String[] args) throws IOException {
		new EmailSender().sendMessage("Sametime from: " , "Sametime");
	}

	public void sendMessage(String text, String subject) throws IOException {
		final PropertyReader reader = PropertyReader.load("notifier.properties");
		final String username = reader.get("username");
		final String password = reader.get("password");
		Properties mailServerProperties;
		Session getMailSession;
		MimeMessage generateMailMessage;

		// Step1
		// System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		// System.out.println("Mail Server Properties have been setup successfully..");

		// Step2
		// System.out.println("\n\n 2nd ===> get Mail Session..");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		try {
			generateMailMessage.addRecipient(Message.RecipientType.TO,
					new InternetAddress(reader.get("targetemail")));

			// generateMailMessage.addRecipient(Message.RecipientType.CC, new
			// InternetAddress("test2@crunchify.com"));
			generateMailMessage.setSubject(subject);
			generateMailMessage.setFrom(new InternetAddress(reader.get("sourceemail")));
			generateMailMessage.setContent(text, "text/html");
			// System.out.println("Mail Session has been created successfully..");

			// Step3
			// System.out.println("\n\n 3rd ===> Get Session and Send mail");
			Transport transport = getMailSession.getTransport("smtp");

			// Enter your correct gmail UserID and Password
			// if you have 2FA enabled then provide App Specific Password
			transport.connect("smtp.gmail.com", username, password);
			transport.sendMessage(generateMailMessage,
					generateMailMessage.getAllRecipients());
			transport.close();

			System.out.println(new Date().toString()
					+ " -- E-mail sent successfully");

		} catch (MessagingException e) {

			e.printStackTrace();
		}

	}
	
	
}
