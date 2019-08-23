package com.amhfilho.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DistribuirTarefas implements Runnable {
    private Socket socket;
    private int threadId;
    private ServidorTarefas tarefas;

    public DistribuirTarefas(Socket socket, int threadId, ServidorTarefas tarefas) {
        this.socket = socket;
        this.threadId = threadId;
        this.tarefas = tarefas;
    }

    @Override
    public void run() {
        System.out.println("Distribuindo tarefas para " + socket + " com thread Id " + threadId);
        try {
            //Scanner scanner = new Scanner(socket.getInputStream());
            //PrintStream saidaCliente = new PrintStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            final int millis = randomTime(10000, 20000);
            //sleep(millis);



            //while(scanner.hasNextLine()){
                //String command = scanner.nextLine();
            Mensagem mensagem = (Mensagem)inputStream.readObject();
                System.out.println(mensagem);
                //saidaCliente.println("Comando recebido: " + command);
                tarefas.broadcastMensagem(mensagem);
            //}

            //saidaCliente.close();
            //scanner.close();
            inputStream.reset();
//            System.out.println("Processamento finalizado para o socket " + socket.getPort() + " em " + millis + " milissegundos");

        } catch (IOException | ClassNotFoundException e) {
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
