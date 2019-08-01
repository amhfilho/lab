/*
 * LeitorArquivoMensagem.java
 *
 * Created on 19 de Outubro de 2007, 14:26
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mensageiro.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author antonioh
 */
public class LeitorArquivoMensagem {
    
    private MailProperties props;
    
    /** Creates a new instance of LeitorArquivoMensagem */
    public LeitorArquivoMensagem() {
        try {
            props = MailProperties.getInstance();
            
        } catch (IOException e) {
            throw MensageiroException.wrap(e);
        }
    }
    
    public String getConteudoArquivo() {
        File arquivo = new File(props.getPropriedade("HTML_FILE"));
        BufferedReader bfr = null;
        StringBuilder result = new StringBuilder();
        
        try {
            bfr = new BufferedReader(new FileReader(arquivo));
            String linha = null;
            
            while ((linha = bfr.readLine())!=null){
                result.append(linha);
            }
            
        } catch (FileNotFoundException ex) {
            throw MensageiroException.wrap(ex);
            
        } catch (IOException ioe) {
            throw MensageiroException.wrap(ioe);
            
        } finally {
            try {
                if (bfr!=null) bfr.close();
            } catch (IOException ex) {
                //faz nada
            }
        }
        return result.toString();
    }
    
}
