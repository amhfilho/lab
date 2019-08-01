/*
 * SendMailAuthAttach.java
 *
 * Created on 19 de Outubro de 2007, 14:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mensageiro.mail;

import com.sun.mail.smtp.SMTPAddressFailedException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author antonioh
 */
public class SendMailAuthAttach {
    private MailProperties propriedades;
    
    public SendMailAuthAttach() {
        try {
            propriedades = MailProperties.getInstance();
            
        } catch (IOException e) {
            throw MensageiroException.wrap(e);
        }
    }
    
    public void postMail( List<String> recipients) {
        boolean debug = false;
        
        //Set the host smtp address
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", propriedades.getPropriedade("SMTP_HOST_NAME"));
        props.put("mail.smtp.auth", "true");
        
        // Definindo o autenticador
        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getDefaultInstance(props, auth);
        
        session.setDebug(debug);
        
        // create a message
        Message msg = new MimeMessage(session);
        
        // definindo a origem
        InternetAddress addressFrom = null;
        try {
            addressFrom = new InternetAddress(propriedades.getPropriedade("FROM"),
                    propriedades.getPropriedade("PERSONAL_NAME"));
            
        } catch (UnsupportedEncodingException ex) {
            throw MensageiroException.wrap(ex);
        }
        
        // definindo os destinos
        InternetAddress[] addressTo = new InternetAddress[recipients.size()];
        
        for (int i = 0; i < recipients.size(); i++) {
            try {
                addressTo[i] = new InternetAddress(recipients.get(i));
                
            } catch (AddressException ex) {
                throw MensageiroException.wrap(ex);
            }
        }
        
        // Criando e enviando a mensagem
        try {
            //
            // This HTML mail have to 2 part, the BODY and the embedded image
            //
            MimeMultipart multipart = new MimeMultipart("related");
            LeitorArquivoMensagem leitor = new LeitorArquivoMensagem();
            String html = leitor.getConteudoArquivo();
            
            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(html, "text/html");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            
            // second part (the image)
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource
                    (propriedades.getPropriedade("IMAGE_FILE"));
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID","<image>");
            
            // add it
            multipart.addBodyPart(messageBodyPart);
            msg.setFrom(addressFrom);
            msg.setRecipients(Message.RecipientType.TO, addressTo);
            // Setting the Subject and Content Type
            msg.setSubject(propriedades.getPropriedade("SUBJECT"));
            msg.setSentDate(new Date());
            msg.setContent(multipart);
            Transport.send(msg);
            
        } catch (SMTPAddressFailedException sx){
            throw MensageiroException.wrap(new RuntimeException("E-mail inválido ou inexistente"));
            
        } catch (MessagingException ex) {
            throw MensageiroException.wrap(ex);
        }
        
    }
    
    private class SMTPAuthenticator extends javax.mail.Authenticator {
        
        public PasswordAuthentication getPasswordAuthentication() {
            String username = propriedades.getPropriedade("SMTP_AUTH_USER");
            String password = propriedades.getPropriedade("SMTP_AUTH_PWD");
            return new PasswordAuthentication(username, password);
        }
    }
}
