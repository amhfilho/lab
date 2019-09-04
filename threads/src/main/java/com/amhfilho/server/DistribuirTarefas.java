package com.amhfilho.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class DistribuirTarefas implements Runnable {
    private Socket socket;
    private ServidorTarefas servidor;
    private ExecutorService executorService;
    private BlockingQueue<String> filaComandos;

    public DistribuirTarefas(Socket socket, ServidorTarefas servidor, ExecutorService executorService, BlockingQueue<String> filaComandos) {
        this.socket = socket;
        this.servidor = servidor;
        this.executorService = executorService;
        this.filaComandos = filaComandos;
    }

    @Override
    public void run() {
        System.out.println("Distribuindo tarefas para " + socket);
        try {
            Scanner scanner = new Scanner(socket.getInputStream());
            PrintStream saidaCliente = new PrintStream(socket.getOutputStream());

            while(scanner.hasNextLine()){
                String command = scanner.nextLine();
                saidaCliente.println("Comando recebido: " + command);
                switch (command){
                    case "c1":
                        System.out.println("Cliente enviou comando c1");
                        executorService.execute(new ComandoC1(saidaCliente));
                        break;
                    case "c2":
                        System.out.println("Cliente enviou comando c2");
                        Future<String> futureWs  = executorService.submit(new ComandoC2ChamaWS(saidaCliente));
                        Future<String> futureBanco = executorService.submit(new ComandoC2ChamaBanco(saidaCliente));
                        executorService.submit(new JuntaFutures(futureWs,futureBanco,saidaCliente));
                        break;
                    case "c3":
                        System.out.println("Cliente enviou comando c3");
                        this.filaComandos.put(command);
                        saidaCliente.println("Comando c3 adicionado na fila");
                        break;

                    case "exit":
                        servidor.parar();
                    default:
                        System.out.println("Comando invalido");
                        saidaCliente.println("Comando invalido");
                }

            }
            saidaCliente.close();
            scanner.close();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private int randomTime(int low, int high) {
        Random r = new Random();
        return r.nextInt(high - low) + low;
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
