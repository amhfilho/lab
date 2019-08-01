/*
 * MensagensEnviadasDAO.java
 *
 * Created on 24 de Setembro de 2007, 14:02
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mensageiro.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import mensageiro.modelo.Paciente;

/**
 *
 * @author antonioh
 */
public class MensagensEnviadasDAO {
    
    private static MensagensEnviadasDAO instance;
    private static Connection con;
    private static final String CLASS_NAME = "sun.jdbc.odbc.JdbcOdbcDriver";
    private static final String DB_URL = "jdbc:odbc:enviados";
    private static final String USERNAME = null;
    private static final String PASSWORD = null;
    
    /** Creates a new instance of MensagensEnviadasDAO */
    private MensagensEnviadasDAO() {
    }
    
    public static MensagensEnviadasDAO getInstance() {
        if (instance==null) {
            instance = new MensagensEnviadasDAO();
        }
        return instance;
    }
    
    public List<Paciente> getPacientes(String codigo, int anoAtual) throws SQLException {
        List<Paciente> pacientes = new ArrayList<Paciente>();
        Connection con = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM PACIENTE WHERE CODIGO=? AND ANOENVIADO=?";
        try {
            con = ConnectionServiceLocator.getConnection(CLASS_NAME,DB_URL,USERNAME,PASSWORD);
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1,codigo);
            pstm.setInt(2,anoAtual);
            
            rs = pstm.executeQuery();
            while (rs.next()){
                pacientes.add(getInflatedPaciente(rs));
            }
        
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                if (rs !=null) {
                    rs.close();
                }
                if (con !=null){
                    con.close();
                }
            } catch (SQLException e){
                //faz nada
            }
        }
        return pacientes;
    }
    
    public void addPacienteEnviado(Paciente paciente) throws SQLException {
        Connection con = null;
        String sql = "INSERT INTO PACIENTE (CODIGO,NOME,EMAIL1,EMAIL2,ANOENVIADO) VALUES (?,?,?,?,?)";
        Calendar hoje = Calendar.getInstance();
        
        try {
            con = ConnectionServiceLocator.getConnection(CLASS_NAME,DB_URL,USERNAME,PASSWORD);
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1,paciente.getCodigo());
            pstm.setString(2,paciente.getNome());
            pstm.setString(3,paciente.getEmail1());
            pstm.setString(4,paciente.getEmail2());
            pstm.setInt(5,hoje.get(Calendar.YEAR));
            pstm.executeUpdate();
            
        } catch (SQLException e) {
            throw e;
            
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                // faz nada
            }
        }
    }
    
    private Paciente getInflatedPaciente(ResultSet rs) throws SQLException {
        Paciente p = new Paciente();
        p.setId(new Integer(rs.getInt("id")));
        p.setNome(rs.getString("nome"));
        p.setEmail1(rs.getString("email1"));
        p.setEmail2(rs.getString("email2"));
        p.setCodigo(rs.getString("codigo"));
        p.setAnoEnviado(new Integer(rs.getInt("anoenviado")));
        return p;
    }
    
}
