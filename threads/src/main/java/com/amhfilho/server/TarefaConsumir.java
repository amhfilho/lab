package com.amhfilho.server;

import java.util.concurrent.BlockingQueue;

public class TarefaConsumir implements Runnable {
    private BlockingQueue<String> filaComandos;

    public TarefaConsumir(BlockingQueue<String> filaComandos){
        this.filaComandos = filaComandos;
    }

    @Override
    public void run() {
        String comando = null;
        while(true){
            try {
                while ((comando = filaComandos.take()) != null){
                    System.out.println("Consumindo comando " + comando + " na thread " + Thread.currentThread().getName());
                    Thread.sleep(10000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
