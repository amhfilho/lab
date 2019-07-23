package com.ibm.notifier;

import org.apache.commons.mail.EmailException;
import org.junit.Before;
import org.junit.Test;
import org.jvnet.mock_javamail.Mailbox;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EmailClientTest {

    @Before
    public void setup(){
        Mailbox.clearAll();
    }

    @Test
    public void shouldCreateEmailMessage(){
        EmailMessage emailMessage = new EmailMessage.Builder()
                .from("amhfilho@gmail.com")
                .to("amhfilho@gmail.com")
                .subject("Test Subject")
                .body("Test Body")
                .build();

        assertEquals("amhfilho@gmail.com", emailMessage.getFrom());
        assertEquals("amhfilho@gmail.com", emailMessage.getTo());
        assertEquals("Test Subject",emailMessage.getSubject());
        assertEquals("Test Body",emailMessage.getBody());
    }

    @Test
    public void shouldSendEmail() throws EmailException, MessagingException, IOException {
        EmailMessage emailMessage = new EmailMessage.Builder()
                .from("amhfilho@gmail.com")
                .to("amhfilho@gmail.com")
                .subject("TestSubject")
                .body("Test Body")
                .build();


        EmailClient client = new EmailClient(properties());
        client.send(emailMessage);

        List<Message> inbox = Mailbox.get("amhfilho@gmail.com");

        assertTrue(inbox.size() == 1);
        assertEquals("TestSubject", inbox.get(0).getSubject());
        assertEquals("Test Body", inbox.get(0).getContent());
    }

    private Properties properties(){
        Properties properties = new Properties();
        properties.setProperty("server", "smtp.testserver");
        properties.setProperty("port","1");
        properties.setProperty("username","username@test.com");
        properties.setProperty("password","testpassword");
        return properties;
    }
}
