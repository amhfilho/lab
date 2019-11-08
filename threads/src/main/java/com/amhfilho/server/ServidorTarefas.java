package com.amhfilho.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServidorTarefas {

    private final ServerSocket servidor;
    private final ExecutorService executor;
    private AtomicBoolean estaRodando;
    private BlockingQueue<String> filaComandos;

    public ServidorTarefas() throws IOException {
        System.out.println(Runtime.getRuntime().availableProcessors() + " processadores");
        System.out.println("Iniciando o servidor...");
        servidor = new ServerSocket(12345);
        executor = Executors.newCachedThreadPool();
        estaRodando = new AtomicBoolean(true);
        filaComandos = new ArrayBlockingQueue<>(2);
        iniciarConsumidores();
    }

    private void iniciarConsumidores() {
        int consumidores = 2;
        for(int i = 0; i < consumidores; i++){
            TarefaConsumir tarefaConsumir = new TarefaConsumir(filaComandos);
            executor.execute(tarefaConsumir);
        }
    }


    public void rodar() throws IOException {
        while(this.estaRodando.get()) {
            try {
                Socket socket = servidor.accept();
                System.out.println("Aceitando um novo cliente na porta " + socket.getPort());
                executor.execute(new DistribuirTarefas(socket, this, executor, filaComandos));
            } catch (SocketException e){
                System.out.println("SocketException, esta rodando? " + this.estaRodando.get());
            }
        }
    }

    public void parar() throws IOException {
        this.estaRodando.set(false);
        servidor.close();
        executor.shutdown();
    }

    public static void main(String[] args) throws IOException {
        ServidorTarefas servidorTarefas = new ServidorTarefas();
        servidorTarefas.rodar();
    }


}
