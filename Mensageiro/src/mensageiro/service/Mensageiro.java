/*
 * Mensageiro.java
 *
 * Created on 20 de Outubro de 2007, 00:35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mensageiro.service;

import java.awt.Cursor;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import mensageiro.gui.Tela;
import mensageiro.mail.MailProperties;
import mensageiro.mail.MensageiroException;
import mensageiro.mail.SendMailAuthAttach;
import mensageiro.mail.ValidadorEmail;
import mensageiro.modelo.Paciente;

/**
 *
 * @author antonioh
 */
public class Mensageiro {
    
    private Date hoje;
    private PacienteDAO pDAO;
    private MensagensEnviadasDAO meDAO;
    private List<Paciente> aniversariantes;
    private List<Paciente> filaParaEnviar;
    private MailProperties props;
    private Tela tela;
    private static Mensageiro instance;
    
    public static void main(String[] args) {
        Mensageiro app = new Mensageiro();       
    }
    
    public static Mensageiro getInstance() {
        if (instance ==null) {
            instance = new Mensageiro();
        }
        return instance;
    }
    
    /** Creates a new instance of Mensageiro */
    private Mensageiro() {        
        // Carrega dados e propriedades
        hoje = new Date();
        pDAO = PacienteDAO.getInstance();
        meDAO = MensagensEnviadasDAO.getInstance();
        try {
            carregaPropriedades();
            
        } catch (RuntimeException e) {
            tela.exibeMensagem(e.getMessage(),true);
            tela.dispose();
            System.exit(0);
        }   
    }
       
    /**
     * Retorna um inteiro que representa o ano atual
     */
    private int getAnoAtual() {
        Calendar cHoje = Calendar.getInstance();
        return cHoje.get(Calendar.YEAR);
    }
    
    /**
     * FAZ O ENVIO DOS E-MAILS
     */
    public void enviaEmailsFila() {
        
        tela.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        
        try{
            carregaDadosPacientes();
            
        } catch (RuntimeException e) {
            tela.exibeMensagem(e.getMessage(),true);
            tela.dispose();
            System.exit(0);
        }
        
        if (aniversariantes.size() == 0) {
            tela.escreveLog("Não há aniversariantes hoje.");
        } else {
            if (filaParaEnviar.size() == 0) {
                tela.escreveLog("Não há e-mails para enviar.");
            }
        }
        
        // Envia os e-mails para os pacientes da fila
        for (Paciente p: filaParaEnviar){
            
            //Teste somente
            if (props.getPropriedade("TESTE").equals("TRUE")) {
                p.setEmail1("ariadinepaesdealmeida@yahoo.com.br");
                p.setEmail2(null);
            }
            
            // Valida os e-mails dos pacientes
            List<String> destinatarios = new ArrayList<String>();
            if (p.getEmail1()!=null && ValidadorEmail.isValid(p.getEmail1())){
                destinatarios.add(p.getEmail1());
            }
            if (p.getEmail2()!=null && ValidadorEmail.isValid(p.getEmail2())){
                destinatarios.add(p.getEmail2());
            }
            
            if (destinatarios.size() > 0) {
                SendMailAuthAttach emissor = new SendMailAuthAttach();
                try {
                    
                    emissor.postMail(destinatarios);
                    p.setAnoEnviado(getAnoAtual());
                    meDAO.addPacienteEnviado(p);
                    tela.escreveLog("E-mail enviado com sucesso para "+p.getNome()+" - "+p.getEmail1()+","+p.getEmail2());
                    
                } catch (MensageiroException e) {
                    tela.escreveLog("Erro ao tentar enviar e-mail para "+p.getNome()+". "+e.getMessage());
                    
                } catch (SQLException e) {
                    tela.escreveLog("E-mail enviado para "+p.getNome()+
                            ". Porém não foi possível gravar esta informação no banco de dados");
                }
            } else {
                tela.escreveLog("Não há e-mail válido para "+p.getNome());
            }
        }
        tela.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    
    /**
     * Carrega dados do banco dos pacientes
     */
    private void carregaDadosPacientes() {
        try {
            aniversariantes = pDAO.getPacientesPorAniversario(hoje);
            filaParaEnviar = new ArrayList<Paciente>();
            for (Paciente p: aniversariantes) {
                List<Paciente> lista = meDAO.getPacientes(p.getCodigo(),getAnoAtual());
                if (lista.size()==0){
                    filaParaEnviar.add(p);
                }
            }
            
        } catch (SQLException e) {
            throw MensageiroException.wrap(new RuntimeException("Erro ao carregar os dados"));
        }
    }
    
    private void carregaPropriedades() {
        try {
            
            props = MailProperties.getInstance();
            
        } catch (IOException ex) {
            throw MensageiroException.wrap(new RuntimeException("Erro ao carregar arquivo de propriedades"));
        }
    }

    public Tela getTela() {
        return tela;
    }

    public void setTela(Tela tela) {
        this.tela = tela;
    }
}
