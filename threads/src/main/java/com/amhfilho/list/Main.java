package com.amhfilho.list;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Lista lista = new Lista();
        for(int i = 0; i < 10; i++){
            new Thread(new TarefaAdicionarElemento(lista,i)).start();
        }

        Thread.sleep(2000);

        for(int i = 0; i <lista.tamanho(); i++) {
            System.out.println("indice " + i + ": " + lista.pegaElemento(i));
        }
    }
}
