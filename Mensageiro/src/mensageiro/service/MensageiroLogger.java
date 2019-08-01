/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mensageiro.service;

import java.io.IOException;
import mensageiro.mail.MailProperties;

/**
 *
 * @author Antonio
 */
public class MensageiroLogger {
    MailProperties props;
    
    private static MensageiroLogger instance;
    
    public static MensageiroLogger getInstance() {
        if (instance==null){
            instance = new MensageiroLogger();
        }
        return instance;
    }
    
    private MensageiroLogger() {
        
    }
    
    private void loadProperties() throws IOException {
        props = MailProperties.getInstance();
    }
    
    

}
