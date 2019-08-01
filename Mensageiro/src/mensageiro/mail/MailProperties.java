/*
 * MailProperties.java
 *
 * Created on 19 de Outubro de 2007, 00:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mensageiro.mail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 *
 * @author antonioh
 */
public class MailProperties {
    private static final String NOME_ARQUIVO = "c:\\mensageiro\\mail.properties";
    private static final String NOME_BUNDLE = "mensageiro.service.mail";
    private static Properties properties = null;
    private static MailProperties instance;
    private static ResourceBundle resourceConfig = null;
//

    /** Creates a new instance of MailProperties */
    private MailProperties() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(NOME_ARQUIVO));
        //resourceConfig = java.util.ResourceBundle.getBundle(NOME_BUNDLE);
    }
    
    public static MailProperties getInstance() throws IOException {
        if (instance == null) {
            instance = new MailProperties();
        }
        return instance;
    }
    
    public String getPropriedade(String nomeProp) {
        //return resourceConfig.getString(nomeProp);
        return properties.getProperty(nomeProp);
    }
    
}
