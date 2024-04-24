package com.ibm.notifier;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Simple e-mail Client
 */
public class EmailClient {

    private Properties properties;
    private static final Logger logger = LoggerFactory.getLogger(EmailClient.class);

    public EmailClient(Properties properties){
        this.properties = properties;
    }

    public void send(EmailMessage emailMessage) throws EmailException {
        Email mail = new SimpleEmail();
        mail.addTo(emailMessage.getTo());
        mail.setFrom(emailMessage.getFrom());
        mail.setSubject(emailMessage.getSubject());
        mail.setMsg(emailMessage.getBody());
        mail.setAuthenticator(new DefaultAuthenticator(
                properties.getProperty("username"),
                properties.getProperty("password")));
        mail.setHostName(properties.getProperty("server"));
        mail.setSmtpPort(Integer.parseInt(properties.getProperty("port")));
        mail.setSSLOnConnect(true);
        logger.info(mail.send());

    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("server", "smtp.gmail.com");
        properties.setProperty("port","587");
        properties.setProperty("username","***");
        properties.setProperty("password","***");
        try {
            new EmailClient(properties).send(new EmailMessage.
                    Builder()
                    .to("xxx@xxx.com")
                    .from("xxx@xxx.com")
                    .subject("test message")
                    .body("body")
                    .build()
            );
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
