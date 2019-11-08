package com.amhfilho.server;

import java.io.PrintStream;
import java.util.concurrent.*;

public class JuntaFutures implements Callable<Void> {
    private Future<String> futureWs;
    private Future<String> futureBanco;
    private PrintStream saidaCliente;

    public JuntaFutures(Future<String> futureWs, Future<String> futureBanco, PrintStream saidaCliente) {
        this.futureWs = futureWs;
        this.futureBanco = futureBanco;
        this.saidaCliente = saidaCliente;
    }

    @Override
    public Void call() {
        System.out.println("Juntando resultados...");

        try {
            String retornoWS = futureWs.get(10, TimeUnit.SECONDS);
            String retornoBanco = futureBanco.get(10, TimeUnit.SECONDS);
            this.saidaCliente.println("Resultado do comando C2: " + retornoWS + ", "+ retornoBanco);

        } catch (InterruptedException |ExecutionException | TimeoutException  e) {
            System.out.println("Timeout: Cancelando a execução do Comando C2");
            this.saidaCliente.println("Timeout na execução do comando C2");
            futureWs.cancel(true);
            futureBanco.cancel(true);
        }

        System.out.println("Finalizou JuntaFutures");

        return null;
    }
}
