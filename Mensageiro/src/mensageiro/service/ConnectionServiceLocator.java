/*
 * ConnectionServiceLocator.java
 */

package mensageiro.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Responsável em fornecer conexao com o banco de dados
 * @author antonioh
 */
public class ConnectionServiceLocator {
    private static Connection con;
    
    /** Creates a new instance of ConnectionServiceLocator */
    private ConnectionServiceLocator() {
    }
    
    public static Connection getConnection(
            String className, String url, String username, String password) throws SQLException {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
        if (username!=null && password !=null) {
            con = DriverManager.getConnection(url,username,password);
        } else {
            con = DriverManager.getConnection(url);
        }
        
        
        return con;
    }
    
}
