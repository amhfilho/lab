package com.amhfilho.server;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class ComandoC2ChamaBanco implements Callable<String> {
    private PrintStream saidaCliente;

    public ComandoC2ChamaBanco(PrintStream saidaCliente){
        this.saidaCliente = saidaCliente;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Executando comando C2 Banco...");
        Thread.sleep(15000);
        int numero = new Random().nextInt(100)+1;
        saidaCliente.println("Comando C2 banco executado com sucesso.");
        System.out.println("Comando C2 banco executado com sucesso.");
        return new Integer(numero).toString();

    }
}
