package com.amhfilho.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorTarefas {
    List<PrintStream> streams = new ArrayList<>();
    List<ObjectOutputStream> objectOutputStreams = new ArrayList<>();

    public void start() throws IOException {
        System.out.println(Runtime.getRuntime().availableProcessors() + " processadores");
        System.out.println("Iniciando o servidor...");
        ServerSocket servidor = new ServerSocket(12345);
        int idcount = 0;

        ExecutorService executor = Executors.newFixedThreadPool(2);

        while(true) {
            Socket socket = servidor.accept();
            System.out.println("Aceitando um novo cliente na porta " + socket.getPort());
            streams.add(new PrintStream(socket.getOutputStream()));
            objectOutputStreams.add(new ObjectOutputStream(socket.getOutputStream()));
            executor.execute(new DistribuirTarefas(socket, idcount++, this));

            //Thread.sleep(10000);
        }
    }

    public static void main(String[] args) throws IOException {
        new ServidorTarefas().start();
    }

    public void broadcast(String message){
        streams.forEach(x->x.println(message));
    }

    public void broadcastMensagem(Mensagem mensagem){
        objectOutputStreams.forEach(x-> {
            try {
                x.writeObject(mensagem);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


}
