package com.amhfilho.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TarefaBuscaTextual implements Runnable {
    String file;
    String nome;
    public TarefaBuscaTextual(String file, String nome) {
        this.file=file;
        this.nome=nome;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(new File(file));
            int linenumber = 1;
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                if(line.toLowerCase().contains(nome.toLowerCase())) {
                    System.out.println(String.format("Arquivo: %s - Nome: %s - Linha: %s", file, line, linenumber));
                }
                linenumber++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
