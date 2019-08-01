package mensageiro.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Antonio
 */
public class MensageiroProperties {

    private static final String NOME_ARQUIVO = "c:\\mensageiro\\mail.properties";
    private static Properties properties = null;

    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(NOME_ARQUIVO));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, 
                    "ERRO FATAL: Não foi possível abrir o arquivo c:\\mensageiro\\mail.properties",
                    "ERRO FATAL",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
    public static String getPropriedade(String nomeProp) {
        
        return properties.getProperty(nomeProp);
    }
}
