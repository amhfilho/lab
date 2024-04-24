/*
 * Main.java
 *
 * Created on 14 de Setembro de 2007, 09:48
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mensageiro.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import mensageiro.mail.SendMailAuthAttach;
import mensageiro.mail.ValidadorEmail;
import mensageiro.modelo.Paciente;

/**
 *
 * @author antonioh
 */
public class Main {
    private Date hoje;
    
    /** Creates a new instance of Main */
    public Main() {
        
        setHoje(new Date());
        PacienteDAO pDAO = PacienteDAO.getInstance();
        
        
        try{
            //Carregando aniversariantes de hoje
            List<Paciente> aniversariantes = pDAO.getPacientesPorAniversario(hoje);
            
            // Criando fila para envio
            List<Paciente> filaParaEnviar = new ArrayList<Paciente>();
            // Verificando se e-mail já foi enviado para cada aniversariante
            // Se não, adiciona na fila
            MensagensEnviadasDAO meDAO = MensagensEnviadasDAO.getInstance();
            System.out.println("Aniversariantes do dia:");
            for (Paciente p : aniversariantes) {
                System.out.println(p);
                List<Paciente> lista = meDAO.getPacientes(p.getCodigo(),getAnoAtual());
                if (lista.size() == 0){
                    filaParaEnviar.add(p);
                }
            }
            System.out.println();
            System.out.println("Fila para envio:");
            for (Paciente p: filaParaEnviar){
                System.out.println(p);
                //Faz o envio do e-mail
                SendMailAuthAttach emissor = new SendMailAuthAttach();
                List<String> destinatarios = new ArrayList<String>();
                p.setEmail1("xxx@xxx.com.br");
                p.setEmail2("xxxxxx@xxx.com.br");
                if (p.getEmail1()!=null && ValidadorEmail.isValid(p.getEmail1())){
                    destinatarios.add(p.getEmail1());
                }
                if (p.getEmail2()!=null && ValidadorEmail.isValid(p.getEmail2())){
                    destinatarios.add(p.getEmail2());
                }
                if (destinatarios.size()>0){
                    emissor.postMail(destinatarios);
                    System.out.println("E-mail enviado para: "+p.getNome());
                } else {
                    System.out.println("E-mail não enviado para "+p.getNome()+
                            ". e-mail invalido");
                }                
                
                //Grava na base o paciente com o email enviado
                p.setAnoEnviado(getAnoAtual());
                meDAO.addPacienteEnviado(p);
            }
            
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
        
    private int getAnoAtual() {
        Calendar cHoje = Calendar.getInstance();
        return cHoje.get(Calendar.YEAR);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main m = new Main();
    }
     
    private void testaDB() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:hsqldb:file:/db/pacientes","sa","");
            String sql = "SELECT * FROM PACIENTE";
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("NOME"));
            }
            
            rs.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public Date getHoje() {
        return hoje;
    }
    
    public void setHoje(Date hoje) {
        this.hoje = hoje;
    }
    
    
}
