package com.amhfilho.server;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class ComandoC2ChamaWS implements Callable<String> {
    private PrintStream saidaCliente;

    public ComandoC2ChamaWS(PrintStream saidaCliente){
        this.saidaCliente = saidaCliente;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Executando comando C2 chama WS...");
        Thread.sleep(11000);
        int numero = new Random().nextInt(100)+1;
        saidaCliente.println("Comando C2 WS executado com sucesso.");
        System.out.println("Comando C2 WS executado com sucesso.");
        return new Integer(numero).toString();

    }
}
