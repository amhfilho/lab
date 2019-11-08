package com.amhfilho.list;

public class TarefaAdicionarElemento implements Runnable{
    private Lista lista;
    private int threadId;

    public TarefaAdicionarElemento(Lista lista, int threadId) {
        this.threadId = threadId;
        this.lista = lista;
    }

    @Override
    public void run() {

        for(int i = 0; i < 100; i++){
            lista.adicionaElementos("Thread " + threadId + " - " + i);
        }

    }
}
