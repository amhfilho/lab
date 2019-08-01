/*
 * PacienteDAO.java
 *
 * Created on 14 de Setembro de 2007, 10:08
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mensageiro.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import mensageiro.modelo.Paciente;

/**
 *
 * @author antonioh
 */
public class PacienteDAO {
    private static PacienteDAO instance;
    
    /** Creates a new instance of PacienteDAO */
    public PacienteDAO() {
    }
    
    public static PacienteDAO getInstance() {
        if (instance==null) {
            instance = new PacienteDAO();
        }
        return instance;
    }
    
    private static Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            
        } catch (ClassNotFoundException e){
            throw new SQLException("Classe não encontrada");
        }
        
        con = DriverManager.getConnection("jdbc:odbc:consultorio");
        return con;
    }
    
    public List<Paciente> getPacientesPorAniversario(Date data) throws SQLException {
        List<Paciente> lista = new ArrayList<Paciente>();
        
        String sql = "SELECT * FROM identificação WHERE MONTH(DATANASCIMENTO)=? AND DAY(DATANASCIMENTO)=?" +
                " AND (ENDEREÇODEEMAIL1 IS NOT NULL OR ENDEREÇOEMAIL2 IS NOT NULL)";
        
        Connection con = null;
        PreparedStatement pstm = null;
        
        try {
            con = getConnection();
            pstm = con.prepareStatement(sql);
            Calendar cData = Calendar.getInstance();
            cData.setTime(data);
            
            pstm.setInt(1,cData.get(Calendar.MONTH)+1);
            pstm.setInt(2,cData.get(Calendar.DATE));
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()){
                lista.add(getInflatedPaciente(rs));
            }
            
        } catch (SQLException e) {
            throw e;
        } finally {
            if (con!=null) con.close();
        }
        
        return lista;
        
    }
        
    private Paciente getInflatedPaciente(ResultSet rs) throws SQLException {
        Paciente paciente = new Paciente();
        paciente.setCodigo(rs.getString("códigodocliente"));
        paciente.setDataNascimento(rs.getDate("datanascimento"));
        paciente.setEmail1(rs.getString("EndereçodeEmail1"));
        paciente.setEmail2(rs.getString("endereçoemail2"));
        paciente.setNome(rs.getString("nomedocliente"));
        return paciente;
    }
    
    private String getStrDate(Date data) {
        if (data==null) return "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        return cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
    }
    
    
    public void testa() throws SQLException {
        Connection con = getConnection();
        
    }
}
