/*
 * Paciente.java
 *
 * Created on 14 de Setembro de 2007, 10:06
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mensageiro.modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author antonioh
 */
public class Paciente {
    
    private Integer id;
    private String nome;
    private String email1;
    private String email2;
    private Date dataNascimento;
    private String codigo;
    private Integer anoEnviado;
    
    /** Creates a new instance of Paciente */
    public Paciente() {
    }
    
    public String getStrData() {
        DateFormat dformat = SimpleDateFormat.getDateInstance();
        return dformat.format(getDataNascimento());
    }
    
    public String toString() {
        return getNome()+","+getEmail1()+","+getEmail2()+","+getStrData();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email) {
        this.email1 = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getAnoEnviado() {
        return anoEnviado;
    }

    public void setAnoEnviado(Integer anoEnviado) {
        this.anoEnviado = anoEnviado;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }
    
}
