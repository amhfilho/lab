package com.amhfilho.search;

public class Main {
    public static void main(String[] args) {
        String nome = "an";
        Thread assinaturas1 = new Thread(new TarefaBuscaTextual("c:/Users/mult-e/Downloads/assinaturas1.txt",nome));
        Thread assinaturas2 = new Thread(new TarefaBuscaTextual("c:/Users/mult-e/Downloads/assinaturas2.txt",nome));
        Thread autores = new Thread(new TarefaBuscaTextual("c:/Users/mult-e/Downloads/autores.txt",nome));

        assinaturas1.start();
        assinaturas2.start();
        autores.start();
    }
}
