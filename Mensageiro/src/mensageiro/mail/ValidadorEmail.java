/*
 * ValidadorEmail.java
 *
 * Created on 19 de Outubro de 2007, 13:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mensageiro.mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Valida endereço de e-mail
 * @author antonioh
 */
public class ValidadorEmail {
    
    private ValidadorEmail() {}
    
    public static boolean isValid(String email) {
                
        //Set the email pattern string
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        
        //Match the given string with the pattern
        Matcher m = p.matcher(email);
        
        //check whether match is found
        boolean matchFound = m.matches();
        
        return matchFound;
    }
}
