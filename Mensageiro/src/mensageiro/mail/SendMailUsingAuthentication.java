package mensageiro.mail;

import com.sun.mail.smtp.SMTPAddressFailedException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMailUsingAuthentication {
    
    private MailProperties propriedades;
    
    public SendMailUsingAuthentication() {
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
        
        // Enviando a mensagem
        try {
            msg.setFrom(addressFrom);
            msg.setRecipients(Message.RecipientType.TO, addressTo);            
            // Setting the Subject and Content Type
            msg.setSubject(propriedades.getPropriedade("SUBJECT"));
            msg.setSentDate(new Date());
            LeitorArquivoMensagem leitor = new LeitorArquivoMensagem();
            String html = leitor.getConteudoArquivo();
            msg.setContent(html, "text/plain");
            Transport.send(msg);
            
        } catch (SMTPAddressFailedException sx){
            throw MensageiroException.wrap(new RuntimeException("E-mail inválido ou inexistente"));
            
        } catch (MessagingException ex) {
            throw MensageiroException.wrap(ex);
        }
           
    }
    
    
    /**
     * SimpleAuthenticator is used to do simple authentication
     * when the SMTP server requires it.
     */
    private class SMTPAuthenticator extends javax.mail.Authenticator {
        
        public PasswordAuthentication getPasswordAuthentication() {
            String username = propriedades.getPropriedade("SMTP_AUTH_USER");
            String password = propriedades.getPropriedade("SMTP_AUTH_PWD");
            return new PasswordAuthentication(username, password);
        }
    }
    
}


